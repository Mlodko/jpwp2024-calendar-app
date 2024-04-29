package server;

import org.eclipse.jetty.server.*;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.server.handler.ContextHandlerCollection;
import org.eclipse.jetty.util.thread.QueuedThreadPool;

import server.handlers.CardHandler;
import server.handlers.LoginHandler;
import server.handlers.RegisterHandler;

public class MainServer {

    static final String IP_ADDR = "127.0.0.1";
    static final int PORT = 8080;

    public MainServer() {
        QueuedThreadPool threadPool = new QueuedThreadPool();
        threadPool.setName("Server");

        Server server = new Server(threadPool);
        ServerConnector connector = new ServerConnector(server);
        connector.setPort(PORT);
        connector.setHost(IP_ADDR);

        server.addConnector(connector);

        ContextHandlerCollection contextCollection = new ContextHandlerCollection();

        contextCollection.addHandler(new ContextHandler(new CardHandler(), "/cards"));
        contextCollection.addHandler(new ContextHandler(new LoginHandler(), "/login"));
        contextCollection.addHandler(new ContextHandler(new RegisterHandler(), "/register"));


        server.setHandler(contextCollection);
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

