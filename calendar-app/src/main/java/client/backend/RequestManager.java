package client.backend;

import client.backend.models.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonWriter;
import org.eclipse.jetty.client.*;
import org.eclipse.jetty.http.HttpHeader;
import org.eclipse.jetty.http.HttpMethod;
import org.eclipse.jetty.http.HttpStatus;
import org.eclipse.jetty.util.ssl.SslContextFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.stream.Collectors;

public class RequestManager implements AutoCloseable {

    private static final String IP_ADDR = "127.0.0.1";
    private static final int HTTP_PORT = 8080;
    private static final int HTTPS_PORT = 8443;
    private static final String HTTPS_SERVER_URL = "https://" + IP_ADDR + ":" + HTTPS_PORT;
    //private static final String HTTP_SERVER_URL = "http://" + IP_ADDR + ":" + HTTP_PORT;

    static final Path KEYSTORE_PATH = Path.of(Paths.get("").toAbsolutePath() + "/src/main/resources/server/keystore.jks");
    static final String KEYSTORE_PASSWORD = "cd@%w46cgQay!pZtrbMp$@az&Df3F4QTHuF4$LRaETA2ryo#9&qkE#nG!inUT$o5B$S5P6DT6#FSxtDjj#mcu5YZE4T4Cqr5nNdBi6KR#csut5LtwpBFKh6$6CjGZ@B6";

    private final HttpClient httpClient;
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();

    public RequestManager() throws Exception {
        SslContextFactory.Client sslContextFactory = new SslContextFactory.Client();
        sslContextFactory.setTrustAll(true);
        sslContextFactory.setKeyStorePath(KEYSTORE_PATH.toString());
        sslContextFactory.setKeyStorePassword(KEYSTORE_PASSWORD);
        this.httpClient = new HttpClient();
        httpClient.setSslContextFactory(sslContextFactory);
        httpClient.setFollowRedirects(false);
        httpClient.start();
    }

