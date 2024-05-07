package client.backend;

import client.backend.models.*;
import client.backend.models.Calendar;
import client.backend.models.Card;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

import static org.apache.commons.codec.digest.DigestUtils.sha256Hex;

public class JsonManager {

    private final static Path rootDir = Paths.get("").toAbsolutePath();
    private final static Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().create();

    public static String getRootDirectory() {
        return rootDir.toString();
    }

    public static void removeAllLocalData() {
        File file = new File(rootDir + "/workspace");

        if (!file.exists()) {
            file.mkdir();
            return;
        }

        purge(file);
    }

    private static void purge(File file) {
        if (file.listFiles() != null) {
            for (File tmp : file.listFiles()) {
                if (tmp.isDirectory())
                    purge(tmp);
                tmp.delete();
            }
        }
    }

    //region READERS

    public static Workspace readCompleteWorkspaceData() throws Exception {
        Workspace workspace = readWorkspaceData();

        for(String calendarId : workspace.getCalendarIds()){
            workspace.addToCalendars(readCompleteCalendarData(calendarId));
        }

        return workspace;
    }

    public static Calendar readCompleteCalendarData(String calendarId) throws Exception {
        Calendar calendar = readCalendarStructureData(calendarId).orElseThrow();
        ArrayList<Card> orphanCards = readOrphanCards(calendarId);
        ArrayList<KanbanBoard> boards = readKanbanBoardsData(calendarId);
        ArrayList<Card> kanbanCards = readAllKanbanCardsData(calendarId);

        // Add cards to kanban boards
        for (KanbanBoard board : boards) {
            board.getItemIds().forEach((columnKey, cardIds) -> {
                board.addToItemsList(columnKey, (ArrayList<Card>) kanbanCards.stream()
                        .filter(card -> cardIds.contains(card.getId()))
                        .collect(Collectors.toCollection(ArrayList::new)));
            });
        }

        calendar.setKanbanBoards(boards);
        calendar.setOrphanCards(orphanCards);
        return calendar;
    }

    // This has: id, kanbanIds, memberIds, workspaceId
    public static Optional<Calendar> readCalendarStructureData(String calendarID) throws IOException {
        File file = new File(rootDir + "/workspace/calendar-" + calendarID + "/calendar.json");

        if (!file.getParentFile().exists() && !file.getParentFile().mkdirs()) {
            throw new IOException("Unable to create directory" + file.getParentFile().getAbsolutePath());
        }

        if (!file.exists()) {
            return Optional.empty();
        }

        String json = Files.readString(file.toPath());

        return Optional.of(gson.fromJson(json, Calendar.class));
    }

    public static ArrayList<Card> readAllKanbanCardsData(String calendarId) throws IOException {
        File cardsFile = new File(rootDir + "/workspace/calendar-" + calendarId + "/cards.json");

        if (!cardsFile.getParentFile().exists() && !cardsFile.getParentFile().mkdirs()) {
            throw new IOException("Unable to create directory " + cardsFile.getParentFile().getAbsolutePath());
        }

        String cardsJson = Files.readString(cardsFile.toPath());
        Type cardArrayType = new TypeToken<ArrayList<Card>>() {
        }.getType();
        return gson.fromJson(cardsJson, cardArrayType);
        // sex
    }

