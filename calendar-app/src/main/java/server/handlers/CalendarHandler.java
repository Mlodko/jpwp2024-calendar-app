package server.handlers;

import client.backend.models.Calendar;
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

public class CalendarHandler extends Handler.Abstract {

    private static final Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();

    @Override
    public boolean handle(Request request, Response response, Callback callback) throws Exception {

        // Check auth
        if(!request.getHeaders().contains(HttpHeader.AUTHORIZATION)) {
            response.setStatus(401);
            response.write(true, StandardCharsets.UTF_8.encode("No auth header"), callback);
            return true;
        }
        String authToken = request.getHeaders().get(HttpHeader.AUTHORIZATION);
        Optional<User> requestingUser = UserManager.findUserWithAuthToken(authToken);

        if(requestingUser.isEmpty()) {
            response.setStatus(401);
            response.write(true, StandardCharsets.UTF_8.encode("User not found"), callback);
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

    private void handleGetRequest(Request request, Response response, Callback callback) throws Exception {
        if(hadBadGetRequestStructure(request)) {
            response.setStatus(400);
            response.write(true, StandardCharsets.UTF_8.encode("Bad request parameters"), callback);
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

        String workspaceId = parameters.getValue("workspace-id");
        List<String> calendarIds = Arrays.stream(parameters.getValue("calendar-ids").replaceAll("/", "").split(",")).toList();

        ArrayList<Calendar> requestedCalendars = new ArrayList<>();
        for(String calendarId : calendarIds) {
            requestedCalendars.add(ServerJsonManager.readCalendarStructureData(calendarId, workspaceId).get());
        }

        if(requestedCalendars.isEmpty()) {
            response.setStatus(404);
            response.write(true, StandardCharsets.UTF_8.encode("Couldn't find specified calendars"), callback);
            return;
        }

        response.setStatus(200);
        response.write(true, StandardCharsets.UTF_8.encode(gson.toJson(requestedCalendars)), callback);
    }

    private boolean hadBadGetRequestStructure(Request request) {
        /*
        CALENDAR GET PARAMETER STRUCTURE:
        workspace-id={}
        calendar-ids={},{},{},...
         */

        Fields parameters;
        try {
            parameters = Request.getParameters(request);
        } catch(Exception e) {
            return true;
        }

        return !(parameters.getNames().contains("workspace-id") && parameters.getNames().contains("calendar-ids"));
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
        Type calendarArrayType = new TypeToken<ArrayList<Calendar>>(){}.getType();
        ArrayList<Calendar> calendarsToAdd = gson.fromJson(rootObject.get("calendars"), calendarArrayType);

        try {
            Workspace workspace = ServerJsonManager.readWorkspaceData(workspaceId);
            workspace.addToCalendars(calendarsToAdd);
            ServerJsonManager.writeWorkspaceData(workspace);
            for(Calendar calendar : calendarsToAdd) {
                ServerJsonManager.writeCalendarData(calendar, workspaceId);
            }
        } catch(Exception e) {
            response.setStatus(500);
            response.write(true, StandardCharsets.UTF_8.encode("Couldn't write workspace"), callback);
            return;
        }

        response.setStatus(200);
    }

    private boolean hasBadPostRequestStructure(JsonObject obj) {
        /*
        CALENDAR JSON POST STRUCTURE
        {
        "workspace-id": {},
        "calendars": {serialized ArrayList<Calendar>}
        }
         */

        return !(obj.has("workspace-id") && obj.has("calendars")
        && obj.get("workspace-id").isJsonPrimitive() && obj.get("calendars").isJsonArray());
    }
}