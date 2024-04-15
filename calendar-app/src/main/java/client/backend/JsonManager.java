package client.backend;

import client.backend.models.Calendar;
import client.backend.models.Card;
import client.backend.models.KanbanBoard;
import client.backend.models.KanbanInsertable;
import client.backend.serialization.CalendarIdDeserializer;
import client.backend.serialization.ColorDeserializer;
import client.backend.serialization.ColorSerializer;
import client.backend.serialization.KanbanIdDeserializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import javafx.scene.paint.Color;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class JsonManager {

    private final static Path rootDir = Paths.get("").toAbsolutePath();


    public static String getRootDirectory() {
        return rootDir.toString();
    }

    public static void writeAllCalendarsData(ArrayList<Calendar> calendars) throws IOException {
        File calendarsFile = new File(rootDir.toString() + "/workspace/calendars.json");

        if(!calendarsFile.getParentFile().exists() && !calendarsFile.getParentFile().mkdirs()) {
            throw new IOException("Unable to create directory " + calendarsFile.getParentFile().getAbsolutePath());
        }

        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().create();

        String json = gson.toJson(calendars);

        if (!calendarsFile.exists()) {
            calendarsFile.createNewFile();
        }

        try (BufferedWriter writer = Files.newBufferedWriter(calendarsFile.toPath())) {
            writer.write(json);
            writer.flush();
        } catch (IOException e) {
            System.err.println(e.getMessage() + "\nCouldn't write to file " + calendarsFile.getAbsolutePath());
            e.printStackTrace();
        }

        for(Calendar calendar : calendars) {
            JsonManager.writeSingleCalendarData(calendar);
        }
    }

    private static void writeSingleCalendarData(Calendar calendar) throws IOException{
        File currentFile = new File(rootDir.toString() + "/workspace/calendar-" + calendar.getID() + "/boards.json");
        if(!currentFile.getParentFile().exists() && !currentFile.getParentFile().mkdirs()) {
            throw new IOException("Unable to create directory " + currentFile.getParentFile().getAbsolutePath());
        }

        if(!currentFile.exists()) currentFile.createNewFile();

        Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().registerTypeAdapter(Color.class, new ColorSerializer()).create();

        try(BufferedWriter writer = Files.newBufferedWriter(currentFile.toPath())) {
            writer.write(gson.toJson(calendar.getKanbanBoards()));
            writer.flush();
        }

        currentFile = new File(currentFile.getParent() + "/cards.json");
        try(BufferedWriter writer = Files.newBufferedWriter(currentFile.toPath())) {
            writer.write(gson.toJson(calendar.getOrphanCards()));
            writer.flush();
        }
    }

    public static ArrayList<Calendar> readAllCalendars() throws IOException {
        ArrayList<Calendar> calendars = JsonManager.readAllCalendarsIdData();

        for (Calendar calendar : calendars) {
            ArrayList<KanbanBoard> allBoards = JsonManager.readAllKanbanBoardsData(calendar);
            ArrayList<Card> allCards = JsonManager.readAllCardsData(calendar);

            for (int i = 0; i < calendar.getKanbanBoards().size(); i++) {
                KanbanBoard emptyBoard = calendar.getKanbanBoards().get(i);
                String boardId = emptyBoard.getId();

                calendar.setKanbanBoard(allBoards.stream().filter(newBoard -> newBoard.getId().equals(boardId)).findFirst().get(),
                                        i);
                KanbanBoard newBoard = calendar.getKanbanBoards().get(i);
                newBoard.setCalendar(calendar);

                HashMap<String, ArrayList<KanbanInsertable>> itemHashmap = new HashMap<>();
                newBoard.getItemIds().forEach((columnTitle, itemIdList) -> {
                    ArrayList<KanbanInsertable> itemList = allCards.stream()
                            .filter(card -> itemIdList.contains(card.getId()))
                            .collect(Collectors.toCollection(ArrayList::new));
                    itemHashmap.put(columnTitle, itemList);
                });
                newBoard.setItemsLists(itemHashmap);
            }

            for (int i = 0; i < calendar.getOrphanCards().size(); i++) {
                Card card = calendar.getOrphanCards().get(i);
                String cardId = card.getId();
                calendar.setOrphanCard(allCards.stream().filter(newCard -> newCard.getId().equals(cardId)).findFirst().get(),
                                        i);
            }
        }

        return calendars;
    }

    private static ArrayList<Calendar> readAllCalendarsIdData() throws IOException {
        File calendarsFile = new File(rootDir + "/workspace/calendars.json");

        if(!calendarsFile.getParentFile().mkdirs() && !calendarsFile.exists()) {
            throw new IOException("Unable to create directory " + calendarsFile.getParentFile().getAbsolutePath());
        }

        String calJson = new String(Files.readAllBytes(calendarsFile.toPath()));
        System.out.println("readAllCalendarsData -> calJson:" + calJson);

        Type calendarArrayType = new TypeToken<ArrayList<Calendar>>() {}.getType();
        Gson gson = new GsonBuilder().registerTypeAdapter(Calendar.class, new CalendarIdDeserializer()).create();
        ArrayList<Calendar> calendars = gson.fromJson(calJson, calendarArrayType);
        // sex

        return calendars;
    }

    private static ArrayList<KanbanBoard> readAllKanbanBoardsData(Calendar calendar) throws IOException {
        File boardsFile = new File(rootDir + "/workspace/calendar-" + calendar.getID() + "/boards.json");
        if(!boardsFile.getParentFile().mkdirs() && !boardsFile.exists()) {
            throw new IOException("Unable to create directory " + boardsFile.getParentFile().getAbsolutePath());
        }
        String boardsJson = new String(Files.readAllBytes(boardsFile.toPath()));

        Type boardArrayType = new TypeToken<ArrayList<KanbanBoard>>() {}.getType();
        Gson gson = new GsonBuilder().create();
        return gson.fromJson(boardsJson, boardArrayType);
    }

    private static ArrayList<Card> readAllCardsData(Calendar calendar) throws IOException {
        File cardsFile = new File(rootDir + "/workspace/calendar-" + calendar.getID() + "/cards.json");
        if(!cardsFile.getParentFile().mkdirs() && !cardsFile.exists()) {
            throw new IOException("Unable to create directory " + cardsFile.getParentFile().getAbsolutePath());
        }
        String cardsJson = new String(Files.readAllBytes(cardsFile.toPath()));

        Type cardArrayType = new TypeToken<ArrayList<Card>>() {}.getType();
        Gson gson = new GsonBuilder().registerTypeAdapter(Color.class, new ColorDeserializer()).create();
        return gson.fromJson(cardsJson, cardArrayType);
    }


    public static void main(String[] args) throws IOException{
        ArrayList<Card> cards = new ArrayList<>(List.of(
                new Card("22", "desc", new Date(), new Date(), new Date()),
                new Card("21", "desc", new Date(), new Date(), new Date()),
                new Card("23", "desc", new Date(), new Date(), new Date())
        ));
        ArrayList<KanbanBoard> boards = new ArrayList<>(List.of(
                new KanbanBoard("1", "twoja stara", new Date(), new Date(), new Calendar("31"), new HashMap<>()),
                new KanbanBoard("2", "twoja stara", new Date(), new Date(), new Calendar("32"), new HashMap<>()),
                new KanbanBoard("3", "twoja stara", new Date(), new Date(), new Calendar("33"), new HashMap<>())
        ));

        ArrayList<KanbanInsertable> kanbanInsertables = new ArrayList<>(cards);
        for(KanbanBoard board: boards) {
            board.addNewItemColumn("Column", kanbanInsertables);
        }

        ArrayList<Calendar> calendars = new ArrayList<>(List.of(
                new Calendar("31", cards, boards),
                new Calendar("32", cards, boards),
                new Calendar("33", cards, boards)
        ));


        JsonManager.writeAllCalendarsData(calendars);

        ArrayList<Calendar> readCalendars = JsonManager.readAllCalendars();
        System.out.println();
    }

}



/*
./workspace
|_calendars.json
|_calendarID_1
| |_boards.json
| |_cards.json
| |_other files i.e. multimedia
|_calendarID_2
| |_...
|_...


 */
