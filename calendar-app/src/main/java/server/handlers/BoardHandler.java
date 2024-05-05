package server.handlers;

import client.backend.models.KanbanBoard;
import client.backend.models.User;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import org.eclipse.jetty.http.HttpHeader;
import org.eclipse.jetty.io.Content;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Response;
import org.eclipse.jetty.util.Callback;
import server.ObjectManager;
import server.ServerJsonManager;
import server.UserManager;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
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
                synchronizeServer(request, response, callback);
            }

            default -> {
                response.setStatus(400); // Bad request
            }
        }
        callback.succeeded();
        return true;
    }

    private void handleGetRequest(Request request, Response response, Callback callback) throws Exception {
        /*
        // Request data should have workspace id and auth header with token
        Set<String> parameterNames = Request.getParameters(request).getNames();



        // If no id given or two fields at the same time given
        if (!(parameterNames.contains("id") && parameterNames.contains("ids"))) {
            response.setStatus(400); // Bad request
            response.write(true, StandardCharsets.UTF_8.encode("No \"id\" or \"ids\" parameter or provided both at the same time"), callback);
            return;
        }



        ArrayList<String> boardIds = new ArrayList<>();
        if(parameterNames.contains("id")){
            boardIds.add(Request.getParameters(request).get("id").getValue());
        } else {
            String ids = Request.getParameters(request).get("ids").getValue();
            boardIds.addAll(List.of(ids.split(",")));
        }


        ArrayList<KanbanBoard> boards = ObjectManager.getKanbanBoards().stream()
                .filter(board -> boardIds.contains(board.getId()))
                .collect(Collectors.toCollection(ArrayList::new));
        response.write(true, StandardCharsets.UTF_8.encode(gson.toJson(boards)), callback);
        */
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

        String workspaceId = rootObject.get("workspace-id").getAsString();
        String calendarId = rootObject.get("calendar-id").getAsString();
        ArrayList<String> boardIds = rootObject.get("board-ids").getAsJsonArray().asList().stream()
                .map(JsonElement::getAsString)
                .collect(Collectors.toCollection(ArrayList::new));

        // Get requested boards
//        ArrayList<KanbanBoard> = ObjectManager.getWorkspaces().stream()

    }

    private boolean hasBadGetRequestStructure(JsonObject obj) {
        /*
        GET REQUEST STRUCTURE
        {
            "workspace-id": {workspaceId},
            "calendar-id": {calendarId},
            "board-ids": []
        }
         */
        return !(obj.has("workspace-id") && obj.has("calendar-id") && obj.has("board-ids")
            && obj.get("workspace-id").isJsonPrimitive() && obj.get("calendar-id").isJsonPrimitive() && obj.get("board-ids").isJsonArray());
    }

    private void synchronizeServer(Request request, Response response, Callback callback) throws Exception {
        /*
            Try to read data
            it should have:
            - an AUTHORIZATION header with auth-token
            - boards.json
            - workspace id
        */

        // Check user authorization
        if (!(request.getHeaders().contains(HttpHeader.AUTHORIZATION) && Request.getParameters(request).getNames().contains("workspace-id")
                && Request.getParameters(request).getNames().contains("calendar-id"))) {
            response.setStatus(401); // Unauthorized, no authorization provided
            return;
        }

        String authToken = request.getHeaders().get(HttpHeader.AUTHORIZATION);

        if (!UserManager.ifAuthTokenInLoggedInUsers(authToken)) {
            response.setStatus(401); // Unauthorized
            return;
        }

        Optional<User> authorizedUser = UserManager.findUserWithAuthToken(authToken);

        if (authorizedUser.isEmpty()) {
            response.setStatus(401); // Unauthorized, no such user in token cache
            return;
        }

        // Parse board part
        String requestJson;

        try {
            requestJson = Content.Source.asString(request);
            if (requestJson == null) {
                throw new IOException();
            }
        } catch (IOException e) {
            e.printStackTrace();
            response.setStatus(400); // Bad request
            return;
        }

        Type boardArrayType = new TypeToken<ArrayList<KanbanBoard>>(){}.getType();
        ArrayList<KanbanBoard> boards = gson.fromJson(requestJson, boardArrayType);

        // Get parameters
        String workspaceId = Request.getParameters(request).getValue("workspace-id");
        String calendarId = Request.getParameters(request).getValue("calendar-id");

        try {
            ServerJsonManager.writeKanbanBoards(boards, workspaceId, calendarId);
        } catch (IOException e) {
            response.setStatus(500);
        }
    }
}
