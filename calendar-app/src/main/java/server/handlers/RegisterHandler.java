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
import java.util.Optional;

public class RegisterHandler extends Handler.Abstract {

    Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
    UserManager manager = new UserManager();

    @Override
    public boolean handle(Request request, Response response, Callback callback) throws Exception {
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

        Optional<User> registeredUser = manager.registerUser(requestUser.getUsername(), requestUser.getPasswordHash(), requestUser.getEmail());

        if (registeredUser.isEmpty()) {
            response.setStatus(409); // Conflict, user with given username exists
            callback.succeeded();
            return true;
        }

        String registeredUserJson = gson.toJson(registeredUser.get());
        response.setStatus(200); // OK, user registered

        response.getHeaders().add(HttpHeader.AUTHORIZATION, registeredUser.get().getAuthToken());
        response.write(true, StandardCharsets.UTF_8.encode(registeredUserJson), callback);
        callback.succeeded();
        return true;
    }
}
