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
import server.ObjectManager;
import server.UserManager;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
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
        // Parse json

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

        if(!rootElement.isJsonObject() || hasBadGetRequestStructure(rootElement.getAsJsonObject())) {
            response.setStatus(400); // Bad request
            response.write(true, StandardCharsets.UTF_8.encode("Bad request structure"), callback);
            return;
        }

        JsonObject rootObject = rootElement.getAsJsonObject();

        String calendarId = rootObject.get("calendar-id").getAsString();
        String workspaceId = rootObject.get("workspace-id").getAsString();
        ArrayList<String> cardIds = rootObject.get("card-ids").getAsJsonArray().asList().stream()
                .map(JsonElement::getAsString)
                .collect(Collectors.toCollection(ArrayList::new));

        ArrayList<Card> requestedCards;
        switch (rootObject.get("type").getAsString()) {
            case "orphan-card" -> {
                requestedCards = ObjectManager.getCalendars().stream()
                        .map(Calendar::getOrphanCards)
                        .flatMap(ArrayList::stream)
                        .filter(card -> cardIds.contains(card.getId()))
                        .collect(Collectors.toCollection(ArrayList::new));
            }
            case "board-card" -> {
                requestedCards = ObjectManager.getCalendars().stream()
                        .map(Calendar::getKanbanBoards)
                        .flatMap(ArrayList::stream)
                        .map(KanbanBoard::getItems)
                        .flatMap(ArrayList::stream)
                        .filter(card -> cardIds.contains(card.getId()))
                        .collect(Collectors.toCollection(ArrayList::new));
            }
            default -> {
                response.setStatus(400);
                response.write(true, StandardCharsets.UTF_8.encode("Bad request structure"), callback);
                return;
            }
        }

        if(requestedCards.isEmpty()) {
            response.setStatus(404); // Not found
            response.write(true, StandardCharsets.UTF_8.encode("Card not found"), callback);
            return;
        }

        response.setStatus(200); // OK
        response.write(true, StandardCharsets.UTF_8.encode(gson.toJson(requestedCards)), callback);
    }

    private boolean hasBadGetRequestStructure(JsonObject obj) {
        /*
        GET REQUEST STRUCTURE:
        {
            "type": "board-card"/"orphan-card",
            "workspace-id": {workspaceId},
            "calendar-id": {calendarId},
            "card-ids" : []
        }
        */
        return !(obj.has("type") && obj.has("workspace-id")
                && obj.has("calendar-id") && obj.has("card-ids")
                && obj.get("type").isJsonPrimitive() && obj.get("workspace-id").isJsonPrimitive()
                && obj.get("calendar-id").isJsonPrimitive()) && obj.get("card-ids").isJsonArray();
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
