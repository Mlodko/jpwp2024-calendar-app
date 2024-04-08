package client.backend;

import client.backend.models.Calendar;
import client.backend.serialization.CalendarIdDeserializer;
import client.backend.serialization.CalendarIdSerializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.InstanceCreator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
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

        GsonBuilder bobTheBuilder = new GsonBuilder();
        bobTheBuilder.registerTypeAdapter(Calendar.class, new CalendarIdSerializer());
        Gson gson = bobTheBuilder.create();

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
    }

    /*
    public static void readAllCalendarsData() throws IOException {

        File calendarsFile = new File(rootDir + "/workspace/calendars.json");

        if(!calendarsFile.getParentFile().mkdirs()) {
            throw new IOException("Unable to create directory " + calendarsFile.getParentFile().getAbsolutePath());
        }

        StringBuilder resultStringBuilder = new StringBuilder();
        try (BufferedReader br
                     = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                resultStringBuilder.append(line).append("\n");
            }
        }
    }
     */

    public static void main(String[] args){
        ArrayList<Calendar> calendars = new ArrayList<>(List.of(
                new Calendar("5189792"),
                new Calendar("jkfnaof"),
                new Calendar("wchodzi gej programista, przestepca, alkoholik i cpun do baru")
        ));

        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();

        String json = gson.toJson(calendars);

        try {
            JsonManager.writeAllCalendarsData(calendars);
        }
        catch (Exception e) {
            e.printStackTrace();
            System.err.println("hehe zesrałem sie ;p");
        }
        ArrayList<Calendar> newCals = gson.fromJson(json, ArrayList.class);
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
