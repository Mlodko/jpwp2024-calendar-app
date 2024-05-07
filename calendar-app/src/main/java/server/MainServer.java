package server;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.server.handler.ContextHandlerCollection;
import org.eclipse.jetty.util.ssl.SslContextFactory;
import org.eclipse.jetty.util.thread.QueuedThreadPool;
import server.handlers.*;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class MainServer {

    static final String IP_ADDR = "127.0.0.1";
    static final int HTTP_PORT = 8080;
    static final int HTTPS_PORT = 8443;
    // You shouldn't store you keystore details like this
    // but since it's just a uni project I will do it anyway
    static final Path KEYSTORE_PATH = Path.of(Paths.get("").toAbsolutePath() + "/src/main/resources/server/keystore.jks");
    static final String KEYSTORE_PASSWORD = "cd@%w46cgQay!pZtrbMp$@az&Df3F4QTHuF4$LRaETA2ryo#9&qkE#nG!inUT$o5B$S5P6DT6#FSxtDjj#mcu5YZE4T4Cqr5nNdBi6KR#csut5LtwpBFKh6$6CjGZ@B6";

    public MainServer() {
        QueuedThreadPool threadPool = new QueuedThreadPool();
        threadPool.setName("Server");

        Server server = new Server(threadPool);

        // HTTP
        ServerConnector httpConnector = new ServerConnector(server);
        httpConnector.setPort(HTTP_PORT);
        httpConnector.setHost(IP_ADDR);

        // HTTPS
        SslContextFactory.Server sslContextFactory = new SslContextFactory.Server();
        sslContextFactory.setKeyStorePath(KEYSTORE_PATH.toString());
        sslContextFactory.setKeyStorePassword(KEYSTORE_PASSWORD);

        ServerConnector httpsConnector = new ServerConnector(server, sslContextFactory);
        httpsConnector.setPort(HTTPS_PORT);
        httpsConnector.setHost(IP_ADDR);

        server.addConnector(httpConnector);
        server.addConnector(httpsConnector);

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