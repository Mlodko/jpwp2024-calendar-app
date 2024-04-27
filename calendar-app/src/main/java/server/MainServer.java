package server;

import org.eclipse.jetty.io.Content;
import org.eclipse.jetty.io.Content.Chunk;
import org.eclipse.jetty.http.*;
import org.eclipse.jetty.server.*;
import org.eclipse.jetty.server.handler.AbstractHandler;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.server.handler.ContextHandlerCollection;
import org.eclipse.jetty.util.thread.QueuedThreadPool;
import org.eclipse.jetty.util.Callback;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;

import javax.servlet.http.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.util.Optional;
import java.util.stream.Collectors;

import client.backend.models.*;

public class MainServer {

    static final int PORT = 8080;
    static final String IP_ADDR = "127.0.0.1";

    public MainServer() {
        QueuedThreadPool threadPool = new QueuedThreadPool();
        threadPool.setName("Server");

        Server server = new Server(threadPool);
        ServerConnector connector = new ServerConnector(server);
        connector.setPort(PORT);
        connector.setHost(IP_ADDR);

        server.addConnector(connector);

        ContextHandlerCollection contextCollection = new ContextHandlerCollection();

        contextCollection.addHandler(new ContextHandler(new CardHandler(), "/chuj"));

        server.setHandler(contextCollection);
        /*
        // Set a simple Handler to handle requests/responses.
        server.setHandler(new Handler.Abstract()
        {
            @Override
            public boolean handle(Request request, Response response, Callback callback)
            {
                // Succeed the callback to signal that the
                // request/response processing is complete.
                System.out.println(request.getMethod());
                callback.succeeded();
                return true;
            }
        });
        */

        // Start the Server to start accepting connections from clients.
        try {
            server.start();
        } catch (Exception e) {
            System.out.println("Server started - unknown error occurred, couldn't start [MainServer -> server.start()]");
        }
    }

    public static void main(String[] args) {
        MainServer mainServer = new MainServer();
        System.out.println();
    }
}

class CardHandler extends Handler.Abstract {

    @Override
    public boolean handle(Request request, Response response, Callback callback){
        switch(request.getMethod()) {
            case "GET" -> {
                Optional<String> inputJson = Optional.empty();

                try {
                    inputJson = Optional.of(Content.Source.asString(request));
                } catch (IOException e) {
                    e.printStackTrace();
                    callback.succeeded();
                }

                if(inputJson.isEmpty()) {
                    callback.failed(new FileNotFoundException("jebał cię pies"));
                }

                Card card = new Card().loadFromString(inputJson.get());
                System.out.println(card.saveToString());
                callback.succeeded();
            }

            default -> {
                System.out.println("DIFOLT");
                callback.succeeded();
            }
        }
        return true;
    }
}
