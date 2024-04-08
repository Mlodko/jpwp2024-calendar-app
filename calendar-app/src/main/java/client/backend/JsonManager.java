package client.backend;

import client.backend.models.Calendar;
import client.backend.models.Card;
import client.backend.models.KanbanBoard;
import client.backend.models.KanbanInsertable;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javafx.scene.paint.Color;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

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

        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();

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

        Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
        try(BufferedWriter writer = Files.newBufferedWriter(currentFile.toPath())) {
            writer.write(gson.toJson(calendar.getKanbanBoards()));
            writer.flush();
        }

        currentFile = new File(currentFile.getParent() + "/cards.json");
        try(BufferedWriter writer = Files.newBufferedWriter(currentFile.toPath())) {
            writer.write(gson.toJson(calendar.getOrphanCards()));
            writer.flush();
        }

        /*
        Path parentDir = calendarFile.getParentFile().toPath();
        File cardsFile = new File(parentDir.toString() + "/cards.json");
        if(!cardsFile.exists()) cardsFile.createNewFile();

        Gson gson = new Gson();

        try (BufferedWriter writer = Files.newBufferedWriter(cardsFile.toPath())) {
            writer.write(gson.toJson(calendar.getKanbanIds()));
            writer.flush();
        } catch (IOException e) {
            System.err.println(e.getMessage() + "\nCouldn't write to file " + cardsFile.getAbsolutePath());
            e.printStackTrace();
        }

        File kanbanFile = new File(parentDir.toString() + "/boards.json");
        if(!kanbanFile.exists()) kanbanFile.createNewFile();

        try (BufferedWriter writer = Files.newBufferedWriter(kanbanFile.toPath())) {
            writer.write();
            writer.flush();
        } catch (IOException e) {
            System.err.println(e.getMessage() + "\nCouldn't write to file " + kanbanFile.getAbsolutePath());
            e.printStackTrace();
        }
         */
    }




    public static void readAllCalendarsData() throws IOException {

        File calendarsFile = new File(rootDir + "/workspace/calendars.json");

        if(!calendarsFile.getParentFile().mkdirs()) {
            throw new IOException("Unable to create directory " + calendarsFile.getParentFile().getAbsolutePath());
        }

        StringBuilder resultStringBuilder = new StringBuilder();
    }

    public static void main(String[] args) throws IOException{
        ArrayList<Card> cards = new ArrayList<>(List.of(
                new Card("21", "desc", new Date(), new Date(), new Date(), Color.RED),
                new Card("22", "desc", new Date(), new Date(), new Date(), Color.RED),
                new Card("23", "desc", new Date(), new Date(), new Date(), Color.RED)
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
