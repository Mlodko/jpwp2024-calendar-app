package server.handlers;

import client.backend.models.Calendar;

import org.eclipse.jetty.io.Content;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Response;
import org.eclipse.jetty.util.Callback;
import server.ServerJsonManager;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Optional;

public class CalendarHandler extends Handler.Abstract {
    @Override
    public boolean handle(Request request, Response response, Callback callback) {

        switch(request.getMethod()) {
            case "POST" -> {
                Optional<String> inputJson = Optional.empty();

                try {
                    inputJson = Optional.of(Content.Source.asString(request));
                } catch (IOException e) {
                    e.printStackTrace();
                    callback.failed(e);
                    return true;
                }

                if (inputJson.isPresent() && inputJson.get().isEmpty()) {
                    callback.failed(new FileNotFoundException("Input is empty"));
                    return true;
                }

                Calendar calendar = new Calendar().loadFromString(inputJson.get());

                try {
                    ServerJsonManager.writeCalendarData(calendar);
                } catch (IOException e) {
                    e.printStackTrace();
                    callback.failed(e);
                    return true;
                }

                callback.succeeded();
                return true;
            }

            case "GET" -> {

            }

            default -> {
                callback.failed(new UnsupportedOperationException("Method not supported"));
            }
        }

        return true;
    }
}
