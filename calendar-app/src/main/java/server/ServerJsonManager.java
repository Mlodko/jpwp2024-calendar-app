package server;

import client.backend.models.*;
import client.backend.models.Calendar;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.eclipse.jetty.util.IO;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.apache.commons.codec.digest.DigestUtils.sha256Hex;

public class ServerJsonManager {

    private final static Path rootDir = Path.of(Paths.get("").toAbsolutePath() + "/server/workspaces/");
    private final static Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().create();

    public static String getRootDirectory() {
        return rootDir.toString();
    }
    public static Path getRootDirectoryPath() { return rootDir; }

    //region READERS
    public static ArrayList<Workspace> readALLdata() {
        if(rootDir.toFile().listFiles() == null) {
            return new ArrayList<>();
        }
        ArrayList<Workspace> workspaces = new ArrayList<>();
        for(String workspaceDirName : rootDir.toFile().list()) {
            if(!workspaceDirName.startsWith("workspace-")) {
                continue;
            }
            String workspaceId = workspaceDirName.substring("workspace-".length());
            try {
                workspaces.add(readCompleteWorkspaceData(workspaceId));
            } catch (Exception e) {
                e.printStackTrace();
                System.err.println(e.getMessage());
            }
        }
        return workspaces;
    }

    public static Workspace readCompleteWorkspaceData(String workspaceId) throws Exception {
        Workspace workspace = readWorkspaceData(workspaceId);

        // I have to clone the calendar IDs because setting/updating a workspace's calendar list
        // will also change its calendar id list which messes with iterating over it
        ArrayList<String> calendarIds = (ArrayList<String>) workspace.getCalendarIds().clone();

        for(String calendarId : calendarIds){
            workspace.addToCalendars(readCompleteCalendarData(calendarId, workspaceId));
        }

        return workspace;
    }

    public static Calendar readCompleteCalendarData(String calendarId, String workspaceId) throws Exception {
        Calendar calendar = readCalendarStructureData(calendarId, workspaceId).orElseThrow();
        ArrayList<Card> orphanCards = readOrphanCards(calendarId, workspaceId);
        ArrayList<KanbanBoard> boards = readKanbanBoardsData(calendarId, workspaceId);
        ArrayList<Card> kanbanCards = readAllKanbanCardsData(calendarId, workspaceId);

        // Add cards to kanban boards
        for (KanbanBoard board : boards) {
            HashMap<String, ArrayList<String>> itemIds = board.getItemIds();
            board.setItemsLists(new HashMap<>());
            itemIds.forEach((columnKey, cardIds) -> {
                board.addNewItemColumn(columnKey, kanbanCards.stream()
                        .filter(card -> cardIds.contains(card.getId()))
                        .collect(Collectors.toCollection(ArrayList::new)));
            });
        }

        calendar.setKanbanBoards(boards);
        calendar.setOrphanCards(orphanCards);
        return calendar;
    }

    // This has: id, kanbanIds, memberIds, workspaceId
    public static Optional<Calendar> readCalendarStructureData(String calendarID, String workspaceId) throws IOException {
        File file = new File(rootDir + "/workspace-" + workspaceId + "/calendar-" + calendarID + "/calendar.json");

        if (!file.getParentFile().exists() && !file.getParentFile().mkdirs()) {
            throw new IOException("Unable to create directory" + file.getParentFile().getAbsolutePath());
        }

        if (!file.exists()) {
            return Optional.empty();
        }

        String json = Files.readString(file.toPath());

        return Optional.of(gson.fromJson(json, Calendar.class));
    }

    public static ArrayList<Card> readAllKanbanCardsData(String calendarId, String workspaceId) throws IOException {
        File cardsFile = new File(rootDir + "/workspace-" + workspaceId + "/calendar-" + calendarId + "/cards.json");

        if (!cardsFile.getParentFile().exists() && !cardsFile.getParentFile().mkdirs()) {
            throw new IOException("Unable to create directory " + cardsFile.getParentFile().getAbsolutePath());
        }

        String cardsJson = Files.readString(cardsFile.toPath());
        Type cardArrayType = new TypeToken<ArrayList<Card>>() {
        }.getType();
        return gson.fromJson(cardsJson, cardArrayType);
    }

    public static ArrayList<Card> readOrphanCards(String calendarID, String workspaceId) throws IOException {
        File file = new File(rootDir + "/workspace-" + workspaceId + "/calendar-" + calendarID + "/orphans.json");

        if (!file.getParentFile().exists() && !file.getParentFile().mkdirs()) {
            throw new IOException("Unable to create directory " + file.getParentFile().getAbsolutePath());
        }

        if (!file.exists()) {
            file.createNewFile();
            return new ArrayList<Card>();
        }

        String json = Files.readString(file.toPath());
        Type type = new TypeToken<ArrayList<Card>>() {
        }.getType();

        return gson.fromJson(json, type);
    }

    public static Workspace readWorkspaceData(String workspaceId) throws IOException {
        File file = new File(rootDir + "/workspace-" + workspaceId + "/workspace.json");

        if (!file.getParentFile().exists() && !file.getParentFile().mkdirs()) {
            throw new IOException("Unable to create directory " + file.getParentFile().getAbsolutePath());
        }

        if (!file.exists()) {
            file.createNewFile();
            return Workspace.DEFAULT;
        }

        String jsonString = Files.readString(file.toPath());

        return gson.fromJson(jsonString, Workspace.class);
    }


