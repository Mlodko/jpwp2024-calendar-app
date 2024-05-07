package server.handlers;

import client.backend.models.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.eclipse.jetty.http.HttpFields;
import org.eclipse.jetty.http.HttpHeader;
import org.eclipse.jetty.io.Content;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Response;
import org.eclipse.jetty.util.Callback;
import org.eclipse.jetty.util.Fields;
import server.UserManager;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class LoginHandler extends Handler.Abstract {

    private static final Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
    private static final UserManager manager = new UserManager();

    @Override
    public boolean handle(Request request, Response response, Callback callback) {

        switch(request.getMethod()) {
            case "POST" -> handlePostRequest(request, response, callback);

            case "DELETE" -> handleDeleteRequest(request, response, callback);

            default -> {
                response.setStatus(400); // Bad request https://developer.mozilla.org/en-US/docs/Web/HTTP/Status/400
            }
        }

        callback.succeeded();
        return true;
    }

    public void handlePostRequest(Request request, Response response, Callback callback) {
        // Read data
        String requestJson = null;
        try {
            requestJson = Content.Source.asString(request);
            if (requestJson == null) {
                throw new IOException();
            }
        } catch (IOException e) {
            e.printStackTrace();
            response.setStatus(400); // Bad request https://developer.mozilla.org/en-US/docs/Web/HTTP/Status/400
            return;
        }

        User requestUser = gson.fromJson(requestJson, User.class);
        Optional<User> loggedInUser = manager.loginUser(requestUser.getUsername(), requestUser.getPasswordHash());

        if (loggedInUser.isEmpty()) {
            response.setStatus(401); // Unauthorized, bad credentials https://developer.mozilla.org/en-US/docs/Web/HTTP/Status/401
            return;
        }

        String userJson = gson.toJson(loggedInUser.get());
        response.setStatus(200); // OK, successfully logged in

        response.getHeaders().add(HttpHeader.AUTHORIZATION, loggedInUser.get().getAuthToken());
        response.write(true, StandardCharsets.UTF_8.encode(userJson), callback);
    }

    public void handleDeleteRequest(Request request, Response response, Callback callback) {
        HttpFields headers = request.getHeaders();

        if(!headers.contains(HttpHeader.AUTHORIZATION)) {
            response.setStatus(401);
            return;
        }

        String authToken = headers.get(HttpHeader.AUTHORIZATION);

        if(!UserManager.logoutUser(authToken)) {
            response.setStatus(404);
            return;
        }

        response.setStatus(200);
    }
}
