package server.handlers;

import client.backend.models.*;
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

public class CardHandler extends Handler.Abstract {

    private static final Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().create();

    @Override
    public boolean handle(Request request, Response response, Callback callback) {
        if(authorizeRequest(request, response, callback).isEmpty()) {
            response.setStatus(401); // Unauthorized
            response.write(true, StandardCharsets.UTF_8.encode("Bad auth token"), callback);
            callback.succeeded();
            return true;
        }

        switch (request.getMethod()) {
            case "GET" -> handleGetRequest(request, response, callback);

            case "POST" -> handlePostRequest(request, response, callback);

            default -> {
                response.setStatus(400); // Bad request
                response.write(true, StandardCharsets.UTF_8.encode("Unsupported method, try GET/POST"), callback);
            }
        }
        callback.succeeded();
        return true;
    }

    private void handleGetRequest(Request request, Response response, Callback callback) {
        if(hasBadGetRequestStructure(request)) {
            response.setStatus(400);
            response.write(true, StandardCharsets.UTF_8.encode("Bad parameter structure"), callback);
            return;
        }

        Fields parameters;
        try {
            parameters = Request.getParameters(request);
        } catch(Exception e) {
            response.setStatus(500);
            response.write(true, StandardCharsets.UTF_8.encode("Couldn't parse request parameters"), callback);
            return;
        }

        String workspaceId = parameters.getValue("workspace-id");
        String calendarId = parameters.getValue("calendar-id");
        List<String> cardIds = Arrays.stream(parameters.getValue("card-ids").replaceAll("/", "").split(",")).toList();

        ArrayList<Card> requestedCards;
        ObjectManager.refreshWorkspaces();
        if(parameters.getValue("type").equals("board-card")) {
            requestedCards = ObjectManager.getWorkspaces().stream()
                    .filter(workspace -> workspace.getId().equals(workspaceId))
                    .map(Workspace::getCalendars)
                    .flatMap(ArrayList::stream)
                    .filter(calendar -> calendar.getID().equals(calendarId))
                    .map(Calendar::getKanbanBoards)
                    .flatMap(ArrayList::stream)
                    .map(KanbanBoard::getItems)
                    .flatMap(ArrayList::stream)
                    .filter(card -> cardIds.contains(card.getId()))
                    .collect(Collectors.toCollection(ArrayList::new));
        } else {
            requestedCards = ObjectManager.getWorkspaces().stream()
                    .filter(workspace -> workspace.getId().equals(workspaceId))
                    .map(Workspace::getCalendars)
                    .flatMap(ArrayList::stream)
                    .filter(calendar -> calendar.getID().equals(calendarId))
                    .map(Calendar::getOrphanCards)
                    .flatMap(ArrayList::stream)
                    .filter(card -> cardIds.contains(card.getId()))
                    .collect(Collectors.toCollection(ArrayList::new));
        }

        if(requestedCards.isEmpty()) {
            response.setStatus(404);
            response.write(true, StandardCharsets.UTF_8.encode("No cards found"), callback);
            return;
        }

        response.setStatus(200);
        response.write(true, StandardCharsets.UTF_8.encode(gson.toJson(requestedCards)), callback);
    }

    private boolean hasBadGetRequestStructure(Request request) {
        /*
        GET REQUEST PARAMETER STRUCTURE:

        type="board-card"
        workspace-id={workspaceId}
        calendar-id={calendarId}
        board-id={boardId}
        card-ids={id},{id},{id},...

        OR

        type="orphan-card"
        workspace-id={workspaceId}
        calendar-id={calendarId}
        card-ids={id},{id},{id},...
        */

        Fields parameters;
        try {
            parameters = Request.getParameters(request);
        } catch (Exception e) {
            return true;
        }

        if(!(parameters.getNames().contains("type") && parameters.getNames().contains("workspace-id")
                && parameters.getNames().contains("calendar-id") && parameters.getNames().contains("card-ids"))) {
            return true;
        }

        if(!(parameters.get("type").getValue().equals("board-card") || parameters.get("type").getValue().equals("orphan-card"))) {
            return true;
        }

        return false;
    }

