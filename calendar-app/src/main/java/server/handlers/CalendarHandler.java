package server.handlers;

import client.backend.models.Calendar;
import client.backend.models.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class CalendarHandler extends Handler.Abstract {

    private static final Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();

    @Override
    public boolean handle(Request request, Response response, Callback callback) throws Exception {

        switch(request.getMethod()) {
            case "GET" -> {
                synchronizeClient(request, response, callback);
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

    private void synchronizeClient(Request request, Response response, Callback callback) throws Exception {
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
            response.setStatus(400); // Bad request
            response.write(true, StandardCharsets.UTF_8.encode("No \"id\" or \"ids\" parameter or provided both at the same time"), callback);
            return;
        }

        // Get authToken and load user
        String authToken = request.getHeaders().get(HttpHeader.AUTHORIZATION);

        Optional<User> goodUser = UserManager.findUserWithAuthToken(authToken);

        if (goodUser.isEmpty()) {
            response.setStatus(401);
            response.write(true, StandardCharsets.UTF_8.encode("Bad auth data"), callback);
            return;
        }

        ArrayList<String> calendarIds = new ArrayList<>();
        if(parameterNames.contains("id")) {
            calendarIds.add(Request.getParameters(request).get("id").getValue());
        } else {
            String ids = Request.getParameters(request).get("ids").getValue();
            calendarIds.addAll(List.of(ids.split(",")));
        }

        ArrayList<Calendar> calendars = ObjectManager.getCalendars().stream()
                .filter(calendar -> calendarIds.contains(calendar.getID()) &&
                        calendar.getMemberIds().contains(goodUser.get().getId()))
                .collect(Collectors.toCollection(ArrayList::new));

        response.write(true, StandardCharsets.UTF_8.encode(gson.toJson(calendars)), callback);
        response.setStatus(200); // OK
    }

    private void synchronizeServer(Request request, Response response, Callback callback) {
        /*  Try to read data
            it should have:
            - an AUTHORIZATION header with auth-token
            - calendar.json
            - "workspace-id" parameter
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

        Fields parameters;
        try {
            parameters = Request.getParameters(request);
        } catch (Exception e) {
            response.setStatus(500); // Server error
            return;
        }

        if (!parameters.getNames().contains("workspace-id")) {
            response.setStatus(400); // Bad request
            response.write(true, StandardCharsets.UTF_8.encode("No workspace-id parameter"), callback);
            return;
        }

        String workspaceId = parameters.getValue("workspace-id");

        Optional<User> authorizedUser = UserManager.findUserWithAuthToken(authToken);

        if (authorizedUser.isEmpty()) {
            response.setStatus(401); // Unauthorized, no such user in token cache
            return;
        }

        // Parse calendar part
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

        Calendar calendar = gson.fromJson(requestJson, Calendar.class);

        if(!calendar.getMemberIds().contains(authorizedUser.get().getId())) {
            response.setStatus(401); // Unauthorized
            return;
        }

        try {
            ServerJsonManager.writeCalendarData(calendar, workspaceId);
        } catch(IOException e) {
            e.printStackTrace();
            response.setStatus(500); // Server error
            return;
        }

        response.setStatus(200); // OK
    }
}
