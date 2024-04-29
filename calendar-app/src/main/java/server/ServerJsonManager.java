package server;

import client.backend.models.Calendar;
import client.backend.models.Card;
import client.backend.models.User;
import client.backend.models.Workspace;
import client.backend.serialization.ColorSerializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import javafx.scene.paint.Color;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ServerJsonManager {
    private final static Path rootDir = Path.of(Paths.get("").toAbsolutePath() + "/server");

     public static String getRootDirectory() {
         return rootDir.toString();
     }

     private static void writeWorkspaceData(Workspace workspace) throws IOException {
         File calendarsFile = new File(rootDir.toString() + "/workspace-" + workspace.getId() + "/calendars.json");

         if(!calendarsFile.exists() && !calendarsFile.getParentFile().mkdirs()) {
             throw new IOException("Unable to create directory " + calendarsFile.getParentFile().getAbsolutePath());
         }

         Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().create();

         String json = gson.toJson(workspace.getCalendars());

         if(!calendarsFile.exists()) calendarsFile.createNewFile();

         try (BufferedWriter writer = Files.newBufferedWriter(calendarsFile.toPath())) {
             writer.write(json);
             writer.flush();
         } catch (IOException e) {
             System.err.println(e.getMessage() + "\nCouldn't write to file " + calendarsFile.getAbsolutePath());
             e.printStackTrace();
         }

         for(Calendar calendar : workspace.getCalendars()) {
             writeSingleCalendarData(calendar);
         }
     }

     private static void writeSingleCalendarData(Calendar calendar) throws IOException {
         File currentFile = new File(rootDir.toString() + "/workspace-" + calendar.getWorkspace().getId()
                 + "/calendar-" + calendar.getID() + "/boards.json");
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

     private static void writeKanbanData (Calendar calendar) throws IOException {

         File calendarFile = new File(rootDir.toString() + "/workspace-" + calendar.getWorkspace().getId() +
                                        "/calendar-" + calendar.getID() +
                                        "/boards.json");

         if(!calendarFile.getParentFile().exists() && !calendarFile.getParentFile().mkdirs()) {
             throw new IOException("Unable to create directory " + calendarFile.getParentFile().getAbsolutePath());
         }

         if(!calendarFile.exists())
             calendarFile.createNewFile();

         ArrayList<Card> cards = calendar.getKanbanBoards().stream().map(kanbanBoard -> kanbanBoard.getItemsLists().values())
                 .flatMap(Collection::stream)
                 .flatMap(ArrayList::stream)
                 .collect(Collectors.toCollection(ArrayList::new));

         Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().create();

         try(BufferedWriter writer = Files.newBufferedWriter(calendarFile.toPath())) {
             writer.write(gson.toJson(calendar.getKanbanBoards()));
             writer.flush();
         }
     }

     public static ArrayList<User> readUsersData() throws IOException{
         File usersFile = new File(rootDir.toString() + "/users.json");

         if(!usersFile.exists()) {
             return new ArrayList<>();
         }

         String json = new String(Files.readAllBytes(usersFile.toPath()));

         Type userArrayType = new TypeToken<ArrayList<User>>() {}.getType();
         Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
         return gson.fromJson(json, userArrayType);
     }

     public static void addToUsers(User user) throws IOException {
         File usersFile = new File(rootDir + "/users.json");
         System.out.println(usersFile.toPath().toString());
         Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();

         // The file doesn't exist, create it and write the user to it
         if(!usersFile.exists()) {
             usersFile.getParentFile().mkdirs();
             usersFile.createNewFile();
             ArrayList<User> arrayList = new ArrayList<>();
             arrayList.add(user);
             String json = gson.toJson(arrayList);
             try(BufferedWriter writer = Files.newBufferedWriter(usersFile.toPath())) {
                 writer.write(json);
                 writer.flush();
             }
             return;
         }

         // Get current users
         ArrayList<User> users = readUsersData();

         // Add the new one
         users.add(user);

         // Serialize the updated list
         String json = gson.toJson(users);
         try(BufferedWriter writer = Files.newBufferedWriter(usersFile.toPath())) {
             writer.write(json);
             writer.flush();
         }
     }

     public static void updateUserData(User newUser) throws IOException {
         File usersFile = new File(rootDir + "/users.json");
         Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();

         // Get current users
         ArrayList<User> users = readUsersData();

         for(int i = 0; i < users.size(); i++) {
             if(!users.get(i).getId().equals(newUser.getId())) {
                 continue;
             }
             // Found the user whose id matches the newUser's, it's index is i
             users.set(i, newUser);
         }

         // Serialize the updated list
         String json = gson.toJson(users);
         try(BufferedWriter writer = Files.newBufferedWriter(usersFile.toPath())) {
             writer.write(json);
             writer.flush();
         }
     }

    public static void main(String[] args) throws IOException{

    }
}
