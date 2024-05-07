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
import java.util.List;
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

    public boolean makeLogoutRequest(User user) {
        if(user.getAuthToken() == null) {
            return false;
        }

        try {
            ContentResponse content = httpClient.newRequest(HTTPS_SERVER_URL + "/login/")
                    .method(HttpMethod.DELETE)
                    .headers(request -> request.add(HttpHeader.AUTHORIZATION, user.getAuthToken()))
                    .send();
            if(content.getStatus() != 200) {
                return false;
            }
        } catch (Exception e) {
            return false;
        }

        return true;
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

    public Optional<ArrayList<Calendar>> getCalendars(String authToken, String workspaceId, ArrayList<String> calendarIds) {
        Request request;
        if(calendarIds.size() > 1) {
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
                encodedId = URLEncoder.encode(calendarIds.get(0), StandardCharsets.UTF_8);
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

    public Optional<ArrayList<KanbanBoard>> getBoards(String authToken, String workspaceId, String calendarId, List<String> boardIds) {
        Request request;
        if(boardIds.size() > 1) {
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
                encodedId = URLEncoder.encode(boardIds.get(0), StandardCharsets.UTF_8);
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

    public Optional<ArrayList<Card>> getKanbanCards(String authToken, String workspaceId, String calendarId, String boardId, ArrayList<String> cardIds) {
        Request request;
        if(cardIds.size() > 1) {
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
                    .param("board-id", URLEncoder.encode(boardId, StandardCharsets.UTF_8))
                    .param("card-ids", encodedIds)
                    .headers(req -> req.add(HttpHeader.AUTHORIZATION, authToken));
        } else {
            String encodedId;
            try {
                encodedId = URLEncoder.encode(cardIds.get(0), StandardCharsets.UTF_8);
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

    public boolean postKanbanCards(String authToken, String workspaceId, String calendarId, String boardId, String columnName, ArrayList<Card> cards) {
        // Prepare json
        JsonObject rootObject = new JsonObject();
        rootObject.addProperty("type", "board-card");
        rootObject.addProperty("workspace-id", workspaceId);
        rootObject.addProperty("calendar-id", calendarId);
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

    public Optional<ArrayList<Card>> getOrphanCards(String authToken, String workspaceId, String calendarId, ArrayList<String> cardIds) {
        Request request;
        if(cardIds.size() > 1) {
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
                encodedId = URLEncoder.encode(cardIds.get(0), StandardCharsets.UTF_8);
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

        /*
        What works:
        - [x] login
        - [x] register
        - workspace
            - GET [x]
            - POST [x]
        - calendar
            - GET [x]
            - POST [x]
        - board
            - GET [x]
            - POST [x]
        - kanban card
            - GET [x]
            - POST [x]
        - orphan card
            - GET [x]
            - POST [x]

         IT IS DONE. BILLIONS SHALL PERISH FROM ITS GLOW.
         */

        User user = new User("klaun", "65e84be33532fb784c48129675f9eff3a682b27168c0ea744b2cf58ee02337c5", "test@test.test");
        try(RequestManager requestManager = new RequestManager()) {
            Optional<User> loggedInUser = requestManager.makeLoginRequest(user);
            System.out.println("Login: " + loggedInUser.isPresent());

            System.out.println("GET workspace: " + requestManager.getWorkspaces(loggedInUser.get().getAuthToken(), "test").isPresent());

            Calendar calendar = gson.fromJson("{\n" +
                    "    \"id\": \"bfcb7f4e-4eed-4cb8-8394-8030db83d3cc\",\n" +
                    "    \"kanbanIds\": [\n" +
                    "      \"Board 1\"\n" +
                    "    ],\n" +
                    "    \"orphanCardIds\": [\n" +
                    "      \"93f92c83-1788-4e97-ad72-83a049fd57a4\",\n" +
                    "      \"72d8042f-71ac-471e-b13f-c04e55fceba1\",\n" +
                    "      \"1947e2cd-6ae1-495a-9379-7e15b7443446\"\n" +
                    "    ],\n" +
                    "    \"memberIds\": [\n" +
                    "      \"f3803917-c90d-4046-9ced-75395e110a8a\"\n" +
                    "    ],\n" +
                    "    \"workspaceId\": \"6e3b4452-c71a-425b-9609-b2f5097f8ab8\"\n" +
                    "  }", Calendar.class);
            ArrayList<Calendar> calendarArrayList = new ArrayList<>();
            calendarArrayList.add(calendar);
            System.out.println("POST calendar: " + requestManager.postCalendars(loggedInUser.get().getAuthToken(), "test", calendarArrayList));

            System.out.println("GET calendar: " + requestManager.getCalendars(loggedInUser.get().getAuthToken(), "test", "1").isPresent());

            KanbanBoard board = gson.fromJson("{\n" +
                    "    \"id\": \"Board 1\",\n" +
                    "    \"title\": \"desc\",\n" +
                    "    \"createTime\": \"Apr 29, 2024, 7:48:57 PM\",\n" +
                    "    \"lastModifiedTime\": \"Apr 29, 2024, 7:48:57 PM\",\n" +
                    "    \"calendarId\": \"bfcb7f4e-4eed-4cb8-8394-8030db83d3cc\",\n" +
                    "    \"itemIds\": {\n" +
                    "      \"Column 1\": [\n" +
                    "        \"53f97d5d-aa79-4f62-8369-09472610a483\",\n" +
                    "        \"fadcb850-49f6-4276-a82c-9bd6656bc3f7\",\n" +
                    "        \"e39751ac-1cd5-4891-84dd-f85b1a2e1f3d\"\n" +
                    "      ]\n" +
                    "    },\n" +
                    "    \"startTime\": \"Apr 25, 2024, 1:17:33 PM\",\n" +
                    "    \"endTime\": \"May 2, 2024, 11:42:27 AM\"\n" +
                    "  }", KanbanBoard.class);
            ArrayList<KanbanBoard> boards = new ArrayList<>();
            boards.add(board);

            System.out.println("POST board: " + requestManager.postBoards(loggedInUser.get().getAuthToken(), "test", "bfcb7f4e-4eed-4cb8-8394-8030db83d3cc", boards));
            System.out.println("GET board: " + requestManager.getBoards(loggedInUser.get().getAuthToken(), "test", "bfcb7f4e-4eed-4cb8-8394-8030db83d3cc", "Board 1").isPresent());

            Card card = gson.fromJson("{\n" +
                    "    \"id\": \"53f97d5d-aa79-4f62-8369-09472610a483\",\n" +
                    "    \"title\": \"Card 4\",\n" +
                    "    \"description\": \"Desc\",\n" +
                    "    \"startTime\": \"Apr 24, 2024, 4:43:47 AM\",\n" +
                    "    \"endTime\": \"May 4, 2024, 6:53:04 PM\",\n" +
                    "    \"creationTime\": \"Apr 29, 2024, 7:48:57 PM\",\n" +
                    "    \"lastModifyTime\": \"Apr 29, 2024, 7:48:57 PM\"\n" +
                    "  }", Card.class);
            ArrayList<Card> cards = new ArrayList<>();
            cards.add(card);

            System.out.println("POST kanban card: " + requestManager.postKanbanCards(loggedInUser.get().getAuthToken(), "test", "bfcb7f4e-4eed-4cb8-8394-8030db83d3cc", "Board 1", "Column 1", cards));
            System.out.println("GET kanban card: " + requestManager.getKanbanCards(loggedInUser.get().getAuthToken(), "test", "bfcb7f4e-4eed-4cb8-8394-8030db83d3cc", "Board 1", "53f97d5d-aa79-4f62-8369-09472610a483").isPresent());

            Card orphan = gson.fromJson("{\n" +
                    "    \"id\": \"72d8042f-71ac-471e-b13f-c04e55fceba1\",\n" +
                    "    \"title\": \"Card 5\",\n" +
                    "    \"description\": \"Desc\",\n" +
                    "    \"startTime\": \"Apr 22, 2024, 10:46:39 PM\",\n" +
                    "    \"endTime\": \"May 2, 2024, 8:42:53 AM\",\n" +
                    "    \"creationTime\": \"Apr 29, 2024, 7:48:57 PM\",\n" +
                    "    \"lastModifyTime\": \"Apr 29, 2024, 7:48:57 PM\"\n" +
                    "  }", Card.class);

            ArrayList<Card> orphans = new ArrayList<>();
            orphans.add(orphan);
            System.out.println("POST orphan card: " + requestManager.postOrphanCards(loggedInUser.get().getAuthToken(), "test", "bfcb7f4e-4eed-4cb8-8394-8030db83d3cc", orphans));
            System.out.println("GET orphan card: " + requestManager.getOrphanCards(loggedInUser.get().getAuthToken(), "test", "bfcb7f4e-4eed-4cb8-8394-8030db83d3cc", "72d8042f-71ac-471e-b13f-c04e55fceba1").isPresent());
            System.out.println("Logout: " + requestManager.makeLogoutRequest(loggedInUser.get()));
        }
    }
}
