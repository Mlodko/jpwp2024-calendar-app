package server;

import client.backend.models.*;
import client.backend.serialization.ColorSerializer;
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
import java.util.Collection;
import java.util.HashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ServerJsonManager {
    private final static Path rootDir = Path.of(Paths.get("").toAbsolutePath() + "/server");

     public static String getRootDirectory() {
         return rootDir.toString();
     }

     public static void writeWorkspaceData(Workspace workspace, boolean ifWriteChildren) throws IOException {
         File calendarsFile = new File(rootDir.toString() + "/workspaces/workspace-" + workspace.getId() + "/workspace.json");

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

         if (ifWriteChildren) {
             for (Calendar calendar : workspace.getCalendars()) {
                 writeCalendarData(calendar);
             }
         }
     }

     public static void writeCalendarData(Calendar calendar) throws IOException {
         File calendarDir = new File(rootDir + "/workspaces/workspace-" + calendar.getWorkspace().getId() +
                 "/calendar-" + calendar.getID() + "/");

         if(!calendarDir.exists() && !calendarDir.getParentFile().mkdirs()) {
             throw new IOException("Unable to create directory " + calendarDir.getAbsolutePath());
         }

         writeKanbanData(calendar);
         writeCardData(calendar);
     }

     private static void writeKanbanData (Calendar calendar) throws IOException {

         File calendarFile = new File(rootDir.toString() + "/workspaces/workspace-" + calendar.getWorkspace().getId() +
                                        "/calendar-" + calendar.getID() +
                                        "/boards.json");

         if(!calendarFile.getParentFile().exists() && !calendarFile.getParentFile().mkdirs()) {
             throw new IOException("Unable to create directory " + calendarFile.getParentFile().getAbsolutePath());
         }

         if(!calendarFile.exists())
             calendarFile.createNewFile();



         Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().create();

         try(BufferedWriter writer = Files.newBufferedWriter(calendarFile.toPath())) {
             writer.write(gson.toJson(calendar.getKanbanBoards()));
             writer.flush();
         }


     }

     private static void writeCardData(Calendar calendar) throws IOException {
         File cardsFile = new File(rootDir + "/workspaces/workspace-" + calendar.getWorkspace().getId() +
                 "/calendar-" + calendar.getID() +
                 "/cards.json");
         if(!cardsFile.exists()) {
             cardsFile.createNewFile();
         }

         ArrayList<Card> cards = calendar.getKanbanBoards().stream().map(kanbanBoard -> kanbanBoard.getItemsLists().values())
                 .flatMap(Collection::stream)
                 .flatMap(ArrayList::stream)
                 .collect(Collectors.toCollection(ArrayList::new));
         cards.addAll(calendar.getOrphanCards());

         Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().create();

         try(BufferedWriter writer = Files.newBufferedWriter(cardsFile.toPath())) {
             writer.write(gson.toJson(cards));
             writer.flush();
         }
     }

     public static ArrayList<Workspace> readAllWorkspaces(){
         File workspacesDir = new File(rootDir.toString() + "/workspaces/");

         if(!workspacesDir.exists()) {
             workspacesDir.mkdirs();
             return new ArrayList<>();
         }

         ArrayList<File> workspaceDirs = Stream.of(workspacesDir.listFiles())
                 .filter(file -> file.isDirectory() && file.getName().contains("workspace"))
                 .collect(Collectors.toCollection(ArrayList::new));

         ArrayList<Workspace> workspaces = new ArrayList<>();
         Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().create();

         try {
             for (File dir : workspaceDirs) {
                 File workspaceFile = new File(dir.toPath() + "/workspace.json");
                 String jsonString = new String(Files.readAllBytes(workspaceFile.toPath()));
                 workspaces.add(readWorkspaceData(gson.fromJson(jsonString, Workspace.class)));
             }
         } catch(IOException e) {
             return new ArrayList<>();
         }

         return workspaces;
     }


     public static Workspace readWorkspaceData(Workspace workspace) throws IOException {
         File workspaceDir = new File(rootDir + "/workspaces/workspace-" + workspace.getId() + "/");

         if(!workspaceDir.exists()) {
             throw new IOException("Directory " + workspaceDir.getAbsolutePath() + " doesn't exist.");
         }

         workspace.setCalendars(workspace.getCalendars().stream()
                 .map(calendar -> {
                     try {
                         return readCalendarData(calendar);
                     } catch (IOException e) {
                         throw new RuntimeException(e);
                     }
                 }).collect(Collectors.toCollection(ArrayList::new)));

         ArrayList<User> members = readUsersData();
         ArrayList<String> memberIds = workspace.getMemberIds();
         workspace.setMembers(members.stream()
                 .filter(member -> memberIds.contains(member.getId()))
                 .collect(Collectors.toCollection(ArrayList::new)));

         return workspace;
     }

     private static Calendar readCalendarData(Calendar calendar) throws IOException {
         File calendarDir = new File(rootDir + "/workspaces/workspace-" + calendar.getWorkspace().getId() +
                 "/calendar-" + calendar.getID() + "/");

         if(!calendarDir.exists()) {
             throw new IOException("Directory " + calendarDir.getAbsolutePath() + " doesn't exist.");
         }

         ArrayList<Card> cards = readCardData(calendar);
         ArrayList<KanbanBoard> boards = readKanbanData(calendar);

         for(KanbanBoard board : boards) {
             HashMap<String, ArrayList<Card>> completeItemLists = new HashMap<>();
             board.getItemIds().forEach((columnTitle, itemIds) -> {
                 ArrayList<Card> itemList = cards.stream()
                         .filter(card -> itemIds.contains(card.getId()))
                         .collect(Collectors.toCollection(ArrayList::new));
                 completeItemLists.put(columnTitle, itemList);
             });
             board.setItemsLists(completeItemLists);
         }

         calendar.setOrphanCards(cards.stream()
                 .filter(card -> calendar.getOrphanCardIds().contains(card.getId()))
                 .collect(Collectors.toCollection(ArrayList::new)));


         return calendar;
     }

     private static ArrayList<KanbanBoard> readKanbanData(Calendar calendar) throws IOException {
         File boardsFile = new File(rootDir + "/workspaces/workspace-" + calendar.getWorkspace().getId() +
                 "/calendar-" + calendar.getID() +
                 "/boards.json");

         if(!boardsFile.exists()) {
             throw new IOException("File " + boardsFile.getAbsolutePath() + " doesn't exist.");
         }

         String boardsJson = new String(Files.readAllBytes(boardsFile.toPath()));

         Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
         Type boardArrayType = new TypeToken<ArrayList<KanbanBoard>>(){}.getType();
         return gson.fromJson(boardsJson, boardArrayType);
     }

     private static ArrayList<Card> readCardData(Calendar calendar) throws IOException {
         File cardsFile = new File(rootDir + "/workspaces/workspace-" + calendar.getWorkspace().getId() +
                 "/calendar-" + calendar.getID() +
                 "/cards.json");

         if(!cardsFile.exists()) {
             throw new IOException("File " + cardsFile.getAbsolutePath() + " doesn't exist");
         }

         String cardsJson = new String(Files.readAllBytes(cardsFile.toPath()));

         Type cardArrayType = new TypeToken<ArrayList<Card>>(){}.getType();
         Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().create();
         return gson.fromJson(cardsJson, cardArrayType);
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
