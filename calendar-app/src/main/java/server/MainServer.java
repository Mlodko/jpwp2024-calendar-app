package server;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.server.handler.ContextHandlerCollection;
import org.eclipse.jetty.util.thread.QueuedThreadPool;
import server.handlers.*;

import java.io.File;

public class MainServer {

    // TODO token authentication

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

        contextCollection.addHandler(new ContextHandler(new LoginHandler(), "/login"));
        contextCollection.addHandler(new ContextHandler(new RegisterHandler(), "/register"));
        contextCollection.addHandler(new ContextHandler(new WorkspaceHandler(), "/workspace"));
        contextCollection.addHandler(new ContextHandler(new CalendarHandler(), "/calendar"));
        contextCollection.addHandler(new ContextHandler(new BoardHandler(), "/board"));
        contextCollection.addHandler(new ContextHandler(new CardHandler(), "/card"));

        server.setHandler(contextCollection);

        try {
            setupDirs();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            server.start();
        } catch (Exception e) {
            System.out.println("Server didn't start - unknown error occurred [MainServer -> server.start()]");
        }
    }

    // check if required files and directories exist, if no - do create them silly bitch
    private static void setupDirs() throws Exception {
        String root = ServerJsonManager.getRootDirectoryPath().getParent().toString();
        File file = new File(root + "/users.json");

        if (!file.exists()) {
            if (!file.getParentFile().mkdirs() && !file.createNewFile())
                throw new Exception("/users.json file doesn't exist and couldn't be created");
        }

        file = new File(root + "/workspaces");

        if (!file.exists()) {
            if (!file.getParentFile().mkdirs() && !file.mkdir())
                throw new Exception("/workspace directory doesn't exist and couldn't be created");
        }
    }

    public static void main(String[] args) {
        MainServer mainServer = new MainServer();
        System.out.println();
    }
}

