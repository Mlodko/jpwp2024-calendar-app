package server.handlers;

import client.backend.models.Calendar;
import client.backend.models.User;
import client.backend.models.Workspace;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


public class WorkspaceHandler extends Handler.Abstract {

    private static final Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();

    @Override
    public boolean handle(Request request, Response response, Callback callback) throws Exception {
        switch (request.getMethod()) {
            // Only sends workspace.json
            case "GET" -> {
                synchronizeClientToServer(request, response, callback);
            }
            case "POST" -> {
                synchronizeServerToClient(request, response, callback);
            }
            default -> {
                response.setStatus(400); // Bad request
                response.write(true, StandardCharsets.UTF_8.encode("Unsupported HTTP method, use GET or POST"), callback);
            }
        }
        callback.succeeded();
        return true;
    }

    private void synchronizeClientToServer(Request request, Response response, Callback callback) throws Exception {
        // Request data should have workspace id and auth header with token
        Set<String> parameterNames = Request.getParameters(request).getNames();

        // No auth header
        if (!request.getHeaders().contains(HttpHeader.AUTHORIZATION)) {
            response.setStatus(401); // Unauthorized
            response.write(true, StandardCharsets.UTF_8.encode("No authorization header"), callback);
            return;
        }

        // If no id given or two fields at the same time given
        if (parameterNames.contains("id") == parameterNames.contains("ids")) {
            //                           ^^^
            //                          This is a XOR you dumbfucks
            response.setStatus(400); // Bad request
            response.write(true, StandardCharsets.UTF_8.encode("No \"id\" or \"ids\" parameter or provided both at the same time"), callback);
            return;
        }

        // Get authToken and load user
        String authToken = request.getHeaders().get(HttpHeader.AUTHORIZATION);

        Optional<User> goodUser = UserManager.findUserWithAuthToken(authToken);

        if (goodUser.isEmpty()) {
            response.setStatus(401); // Unauthorized
            response.write(true, StandardCharsets.UTF_8.encode("Bad auth data"), callback);
            return;
        }

        // Get id/ids
        // Check if user in workspace(s) specified by id/ids
        ArrayList<String> workspaceIds = new ArrayList<>();

        if (parameterNames.contains("id")) {
            workspaceIds.add(Request.getParameters(request).get("id").getValue());
        } else {
            String ids = Request.getParameters(request).get("ids").getValue();
            // Ids in format "id,id,id"
            workspaceIds.addAll(Arrays.stream(ids.split(",")).toList());
        }

        ArrayList<Workspace> workspaces = ObjectManager.getWorkspaces().stream()
                .filter(workspace -> workspaceIds.contains(workspace.getId()))
                .collect(Collectors.toCollection(ArrayList::new));
        if(workspaces.isEmpty()) {
            response.setStatus(404); // Not found
            response.write(true, StandardCharsets.UTF_8.encode("Workspace not found"), callback);
            return;
        }
        if(workspaces.stream().anyMatch(workspace -> workspace.getMemberIds().contains(goodUser.get().getId()))) {
            response.setStatus(403); // Forbidden
            response.write(true, StandardCharsets.UTF_8.encode("Not authorized to access resource"), callback);
            return;
        }

        // If authorized send
        String responseJson = gson.toJson(workspaces);
        response.setStatus(200); // OK
        response.write(true, StandardCharsets.UTF_8.encode(responseJson), callback);
    }

    private void synchronizeServerToClient(Request request, Response response, Callback callback) {
        /*  Try to read data
            it should have:
            - an AUTHORIZATION header with auth-token
            - workspace.json
        */

        // Check user authorization
        if (!request.getHeaders().contains(HttpHeader.AUTHORIZATION)) {
            response.setStatus(401); // Unauthorized, no authorization provided
            response.write(true, StandardCharsets.UTF_8.encode("No authorization header"), callback);

            return;
        }

        String authToken = request.getHeaders().get(HttpHeader.AUTHORIZATION);
        if (!UserManager.ifAuthTokenInLoggedInUsers(authToken)) {
            response.setStatus(401); // Unauthorized
            response.write(true, StandardCharsets.UTF_8.encode("Bad auth data"), callback);

            return;
        }

        Optional<User> authorizedUser = UserManager.findUserWithAuthToken(authToken);

        if (authorizedUser.isEmpty()) {
            response.setStatus(401); // Unauthorized
            response.write(true, StandardCharsets.UTF_8.encode("Bad auth data"), callback);
            return;
        }

        // Parse workspace part
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

        Workspace workspace = gson.fromJson(requestJson, Workspace.class);

        if (!workspace.getMemberIds().contains(authorizedUser.get().getId())) {
            response.setStatus(403); // Forbidden, user not in workspace
            response.write(true, StandardCharsets.UTF_8.encode("Not authorized to access resource"), callback);
            return;
        }

        try {
            ServerJsonManager.writeWorkspaceData(workspace);
        } catch (IOException e) {
            e.printStackTrace();
            response.setStatus(500); // Server error
            response.write(true, StandardCharsets.UTF_8.encode("Couldn't write workspace data"), callback);
            return;
        }

        response.setStatus(200); // OK
    }
}