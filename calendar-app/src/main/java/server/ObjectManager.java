package server;

import client.backend.models.Calendar;
import client.backend.models.KanbanBoard;
import client.backend.models.Workspace;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;

public abstract class ObjectManager {

    private static ArrayList<Workspace> workspaces = ServerJsonManager.readALLdata();

    public static void addToWorkspaces(ArrayList<Workspace> workspaces) throws IOException {
        ObjectManager.workspaces.addAll(workspaces);
        for(Workspace workspace : workspaces) {
            ServerJsonManager.writeWorkspaceData(workspace);
        }
    }

    public static ArrayList<Workspace> getWorkspaces() {
        refreshWorkspaces();
        return workspaces;
    }

    public static ArrayList<Calendar> getCalendars() {
        return workspaces.stream()
                .map(Workspace::getCalendars)
                .flatMap(ArrayList::stream)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public static ArrayList<KanbanBoard> getKanbanBoards() {
        return getCalendars().stream()
                .map(Calendar::getKanbanBoards)
                .flatMap(ArrayList::stream)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public static void refreshWorkspaces() {
        workspaces = ServerJsonManager.readALLdata();
    }

    public static boolean writeWorkspaceFromCache(String workspaceId) {
        Optional<Workspace> workspace = workspaces.stream()
                .filter(wrkspc -> wrkspc.getId().equals(workspaceId))
                .findFirst();

        if(workspace.isEmpty()) {
            return false;
        }
        try {
            ServerJsonManager.writeALLdata(workspace.get());
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public static void main(String[] args) {
        getWorkspaces();
        System.out.println();
    }
}