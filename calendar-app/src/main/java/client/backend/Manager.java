package client.backend;

import client.backend.models.*;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Manager {

    private final Path rootDirectory;
    private Path currentDirectory;

    public Manager() {
        rootDirectory = Paths.get("").toAbsolutePath();
    }

    public String getRootDirectory() {
        return rootDirectory.toString();
    }

    // calendar-app / workspaces / ... / ...
    public static void main(String[] args) {
        Manager manager = new Manager();
        System.out.println(manager.rootDirectory.toString());
        File file = new File(manager.rootDirectory.toString() + "/workspaces");
        System.out.println(file.getAbsolutePath());
    }
}
