package server;

import client.backend.models.Workspace;

import java.io.IOException;
import java.util.ArrayList;

public class ObjectManager {
    private static ArrayList<Workspace> workspaces = ServerJsonManager.readAllWorkspaces();

    public static ArrayList<Workspace> getWorkspaces() {
        return workspaces;
    }

    public static void refreshWorkspaces() {
        workspaces = ServerJsonManager.readAllWorkspaces();
    }
}