    private void handlePostRequest(Request request, Response response, Callback callback) {
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

        // Parse json
        JsonObject rootObject = rootElement.getAsJsonObject();
        String workspaceId = rootObject.get("workspace-id").getAsString();
        Type cardArrayType = new TypeToken<ArrayList<Card>>(){}.getType();
        ArrayList<Card> cardsToAdd = gson.fromJson(rootObject.get("cards").getAsJsonArray(), cardArrayType);

        // Add to ObjectManager

        Optional<Workspace> workspaceToModify = ObjectManager.getWorkspaces().stream()
                .filter(workspace -> workspace.getId().equals(workspaceId))
                .findFirst();

        if(workspaceToModify.isEmpty()) {
            response.setStatus(404); // Not found
            response.write(true, StandardCharsets.UTF_8.encode("Workspace not found"), callback);
            return;
        }

        switch(rootObject.get("type").getAsString()) {
            case "board-card" -> {
                String boardId = rootObject.get("board-id").getAsString();
                String columnName = rootObject.get("column-name").getAsString();

                Optional<KanbanBoard> boardToModify = workspaceToModify.get().getCalendars().stream()
                        .map(Calendar::getKanbanBoards)
                        .flatMap(ArrayList::stream)
                        .filter(board -> board.getId().equals(boardId))
                        .findFirst();

                if(boardToModify.isEmpty()) {
                    response.setStatus(404); // Not found
                    response.write(true, StandardCharsets.UTF_8.encode("Board not found"), callback);
                    return;
                }

                if(boardToModify.get().checkIfColumnExists(columnName)) {
                    boardToModify.get().addToItemsList(columnName, cardsToAdd);
                } else {
                    boardToModify.get().addNewItemColumn(columnName, cardsToAdd);
                }


                try {
                    ServerJsonManager.writeALLdata(workspaceToModify.get());
                } catch (IOException e) {
                    response.setStatus(500); // Server error
                    response.write(true, StandardCharsets.UTF_8.encode("Couldn't write JSON"), callback);
                    return;
                }
            }

            case "orphan-card" -> {
                String calendarId = rootObject.get("calendar-id").getAsString();

                Optional<Calendar> calendarToModify = workspaceToModify.get().getCalendars().stream()
                        .filter(calendar -> calendar.getID().equals(calendarId))
                        .findFirst();

                if(calendarToModify.isEmpty()) {
                    response.setStatus(404); // Not found
                    response.write(true, StandardCharsets.UTF_8.encode("Calendar not found"), callback);
                    return;
                }

                calendarToModify.get().addToOrphanCards(cardsToAdd);
                try {
                    ServerJsonManager.writeALLdata(workspaceToModify.get());
                } catch (IOException e) {
                    response.setStatus(500); // Server error
                    response.write(true, StandardCharsets.UTF_8.encode("Couldn't write JSON"), callback);
                    return;
                }
            }

            default -> {
                response.setStatus(400); // Bad request
                response.write(true, StandardCharsets.UTF_8.encode("Bad request structure"), callback);
                return;
            }
        }
        response.setStatus(200); // OK
    }

    private boolean hasBadPostRequestStructure(JsonObject obj) {
        /*
        POST REQUEST STRUCTURE:
        {
            "type": "board-card",
            "workspace-id": {workspaceId},
            "board-id": {boardId},
            "column-name": {columnName},
            "cards" : {serialized arraylist<Card>}
        }
        OR
        {
            "type": "orphan-card",
            "workspace-id": {workspaceId},
            "calendar-id": {calendarId},
            "cards" : {serialized arraylist<Card>}
        }
        */

        if(!(obj.has("type") && obj.has("cards") && obj.has("workspace-id")
        && obj.get("type").isJsonPrimitive() && obj.get("cards").isJsonArray() && obj.get("workspace-id").isJsonPrimitive())) {
            return false;
        }

        if(obj.get("type").getAsString().equals("board-card") && obj.has("board-id")
        && obj.has("column-name") && obj.get("board-id").isJsonPrimitive()
        && obj.get("column-name").isJsonPrimitive()) {
            return false;
        } else return !(obj.get("type").getAsString().equals("orphan-card") && obj.has("calendar-id")
                && obj.get("calendar-id").isJsonPrimitive());
    }

    private Optional<User> authorizeRequest(Request request, Response response, Callback callback) {
        if(!request.getHeaders().contains(HttpHeader.AUTHORIZATION)) {
            return Optional.empty();
        }
        String authToken = request.getHeaders().get(HttpHeader.AUTHORIZATION);

        return UserManager.findUserWithAuthToken(authToken);
    }
}
