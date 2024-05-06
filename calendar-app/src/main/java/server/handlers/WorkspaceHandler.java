package server.handlers;

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
import java.util.*;
import java.util.stream.Collectors;


public class WorkspaceHandler extends Handler.Abstract {

    private static final Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();

    @Override
    public boolean handle(Request request, Response response, Callback callback) {

        // Check auth
        if (!request.getHeaders().contains(HttpHeader.AUTHORIZATION)) {
            response.setStatus(401); // Unauthorized
            response.write(true, StandardCharsets.UTF_8.encode("No authorization header"), callback);
            return true;
        }

        String authToken = request.getHeaders().get(HttpHeader.AUTHORIZATION);

        Optional<User> goodUser = UserManager.findUserWithAuthToken(authToken);

        if (goodUser.isEmpty()) {
            response.setStatus(401); // Unauthorized
            response.write(true, StandardCharsets.UTF_8.encode("Bad auth data"), callback);
            return true;
        }

        switch (request.getMethod()) {
            // Only sends workspace.json
            case "GET" -> handleGetRequest(request, response, callback);
            case "POST" -> handlePostRequest(request, response, callback);
            default -> {
                response.setStatus(400); // Bad request
                response.write(true, StandardCharsets.UTF_8.encode("Unsupported HTTP method, use GET or POST"), callback);
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
            response.write(true, StandardCharsets.UTF_8.encode("Couldn't read parameters"), callback);
            return;
        }

        List<String> workspaceIds = Arrays.stream(parameters.getValue("workspace-ids").replaceAll("/", "").split(",")).toList();

        ArrayList<Workspace> requestedWorkspaces = new ArrayList<>();
        for(String workspaceId : workspaceIds) {
            try {
                requestedWorkspaces.add(ServerJsonManager.readWorkspaceData(workspaceId));
            } catch(IOException e) {
                response.setStatus(404);
                response.write(true, StandardCharsets.UTF_8.encode("Couldn't find workspaces"), callback);
                return;
            }
        }

        if(requestedWorkspaces.isEmpty()) {
            response.setStatus(404);
            response.write(true, StandardCharsets.UTF_8.encode("Couldn't find workspaces"), callback);
            return;
        }

        response.setStatus(200);
        response.write(true, StandardCharsets.UTF_8.encode(gson.toJson(requestedWorkspaces)), callback);
    }

    private boolean hasBadGetRequestStructure(Request request) {
        /*
        WORKSPACE GET REQUEST STRUCTURE
        workspace-ids={},{},{}
         */

        Fields parameters;
        try {
            parameters = Request.getParameters(request);
        } catch(Exception e) {
            return true;
        }

        return !(parameters.getNames().contains("workspace-ids"));
    }

    private void handlePostRequest(Request request, Response response, Callback callback) {


        // Parse workspace part
        String requestJson;
        try {
            requestJson = Content.Source.asString(request);
            if (requestJson == null) {
                response.setStatus(500); // Bad request
                response.write(true, StandardCharsets.UTF_8.encode("Couldn't read request content"), callback);
                return;
            }
        } catch (IOException e) {
            e.printStackTrace();
            response.setStatus(400); // Bad request
            response.write(true, StandardCharsets.UTF_8.encode("Couldn't read request content"), callback);
            return;
        }

        JsonElement requestElement = JsonParser.parseString(requestJson);

        if(!requestElement.isJsonObject() && hasBadPostRequestStructure(requestElement.getAsJsonObject())) {
            response.setStatus(400); // Bad request
            response.write(true, StandardCharsets.UTF_8.encode("Bad request content"), callback);
            return;
        }
        JsonObject jsonObject = requestElement.getAsJsonObject();
        Type workspaceArrayType = new TypeToken<ArrayList<Workspace>>(){}.getType();
        ArrayList<Workspace> workspacesToAdd = gson.fromJson(jsonObject.get("workspaces"), workspaceArrayType);

        if(workspacesToAdd.isEmpty()) {
            response.setStatus(400);
            response.write(true, StandardCharsets.UTF_8.encode("Empty workspaces field"), callback);
        }

        try {
            ObjectManager.addToWorkspaces(workspacesToAdd);
        } catch(IOException e) {
            response.setStatus(500);
            response.write(true, StandardCharsets.UTF_8.encode("Couldn't write workspaces"), callback);
            return;
        }

        response.setStatus(200);
    }

    private boolean hasBadPostRequestStructure(JsonObject obj) {
        /*
        WORKSPACE POST REQUEST STRUCTURE
        {
            "workspaces": [
            {},{},{}
            ]
        }
         */

        return !(obj.has("workspaces") && obj.get("workspaces").isJsonArray());
    }
}