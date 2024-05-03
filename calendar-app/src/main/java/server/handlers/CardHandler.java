package server.handlers;

import client.backend.models.Calendar;
import client.backend.models.Card;
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
import server.UserManager;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Optional;

public class CardHandler extends Handler.Abstract {

    private static final Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().create();

    @Override
    public boolean handle(Request request, Response response, Callback callback) {
        switch (request.getMethod()) {
            case "GET" -> {
                handleGetRequest(request, response, callback);
                return true;
            }

            case "POST" -> {

            }

            default -> {
                System.out.println("Don't know how or why, but it got to default. CardHandler->handle");
                response.setStatus(400); // Bad request
                response.write(true, StandardCharsets.UTF_8.encode("Unsupported method, try GET/POST"), callback);
            }
        }
        callback.succeeded();
        return true;
    }

    private void handleGetRequest(Request request, Response response, Callback callback) {
        if(!request.getHeaders().contains(HttpHeader.AUTHORIZATION)) {
            response.setStatus(401); // Unauthorized
            response.write(true, StandardCharsets.UTF_8.encode("No authorization header"), callback);
            return;
        }

        String authToken = request.getHeaders().get(HttpHeader.AUTHORIZATION);

        Optional<User> loggedInUser = UserManager.findUserWithAuthToken(authToken);

        if(loggedInUser.isEmpty()) {
            response.setStatus(401); // Unauthorized
            response.write(true, StandardCharsets.UTF_8.encode("Bad auth token"), callback);
            return;
        }

        // Parse json

        /*
        REQUEST STRUCTURE:
        {
            "type": "board-card"/"orphan-card",
            "workspace-id": {workspaceId},
            "calendar-id": {calendarId},
            "cards": {serialized ArrayList<Card>}
        }
        */
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

        if(!rootElement.isJsonObject() || hasBadRequestStructure(rootElement.getAsJsonObject())) {
            response.setStatus(400); // Bad request
            response.write(true, StandardCharsets.UTF_8.encode("Bad request structure"), callback);
            return;
        }

        JsonObject rootObject = rootElement.getAsJsonObject();

        String calendarId = rootObject.get("calendar-id").getAsString();
        String workspaceId = rootObject.get("workspace-id").getAsString();
        Type cardArrayType = new TypeToken<ArrayList<Card>>() {}.getType();
        ArrayList<Card> cards = gson.fromJson(rootObject.get("cards"), cardArrayType);

        switch (rootObject.get("type").getAsString()) {
            case "orphan" -> {
                Optional<Calendar> calendar = ObjectManager.getCalendars().stream()
                        .filter(cal -> cal.getID().equals(calendarId))
                        .findFirst();

                if(calendar.isEmpty()) {
                    response.setStatus(404); // Not found
                    response.write(true, StandardCharsets.UTF_8.encode("No calendar found"), callback);
                    return;
                }

                // TODO THIS ISN'T FINISHED IM JUST TOO TIRED
            }
        }

    }

    private boolean hasBadRequestStructure(JsonObject obj) {
        return !(obj.has("type") && obj.has("workspace-id")
                && obj.has("calendar-id") && obj.has("cards")
                && obj.get("type").isJsonPrimitive() && obj.get("workspace-id").isJsonPrimitive()
                && obj.get("calendar-id").isJsonPrimitive() && obj.get("cards").isJsonObject());
    }
}