    public static ArrayList<KanbanBoard> readKanbanBoardsData(String calendarId, String workspaceId) throws IOException {

        File boardsFile = new File(rootDir + "/workspace-" + workspaceId + "/calendar-" + calendarId + "/boards.json");

        if (!boardsFile.exists()) {
            return new ArrayList<>();
        }

        String boardsJson = Files.readString(boardsFile.toPath());

        if (boardsJson.isEmpty()) {
            return new ArrayList<>();
        }

        Type boardArrayType = new TypeToken<ArrayList<KanbanBoard>>() {
        }.getType();

        return gson.fromJson(boardsJson, boardArrayType);
    }

    public static ArrayList<User> readUsersData() throws IOException {
        File usersFile = new File(rootDir.getParent() + "/users.json");

        if(!usersFile.exists()) {
            return new ArrayList<>();
        }

        Type userArrayType = new TypeToken<ArrayList<User>>(){}.getType();
        String jsonString = Files.readString(usersFile.toPath());

        if (jsonString.isEmpty())
            return new ArrayList<>();
        else
            return gson.fromJson(jsonString, userArrayType);
    }
    //endregion

    //region WRITERS
    public static void writeALLdata(Workspace workspace) throws IOException {
        writeWorkspaceData(workspace);
        String workspaceId = workspace.getId();

        for(Calendar calendar : workspace.getCalendars()) {
            writeCalendarData(calendar, workspaceId);
            writeOrphanCards(calendar.getOrphanCards(), calendar.getID(), workspaceId);
            writeKanbanBoards(calendar.getKanbanBoards(), calendar.getID(), workspaceId);

            for (KanbanBoard board : calendar.getKanbanBoards()) {
                writeKanbanCards(board.getItems(), calendar.getID(), workspaceId);
            }
        }
    }

    public static void writeWorkspaceData(Workspace workspace) throws IOException {
        File workspaceFile = new File(getRootDirectory() + "/workspace-" + workspace.getId() + "/workspace.json");

        if (!workspaceFile.getParentFile().exists() && !workspaceFile.getParentFile().mkdirs()) {
            throw new IOException("Could not create workspace directory - " + workspaceFile.getParentFile().getAbsolutePath());
        }

        for(String calendarId : workspace.getCalendarIds()) {
            File calendarDir = new File(rootDir + "/workspace-" + workspace.getId() + "/calendar-" + calendarId + "/");
            calendarDir.mkdirs();
            calendarDir.createNewFile();
        }

        String json = gson.toJson(workspace);

        if (!workspaceFile.exists()) {
            workspaceFile.createNewFile();
        }

        try (BufferedWriter writer = Files.newBufferedWriter(workspaceFile.toPath())) {
            writer.write(json);
            writer.flush();
        } catch (IOException e) {
            System.err.println(e.getMessage() + "\nCouldn't write to file " + workspaceFile.getAbsolutePath());
            e.printStackTrace();
        }
    }

    public static void writeCalendarData(Calendar calendar, String workspaceId) throws IOException {
        File calendarFile = new File(rootDir + "/workspace-" + workspaceId + "/calendar-" + calendar.getID() + "/calendar.json");

        if (!calendarFile.exists()) {
            calendarFile.createNewFile();
        }

        try (BufferedWriter writer = Files.newBufferedWriter(calendarFile.toPath())) {
            writer.write(gson.toJson(calendar));
            writer.flush();
        }
    }

    public static void writeKanbanBoards(ArrayList<KanbanBoard> boards, String calendarId, String workspaceId) throws IOException {
        File kanbanFile = new File(rootDir + "/workspace-" + workspaceId + "/calendar-" + calendarId + "/boards.json");

        if (!kanbanFile.exists()) {
            kanbanFile.createNewFile();
        }

        try (BufferedWriter writer = Files.newBufferedWriter(kanbanFile.toPath())) {
            writer.write(gson.toJson(boards));
            writer.flush();
        }
    }

    public static void writeKanbanCards(ArrayList<Card> cards, String calendarId, String workspaceId) throws IOException {
        File cardsFile = new File(rootDir + "/workspace-" + workspaceId + "/calendar-" + calendarId + "/cards.json");

        if (!cardsFile.exists()) {
            cardsFile.createNewFile();
        }

        try (BufferedWriter writer = Files.newBufferedWriter(cardsFile.toPath())) {
            writer.write(gson.toJson(cards));
            writer.flush();
        }
    }

    public static void writeOrphanCards(ArrayList<Card> cards, String calendarId, String workspaceId) throws IOException {
        File orphanFile = new File(rootDir + "/workspace-" + workspaceId + "/calendar-" + calendarId + "/orphans.json");

        if (!orphanFile.exists()) {
            orphanFile.createNewFile();
        }

        try (BufferedWriter writer = Files.newBufferedWriter(orphanFile.toPath())) {
            writer.write(gson.toJson(cards));
            writer.flush();
        }
    }

    public static void writeUserData(ArrayList<User> users) throws IOException {
        File userFile = new File(rootDir.getParent() + "/users.json");

        if (!userFile.exists()) {
            userFile.createNewFile();
        }

        try (BufferedWriter writer = Files.newBufferedWriter(userFile.toPath())) {
            writer.write(gson.toJson(users));
            writer.flush();
        }
    }
    //endregion

    //region Updating data
    public static boolean addToUsers(User user) {
        ArrayList<User> users;
        try {
            users = readUsersData();
            if(users.stream().map(User::getId).toList().contains(user.getId())) {
                return false;
            }
            users.add(user);
            writeUserData(users);
        } catch (IOException e) {
            return false;
        }
        return true;
    }
    //endregion
}
