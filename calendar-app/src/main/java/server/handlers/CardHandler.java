package server.handlers;

import client.backend.models.Card;
import org.eclipse.jetty.io.Content;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Response;
import org.eclipse.jetty.util.Callback;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Optional;

public class CardHandler extends Handler.Abstract {

    @Override
    public boolean handle(Request request, Response response, Callback callback) {
        switch (request.getMethod()) {
            case "GET" -> {
                Optional<String> inputJson = Optional.empty();

                try {
                    inputJson = Optional.of(Content.Source.asString(request));
                } catch (IOException e) {
                    e.printStackTrace();
                    callback.succeeded();
                }

                if (inputJson.isEmpty()) {
                    callback.failed(new FileNotFoundException("Input is empty"));
                }

                Card card = new Card().loadFromString(inputJson.get());
                System.out.println(card.saveToString());
                callback.succeeded();
            }

            default -> {
                System.out.println("Don't know how or why, but it got to default. CardHandler->handle");
                callback.succeeded();
            }
        }
        return true;
    }
}
