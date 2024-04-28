package server;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
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
        contextCollection.addHandler(new ContextHandler(new LoginHandler(), "/login"));
        contextCollection.addHandler(new ContextHandler(new RegisterHandler(), "/register"));


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

class LoginHandler extends Handler.Abstract {

    Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
    UserManager manager = new UserManager();
    @Override
    public boolean handle(Request request, Response response, Callback callback) {

        if(!request.getMethod().equals("POST")) {
            response.setStatus(400); // Bad request https://developer.mozilla.org/en-US/docs/Web/HTTP/Status/400
            callback.succeeded();
            return true;
        }

        // Read data
        String requestJson = null;
        try {
            requestJson = Content.Source.asString(request);
            if(requestJson == null) {
                throw new IOException();
            }
        } catch (IOException e) {
            e.printStackTrace();
            response.setStatus(400); // Bad request https://developer.mozilla.org/en-US/docs/Web/HTTP/Status/400
            callback.succeeded();
            return true;
        }

        User requestUser = gson.fromJson(requestJson, User.class);

        Optional<User> loggedInUser = manager.authenticateUser(requestUser.getUsername(), requestUser.getPasswordHash());

        if(loggedInUser.isEmpty()) {
            response.setStatus(401); // Unauthorized, bad credentials https://developer.mozilla.org/en-US/docs/Web/HTTP/Status/401
            callback.succeeded();
            return true;
        }

        String userJson = gson.toJson(loggedInUser.get());
        response.setStatus(200); // OK, successfully logged in

        response.write(true, StandardCharsets.UTF_8.encode(userJson), callback);
        callback.succeeded();
        return true;

    }
}

class RegisterHandler extends Handler.Abstract {

    Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
    UserManager manager = new UserManager();

    @Override
    public boolean handle(Request request, Response response, Callback callback) throws Exception {
        if(!request.getMethod().equals("POST")) {
            response.setStatus(400); // Bad request https://developer.mozilla.org/en-US/docs/Web/HTTP/Status/400
            callback.succeeded();
            return true;
        }
        // Read data
        String requestJson = null;
        try {
            requestJson = Content.Source.asString(request);
            if(requestJson == null) {
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

        if(registeredUser.isEmpty()) {
            response.setStatus(409); // Conflict, user with given username exists
            callback.succeeded();
            return true;
        }

        String registeredUserJson = gson.toJson(registeredUser.get());
        response.setStatus(200); // OK, user registered

        response.write(true, StandardCharsets.UTF_8.encode(registeredUserJson), callback);
        callback.succeeded();
        return true;
    }
}