    public static ArrayList<Card> readOrphanCards(String calendarID) throws IOException {
        File file = new File(rootDir + "/workspace/calendar-" + calendarID + "/orphans.json");

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

    private static Workspace readWorkspaceData() throws IOException {
        File file = new File(rootDir.toString() + "/workspace/workspace.json");

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


    private static ArrayList<KanbanBoard> readKanbanBoardsData(String calendarId) throws IOException {

        File boardsFile = new File(rootDir + "/workspace/calendar-" + calendarId + "/boards.json");

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

    //endregion
    //region WRITERS

    public static void writeALLdata(Workspace workspace /*This workspace *needs* to be complete*/) throws IOException {
        writeWorkspaceData(workspace);

        for(Calendar calendar : workspace.getCalendars()) {
            writeCalendarData(calendar);
            writeOrphanCards(calendar.getOrphanCards(), calendar.getID());
            writeKanbanBoards(calendar.getKanbanBoards(), calendar.getID());

            for (KanbanBoard board : calendar.getKanbanBoards()) {
                writeKanbanCards(board.getItems(), calendar.getID());
            }
        }
    }

    public static void writeWorkspaceData(Workspace workspace) throws IOException {
        File workspaceFile = new File(getRootDirectory() + "/workspace/workspace.json");

        if (!workspaceFile.getParentFile().exists() && !workspaceFile.getParentFile().mkdirs()) {
            throw new IOException("Could not create workspace directory - " + workspaceFile.getParentFile().getAbsolutePath());
        }

        for(String calendarId : workspace.getCalendarIds()) {
            File calendarDir = new File(rootDir + "/workspace/calendar-" + calendarId + "/");
            calendarDir.mkdirs();
            new File(calendarDir.toPath() + "/calendar.json").createNewFile();
            new File(calendarDir.toPath() + "/boards.json").createNewFile();
            new File(calendarDir.toPath() + "/cards.json").createNewFile();
            new File(calendarDir.toPath() + "/orphans.json").createNewFile();
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

    public static void writeCalendarData(Calendar calendar) throws IOException {
        File calendarFile = new File(rootDir + "/workspace/calendar-" + calendar.getID() + "/calendar.json");

        if (!calendarFile.exists()) {
            calendarFile.createNewFile();
        }

        try (BufferedWriter writer = Files.newBufferedWriter(calendarFile.toPath())) {
            writer.write(gson.toJson(calendar));
            writer.flush();
        }
    }

    public static void writeKanbanBoards(ArrayList<KanbanBoard> boards, String calendarId) throws IOException {
        File kanbanFile = new File(rootDir + "/workspace/calendar-" + calendarId + "/boards.json");

        if (!kanbanFile.exists()) {
            kanbanFile.createNewFile();
        }

        try (BufferedWriter writer = Files.newBufferedWriter(kanbanFile.toPath())) {
            writer.write(gson.toJson(boards));
            writer.flush();
        }
    }

    public static void writeKanbanCards(ArrayList<Card> cards, String calendarId) throws IOException {
        File cardsFile = new File(rootDir + "/workspace/calendar-" + calendarId + "/cards.json");

        if (!cardsFile.exists()) {
            cardsFile.createNewFile();
        }

        try (BufferedWriter writer = Files.newBufferedWriter(cardsFile.toPath())) {
            writer.write(gson.toJson(cards));
            writer.flush();
        }
    }

    public static void writeOrphanCards(ArrayList<Card> cards, String calendarId) throws IOException {
        File orphanFile = new File(rootDir + "/workspace/calendar-" + calendarId + "/orphans.json");

        if (!orphanFile.exists()) {
            orphanFile.createNewFile();
        }

        try (BufferedWriter writer = Files.newBufferedWriter(orphanFile.toPath())) {
            writer.write(gson.toJson(cards));
            writer.flush();
        }
    }

    //endregion

    public static void main(String[] args) throws IOException {
        ArrayList<Card> cards = new ArrayList<>(List.of(
                new Card("Card 1", "Desc", getRandomDateWithin7Days(-1), getRandomDateWithin7Days(1)),
                new Card("Card 2", "Desc", getRandomDateWithin7Days(-1), getRandomDateWithin7Days(1)),
                new Card("Card 3", "Desc", getRandomDateWithin7Days(-1), getRandomDateWithin7Days(1))
        ));
        ArrayList<KanbanBoard> boards = new ArrayList<>(List.of(
                new KanbanBoard("Board 1", "desc", new Date(), new Date(), new Calendar(), getRandomDateWithin7Days(-1), getRandomDateWithin7Days(1), new HashMap<>())
        ));
        boards.get(0).addNewItemColumn("Column 1", cards);

        ArrayList<Card> orphanCards = new ArrayList<>(List.of(
                new Card("Card 4", "Desc", getRandomDateWithin7Days(-1), getRandomDateWithin7Days(1)),
                new Card("Card 5", "Desc", getRandomDateWithin7Days(-1), getRandomDateWithin7Days(1)),
                new Card("Card 6", "Desc", getRandomDateWithin7Days(-1), getRandomDateWithin7Days(1))
        ));

        ArrayList<User> users = new ArrayList<>(List.of(
                new User("User1", sha256Hex("password123"), "test@agh.edu.pl")
        ));

        Workspace workspace = new Workspace("Test workspace", "Description");
        workspace.setMembers(users);

        ArrayList<Calendar> calendars = new ArrayList<>(List.of(
                new Calendar(orphanCards, boards, users, workspace)
        ));

        workspace.setCalendars(calendars);
        boards.get(0).setCalendar(calendars.get(0));

        writeWorkspaceData(workspace);
    }

    static Date getRandomDateWithin7Days(long multiplier) {
        // Get the current date
        Date currentDate = new Date();

        // Create a Calendar instance and set it to the current date
        java.util.Calendar calendar = java.util.Calendar.getInstance();
        calendar.setTime(currentDate);

        // Generate a random number of milliseconds within 7 days
        Random random = new Random();
        long randomMillisToAdd = multiplier * Math.abs(random.nextLong() % (7 * 24 * 60 * 60 * 1000));

        // Add the random duration to the current date
        calendar.add(java.util.Calendar.MILLISECOND, (int) randomMillisToAdd);

        // Return the random date
        return calendar.getTime();
    }
}