    public Optional<User> makeLoginRequest(User user) {
        Optional<User> loggedInUser = Optional.empty();
        InputStreamResponseListener listener = new InputStreamResponseListener();

        httpClient.POST(HTTPS_SERVER_URL + "/login/")
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
                    String authToken = listener.get(5, TimeUnit.SECONDS).getHeaders().get(HttpHeader.AUTHORIZATION);
                    authenticatedUser.setAuthToken(authToken);
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
        httpClient.POST(HTTPS_SERVER_URL + "/register/")
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

    public Optional<ArrayList<Workspace>> getWorkspaces(String authToken, String... workspaceIds) { // ids meaning workspace-ids
        Request request;
        if(workspaceIds.length > 1) {
            String encodedIds;
            try {
                encodedIds = URLEncoder.encode(String.join(",", workspaceIds), StandardCharsets.UTF_8);
            } catch(Exception e) {
                return Optional.empty();
            }
            request = httpClient.newRequest(HTTPS_SERVER_URL + "/workspace/")
                    .method(HttpMethod.GET)
                    .param("workspace-ids", encodedIds)
                    .headers(req -> req.add(HttpHeader.AUTHORIZATION, authToken));
        } else {
            String encodedId;
            try {
                 encodedId = URLEncoder.encode(workspaceIds[0], StandardCharsets.UTF_8);
            } catch(Exception e) {
                return Optional.empty();
            }
            request = httpClient.newRequest(HTTPS_SERVER_URL + "/workspace/")
                    .param("workspace-ids", encodedId)
                    .method(HttpMethod.GET)
                    .headers(req -> req.add(HttpHeader.AUTHORIZATION, authToken));
        }

        ContentResponse content;
        try {
            content = request.send();
        } catch(Exception e) {
            return Optional.empty();
        }

        if(content.getStatus() == 200) {
            Type workspaceArrayType = new TypeToken<ArrayList<Workspace>>(){}.getType();
            ArrayList<Workspace> workspaces = gson.fromJson(content.getContentAsString(), workspaceArrayType);

            if(workspaces == null || workspaces.isEmpty()) {
                return Optional.empty();
            }

            return Optional.of(workspaces);
        } else {
            System.out.println(content.getStatus() + ": " + content.getReason());
        }

        return Optional.empty();
    }

    public boolean postWorkspaces(String authToken, ArrayList<Workspace> workspaces) {

        // Prepare json
        JsonObject rootObject = new JsonObject();
        rootObject.add("workspaces", gson.toJsonTree(workspaces));

        ContentResponse content;
        try {
            content = httpClient.newRequest(HTTPS_SERVER_URL + "/workspace/")
                    .method(HttpMethod.POST)
                    .headers(request -> request.add(HttpHeader.AUTHORIZATION, authToken))
                    .body(new StringRequestContent("application/json", gson.toJson(rootObject)))
                    .send();
        } catch (Exception e) {
            return false;
        }

        return content.getStatus() == HttpStatus.OK_200;
    }

    public Optional<ArrayList<Calendar>> getCalendars(String authToken, String workspaceId, String... calendarIds) {
        Request request;
        if(calendarIds.length > 1) {
            String encodedIds;
            try {
                encodedIds = URLEncoder.encode(String.join(",", calendarIds), StandardCharsets.UTF_8);
            } catch(Exception e) {
                return Optional.empty();
            }
            request = httpClient.newRequest(HTTPS_SERVER_URL + "/calendar/")
                    .method(HttpMethod.GET)
                    .param("workspace-id", workspaceId)
                    .param("calendar-ids", encodedIds)
                    .headers(req -> req.add(HttpHeader.AUTHORIZATION, authToken));
        } else {
            String encodedId;
            try {
                encodedId = URLEncoder.encode(calendarIds[0], StandardCharsets.UTF_8);
            } catch(Exception e) {
                return Optional.empty();
            }
            request = httpClient.newRequest(HTTPS_SERVER_URL + "/calendar/")
                    .param("workspace-id", workspaceId)
                    .param("calendar-ids", encodedId)
                    .method(HttpMethod.GET)
                    .headers(req -> req.add(HttpHeader.AUTHORIZATION, authToken));
        }

        ContentResponse content;
        try {
            content = request.send();
        } catch(Exception e) {
            return Optional.empty();
        }

        if(content.getStatus() == 200) {
            Type workspaceArrayType = new TypeToken<ArrayList<Calendar>>(){}.getType();
            ArrayList<Calendar> calendars = gson.fromJson(content.getContentAsString(), workspaceArrayType);

            if(calendars == null || calendars.isEmpty()) {
                return Optional.empty();
            }

            return Optional.of(calendars);
        } else {
            System.out.println(content.getStatus() + ": " + content.getReason());
        }

        return Optional.empty();
    }

    public boolean postCalendars(String authToken, String workspaceId, ArrayList<Calendar> calendars) {

        // Prepare json
        JsonObject rootObject = new JsonObject();
        rootObject.addProperty("workspace-id", workspaceId);
        rootObject.add("calendars", gson.toJsonTree(calendars));

        ContentResponse content;
        try {
            content = httpClient.newRequest(HTTPS_SERVER_URL + "/calendar/")
                    .method(HttpMethod.POST)
                    .headers(request -> request.add(HttpHeader.AUTHORIZATION, authToken))
                    .body(new StringRequestContent("application/json", gson.toJson(rootObject)))
                    .send();
        } catch (Exception e) {
            return false;
        }

        return content.getStatus() == HttpStatus.OK_200;
    }

    public Optional<ArrayList<KanbanBoard>> getBoards(String authToken, String workspaceId, String calendarId, String... boardIds) {
        Request request;
        if(boardIds.length > 1) {
            String encodedIds;
            try {
                encodedIds = URLEncoder.encode(String.join(",", boardIds), StandardCharsets.UTF_8);
            } catch(Exception e) {
                return Optional.empty();
            }
            request = httpClient.newRequest(HTTPS_SERVER_URL + "/board/")
                    .method(HttpMethod.GET)
                    .param("workspace-id", workspaceId)
                    .param("calendar-id", calendarId)
                    .param("board-ids", encodedIds)
                    .headers(req -> req.add(HttpHeader.AUTHORIZATION, authToken));
        } else {
            String encodedId;
            try {
                encodedId = URLEncoder.encode(boardIds[0], StandardCharsets.UTF_8);
            } catch(Exception e) {
                return Optional.empty();
            }
            request = httpClient.newRequest(HTTPS_SERVER_URL + "/board/")
                    .method(HttpMethod.GET)
                    .param("workspace-id", workspaceId)
                    .param("calendar-id", calendarId)
                    .param("board-ids", encodedId)
                    .headers(req -> req.add(HttpHeader.AUTHORIZATION, authToken));
        }

        ContentResponse content;
        try {
            content = request.send();
        } catch(Exception e) {
            return Optional.empty();
        }

        if(content.getStatus() == 200) {
            Type workspaceArrayType = new TypeToken<ArrayList<KanbanBoard>>(){}.getType();
            ArrayList<KanbanBoard> boards = gson.fromJson(content.getContentAsString(), workspaceArrayType);

            if(boards == null || boards.isEmpty()) {
                return Optional.empty();
            }

            return Optional.of(boards);
        } else {
            System.out.println(content.getStatus() + ": " + content.getReason());
        }

        return Optional.empty();
    }

    public boolean postBoards(String authToken, String workspaceId, String calendarId, ArrayList<KanbanBoard> boards) {
        // prepare json
        JsonObject rootObject = new JsonObject();
        rootObject.addProperty("workspace-id", workspaceId);
        rootObject.addProperty("calendar-id", calendarId);
        rootObject.add("boards", gson.toJsonTree(boards));

        ContentResponse content;
        try {
            content = httpClient.newRequest(HTTPS_SERVER_URL + "/board/")
                    .method(HttpMethod.POST)
                    .headers(request -> request.add(HttpHeader.AUTHORIZATION, authToken))
                    .body(new StringRequestContent("application/json", gson.toJson(rootObject)))
                    .send();
        } catch (Exception e) {
            return false;
        }

        return content.getStatus() == HttpStatus.OK_200;
    }

    public Optional<ArrayList<Card>> getKanbanCards(String authToken, String workspaceId, String calendarId, String boardId, String... cardIds) {
        Request request;
        if(cardIds.length > 1) {
            String encodedIds;
            try {
                encodedIds = URLEncoder.encode(String.join(",", cardIds), StandardCharsets.UTF_8);
            } catch(Exception e) {
                return Optional.empty();
            }
            request = httpClient.newRequest(HTTPS_SERVER_URL + "/card/")
                    .method(HttpMethod.GET)
                    .param("type", "board-card")
                    .param("workspace-id", workspaceId)
                    .param("calendar-id", calendarId)
                    .param("board-id", boardId)
                    .param("card-ids", encodedIds)
                    .headers(req -> req.add(HttpHeader.AUTHORIZATION, authToken));
        } else {
            String encodedId;
            try {
                encodedId = URLEncoder.encode(cardIds[0], StandardCharsets.UTF_8);
            } catch(Exception e) {
                return Optional.empty();
            }
            request = httpClient.newRequest(HTTPS_SERVER_URL + "/card/")
                    .method(HttpMethod.GET)
                    .param("type", "board-card")
                    .param("workspace-id", workspaceId)
                    .param("calendar-id", calendarId)
                    .param("board-id", boardId)
                    .param("card-ids", encodedId)
                    .headers(req -> req.add(HttpHeader.AUTHORIZATION, authToken));
        }

        ContentResponse content;
        try {
            content = request.send();
        } catch(Exception e) {
            return Optional.empty();
        }

        if(content.getStatus() == 200) {
            Type cardArrayType = new TypeToken<ArrayList<Card>>(){}.getType();
            ArrayList<Card> cards = gson.fromJson(content.getContentAsString(), cardArrayType);

            if(cards == null || cards.isEmpty()) {
                return Optional.empty();
            }

            return Optional.of(cards);
        } else {
            System.out.println(content.getStatus() + ": " + content.getReason());
        }

        return Optional.empty();
    }

    public boolean postKanbanCards(String authToken, String workspaceId, String boardId, String columnName, ArrayList<Card> cards) {
        // Prepare json
        JsonObject rootObject = new JsonObject();
        rootObject.addProperty("type", "board-card");
        rootObject.addProperty("workspace-id", workspaceId);
        rootObject.addProperty("board-id", boardId);
        rootObject.addProperty("column-name", columnName);
        rootObject.add("cards", gson.toJsonTree(cards));

        ContentResponse content;
        try {
            content = httpClient.newRequest(HTTPS_SERVER_URL + "/card/")
                    .method(HttpMethod.POST)
                    .headers(request -> request.add(HttpHeader.AUTHORIZATION, authToken))
                    .body(new StringRequestContent("application/json", gson.toJson(rootObject)))
                    .send();
        } catch (Exception e) {
            return false;
        }

        return content.getStatus() == HttpStatus.OK_200;
    }

    public Optional<ArrayList<Card>> getOrphanCards(String authToken, String workspaceId, String calendarId, String... cardIds) {
        Request request;
        if(cardIds.length > 1) {
            String encodedIds;
            try {
                encodedIds = URLEncoder.encode(String.join(",", cardIds), StandardCharsets.UTF_8);
            } catch(Exception e) {
                return Optional.empty();
            }
            request = httpClient.newRequest(HTTPS_SERVER_URL + "/card/")
                    .method(HttpMethod.GET)
                    .param("type", "orphan-card")
                    .param("workspace-id", workspaceId)
                    .param("calendar-id", calendarId)
                    .param("card-ids", encodedIds)
                    .headers(req -> req.add(HttpHeader.AUTHORIZATION, authToken));
        } else {
            String encodedId;
            try {
                encodedId = URLEncoder.encode(cardIds[0], StandardCharsets.UTF_8);
            } catch(Exception e) {
                return Optional.empty();
            }
            request = httpClient.newRequest(HTTPS_SERVER_URL + "/card/")
                    .method(HttpMethod.GET)
                    .param("type", "orphan-card")
                    .param("workspace-id", workspaceId)
                    .param("calendar-id", calendarId)
                    .param("card-ids", encodedId)
                    .headers(req -> req.add(HttpHeader.AUTHORIZATION, authToken));
        }

        ContentResponse content;
        try {
            content = request.send();
        } catch(Exception e) {
            return Optional.empty();
        }

        if(content.getStatus() == 200) {
            Type cardArrayType = new TypeToken<ArrayList<Card>>(){}.getType();
            ArrayList<Card> cards = gson.fromJson(content.getContentAsString(), cardArrayType);

            if(cards == null || cards.isEmpty()) {
                return Optional.empty();
            }

            return Optional.of(cards);
        } else {
            System.out.println(content.getStatus() + ": " + content.getReason());
        }

        return Optional.empty();
    }

    public boolean postOrphanCards(String authToken, String workspaceId, String calendarId, ArrayList<Card> cards) {
        // Prepare json
        JsonObject rootObject = new JsonObject();
        rootObject.addProperty("type", "orphan-card");
        rootObject.addProperty("workspace-id", workspaceId);
        rootObject.addProperty("calendar-id", calendarId);
        rootObject.add("cards", gson.toJsonTree(cards));

        ContentResponse content;
        try {
            content = httpClient.newRequest(HTTPS_SERVER_URL + "/card/")
                    .method(HttpMethod.POST)
                    .headers(request -> request.add(HttpHeader.AUTHORIZATION, authToken))
                    .body(new StringRequestContent("application/json", gson.toJson(rootObject)))
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

    public static void main(String[] args) throws Exception {
        User user = new User("klaun", "65e84be33532fb784c48129675f9eff3a682b27168c0ea744b2cf58ee02337c5", "test@test.test");
        try(RequestManager requestManager = new RequestManager()) {
            Optional<User> loggedInUser = requestManager.makeLoginRequest(user);
            System.out.println("Login: " + loggedInUser.isPresent());

            System.out.println("GET workspace: " + requestManager.getWorkspaces(loggedInUser.get().getAuthToken(), "test").isPresent());

            Calendar calendar = new Calendar("1");
            ArrayList<Calendar> calendarArrayList = new ArrayList<>();
            calendarArrayList.add(calendar);
            System.out.println("POST calendar: " + requestManager.postCalendars(loggedInUser.get().getAuthToken(), "test", calendarArrayList));

            System.out.println("GET calendar: " + requestManager.getCalendars(loggedInUser.get().getAuthToken(), "test", "1").isPresent());
        }
    }
}
