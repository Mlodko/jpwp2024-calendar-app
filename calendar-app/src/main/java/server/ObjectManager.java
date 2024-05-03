package server;

import client.backend.models.Calendar;
import client.backend.models.KanbanBoard;
import client.backend.models.Workspace;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class ObjectManager {
    private static ArrayList<Workspace> workspaces = ServerJsonManager.readALLdata();

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

    public static void main(String[] args) {
        getWorkspaces();
        System.out.println();
    }
}
