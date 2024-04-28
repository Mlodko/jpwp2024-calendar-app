package client.backend;

import client.backend.models.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.eclipse.jetty.client.*;
import org.eclipse.jetty.client.transport.HttpRequest;
import org.eclipse.jetty.http.HttpMethod;
import org.eclipse.jetty.http.HttpStatus;
import org.eclipse.jetty.io.Transport;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.stream.Collectors;

public class RequestManager {

    private static final String IP_ADDR = "127.0.0.1:8080";

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
        httpClient.POST("http://" + IP_ADDR + "/login/")
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

    public Optional<User> makeRegisterRequest(User temp) {
        Optional<User> loggedInUser = Optional.empty();
        InputStreamResponseListener listener = new InputStreamResponseListener();
        httpClient.POST("http://" + IP_ADDR + "/register/")
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
}
