package server.handlers;

import client.backend.models.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.eclipse.jetty.http.HttpHeader;
import org.eclipse.jetty.io.Content;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Response;
import org.eclipse.jetty.util.Callback;
import server.UserManager;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class LoginHandler extends Handler.Abstract {

    private final Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
    private final UserManager manager = new UserManager();


    @Override
    public boolean handle(Request request, Response response, Callback callback) {

        if (!request.getMethod().equals("POST")) {
            response.setStatus(400); // Bad request https://developer.mozilla.org/en-US/docs/Web/HTTP/Status/400
            callback.succeeded();
            return true;
        }

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
            callback.succeeded();
            return true;
        }

        User requestUser = gson.fromJson(requestJson, User.class);

        Optional<User> loggedInUser = manager.loginUser(requestUser.getUsername(), requestUser.getPasswordHash());

        if (loggedInUser.isEmpty()) {
            response.setStatus(401); // Unauthorized, bad credentials https://developer.mozilla.org/en-US/docs/Web/HTTP/Status/401
            callback.succeeded();
            return true;
        }

        String userJson = gson.toJson(loggedInUser.get());
        response.setStatus(200); // OK, successfully logged in

        response.getHeaders().add(HttpHeader.AUTHORIZATION, loggedInUser.get().getAuthToken());
        response.write(true, StandardCharsets.UTF_8.encode(userJson), callback);
        callback.succeeded();
        return true;
    }
}
