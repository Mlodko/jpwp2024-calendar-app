package server.handlers;

import client.backend.models.Calendar;
import client.backend.models.KanbanBoard;
import client.backend.models.User;
import client.backend.models.Workspace;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import org.eclipse.jetty.http.HttpHeader;
import org.eclipse.jetty.io.Content;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Response;
import org.eclipse.jetty.util.Callback;
import org.eclipse.jetty.util.Fields;
import server.ObjectManager;
import server.ServerJsonManager;
import server.UserManager;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class BoardHandler extends Handler.Abstract {

    private static final Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();

    @Override
    public boolean handle(Request request, Response response, Callback callback) throws Exception {

        // No auth header
        if (!request.getHeaders().contains(HttpHeader.AUTHORIZATION)) {
            response.setStatus(401); // Unauthorized
            response.write(true, StandardCharsets.UTF_8.encode("No authorization header"), callback);
            callback.succeeded();
            return true;
        }

        // Get authToken and load user
        String authToken = request.getHeaders().get(HttpHeader.AUTHORIZATION);

        Optional<User> goodUser = UserManager.findUserWithAuthToken(authToken);

        if (goodUser.isEmpty()) {
            response.setStatus(401);
            response.write(true, StandardCharsets.UTF_8.encode("Bad auth data"), callback);
            callback.succeeded();
            return true;
        }

        switch(request.getMethod()) {
            case "GET" -> {
                handleGetRequest(request, response, callback);
            }

            case "POST" -> {
                handlePostRequest(request, response, callback);
            }

            default -> {
                response.setStatus(400); // Bad request
            }
        }
        callback.succeeded();
        return true;
    }

    private void handleGetRequest(Request request, Response response, Callback callback) {
        if(hasBadGetRequestStructure(request)) {
            response.setStatus(400);
            response.write(true, StandardCharsets.UTF_8.encode("Bad request structure"), callback);
            return;
        }
        Fields parameters;
        try {
            parameters = Request.getParameters(request);
        } catch(Exception e) {
            response.setStatus(500);
            response.write(true, StandardCharsets.UTF_8.encode("Couldn't parse parameters"), callback);
            return;
        }
        String workspaceId = parameters.getValue("workspace-id");
        String calendarId = parameters.getValue("calendar-id");
        List<String> boardIds = Arrays.stream(parameters.getValue("board-ids").replaceAll("/", "")
                .split(",")).toList();

        ObjectManager.refreshWorkspaces();
        ArrayList<KanbanBoard> requestedBoards = ObjectManager.getWorkspaces().stream()
                .filter(workspace -> workspace.getId().equals(workspaceId))
                .map(Workspace::getCalendars)
                .flatMap(ArrayList::stream)
                .filter(calendar -> calendar.getID().equals(calendarId))
                .map(Calendar::getKanbanBoards)
                .flatMap(ArrayList::stream)
                .filter(board -> boardIds.contains(board.getId()))
                .collect(Collectors.toCollection(ArrayList::new));

        if(requestedBoards.isEmpty()) {
            response.setStatus(404);
            response.write(true, StandardCharsets.UTF_8.encode("No boards found"), callback);
            return;
        }

        response.setStatus(200);
        response.write(true, StandardCharsets.UTF_8.encode(gson.toJson(requestedBoards)), callback);
    }

    private boolean hasBadGetRequestStructure(Request request) {
        /*
        GET REQUEST PARAMETER STRUCTURE
        workspace-id={}
        calendar-id={}
        board-ids={},{},{},...
         */

        Fields parameters;
        try {
            parameters = Request.getParameters(request);
        } catch(Exception e) {
            return true;
        }

        return !(parameters.getNames().contains("workspace-id")
                && parameters.getNames().contains("calendar-id")
                && parameters.getNames().contains("board-ids"));
    }

    private void handlePostRequest(Request request, Response response, Callback callback) throws Exception {
        // Read json from request and check if it's well-structured
        String requestJson;
        try {
            requestJson = Content.Source.asString(request);
            if (requestJson == null) {
                throw new IOException();
            }
        } catch (IOException e) {
            e.printStackTrace();
            response.setStatus(400); // Bad request
            response.write(true, StandardCharsets.UTF_8.encode("Couldn't read request content"), callback);
            return;
        }

        JsonElement rootElement = JsonParser.parseString(requestJson);

        if(!rootElement.isJsonObject() || hasBadPostRequestStructure(rootElement.getAsJsonObject())) {
            response.setStatus(400); // Bad request
            response.write(true, StandardCharsets.UTF_8.encode("Bad request structure"), callback);
            return;
        }

        JsonObject rootObject = rootElement.getAsJsonObject();
        String workspaceId = rootObject.get("workspace-id").getAsString();
        String calendarId = rootObject.get("calendar-id").getAsString();
        Type boardArrayType = new TypeToken<ArrayList<KanbanBoard>>(){}.getType();
        ArrayList<KanbanBoard> boardsToAdd = gson.fromJson(rootObject.get("boards").getAsJsonArray(), boardArrayType);

        ObjectManager.refreshWorkspaces();
        Optional<Calendar> calendarToModify =ObjectManager.getWorkspaces().stream()
                .filter(workspace -> workspace.getId().equals(workspaceId))
                .map(Workspace::getCalendars)
                .flatMap(ArrayList::stream)
                .filter(calendar -> calendar.getID().equals(calendarId))
                .findFirst();

        if(calendarToModify.isEmpty()) {
            response.setStatus(404);
            response.write(true, StandardCharsets.UTF_8.encode("Couldn't find specified calendar"), callback);
            return;
        }

        calendarToModify.get().addToKanbanBoards(boardsToAdd);
        if(ObjectManager.writeWorkspaceFromCache(workspaceId)) {
            response.setStatus(200);
            return;
        }

        response.setStatus(500);
        response.write(true, StandardCharsets.UTF_8.encode("Couldn't write changes"), callback);
    }

    private boolean hasBadPostRequestStructure(JsonObject obj) {
        /*
        POST REQUEST STRUCTURE:
        {
        "workspace-id": {},
        "calendar-id": {},
        "boards": {serialized ArrayList<KanbanBoard>}
        }
         */

        return !(obj.has("workspace-id") && obj.has("calendar-id") && obj.has("boards")
        && obj.get("workspace-id").isJsonPrimitive() && obj.get("calendar-id").isJsonPrimitive() && obj.get("boards").isJsonArray());
    }
}
