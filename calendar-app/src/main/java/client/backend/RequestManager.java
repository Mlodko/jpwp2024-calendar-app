package client.backend;

import client.backend.models.User;
import client.backend.models.Workspace;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.eclipse.jetty.client.*;
import org.eclipse.jetty.http.HttpHeader;
import org.eclipse.jetty.http.HttpStatus;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.stream.Collectors;

public class RequestManager implements AutoCloseable {

    private static final String IP_ADDR = "127.0.0.1";
    private static final int PORT = 8080;
    private static final String SERVER_URL = "http://" + IP_ADDR + ":" + PORT;
    private final HttpClient httpClient;
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();

    public RequestManager() throws Exception {
        this.httpClient = new HttpClient();
        httpClient.setFollowRedirects(false);
        httpClient.start();
    }

    public Optional<User> makeLoginRequest(User user) {
        Optional<User> loggedInUser = Optional.empty();
        InputStreamResponseListener listener = new InputStreamResponseListener();

        httpClient.POST(SERVER_URL + "/login/")
                .body(new StringRequestContent("application/json", gson.toJson(user)))
                .send(listener);

        try {
            Response response = listener.get(5, TimeUnit.SECONDS);
            if (response.getStatus() == HttpStatus.OK_200) {
                try(InputStream content = listener.getInputStream()) {
                    String json = new BufferedReader(
                            new InputStreamReader(content, StandardCharsets.UTF_8))
                            .lines()
                            .collect(Collectors.joining("\n"));

                    User authenticatedUser = gson.fromJson(json, User.class);
                    listener.get(5, TimeUnit.SECONDS).getHeaders().get(HttpHeader.AUTHORIZATION);
                    loggedInUser = Optional.of(authenticatedUser);
                }
            }
            else {
                System.out.println(response.getStatus() + ": " + response.getReason());
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (TimeoutException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return loggedInUser;
    }

    public Optional<User> makeRegisterRequest(User temp) {
        Optional<User> loggedInUser = Optional.empty();
        InputStreamResponseListener listener = new InputStreamResponseListener();
        httpClient.POST(SERVER_URL + "/register/")
                .body(new StringRequestContent("application/json", gson.toJson(temp)))
                .send(listener);

        try {
            Response response = listener.get(5, TimeUnit.SECONDS);

            if (response.getStatus() == HttpStatus.OK_200) {
                try(InputStream content = listener.getInputStream()) {

                    String json = new BufferedReader(
                            new InputStreamReader(content, StandardCharsets.UTF_8))
                            .lines()
                            .collect(Collectors.joining("\n"));

                    listener.get(5, TimeUnit.SECONDS).getHeaders().get(HttpHeader.AUTHORIZATION);
                    loggedInUser = Optional.of(gson.fromJson(json, User.class));
                }
            }
            else {
                System.out.println(response.getStatus() + ": " + response.getReason());
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (TimeoutException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return loggedInUser;
    }

    public Optional<ArrayList<Workspace>> getWorkspaces(String... ids) {
        InputStreamResponseListener listener = new InputStreamResponseListener();
        ContentResponse content;

        try {
            if(ids.length > 1) {
                String longString = String.join(",", ids);
                content = httpClient.GET(SERVER_URL + "/workspaces?ids=" + longString);
            } else {
                content = httpClient.GET(SERVER_URL + "/workspaces?ids" + ids[0]);
            }
        } catch (Exception e) {
            return Optional.empty();
        }

        if(content.getStatus() == 200) {
            Type workspaceArrayType = new TypeToken<ArrayList<Workspace>>(){}.getType();
            ArrayList<Workspace> workspaces = gson.fromJson(content.getContentAsString(), workspaceArrayType);

            if(workspaces == null || workspaces.isEmpty()) {
                return Optional.empty();
            }

            return Optional.of(workspaces);
        }

        return Optional.empty();
    }

    public boolean postWorkspace(Workspace workspace) {
        ContentResponse content;

        try {
            content = httpClient.POST(SERVER_URL + "/workspace")
                    .body(new StringRequestContent("application/json", gson.toJson(workspace)))
                    .send();
        } catch (Exception e) {
            return false;
        }

        return content.getStatus() == HttpStatus.OK_200;
    }

    @Override
    public void close() throws Exception {
        httpClient.stop();
    }

    // for debugging purposes
    public static void main(String[] args) {
        try {
            Optional<User> optUsr = User.register("bob", "bob12345", "bob@watykan.it");
        } catch (Exception e) {
            System.out.println("mam wylew");
            e.printStackTrace();
        }
    }
}
