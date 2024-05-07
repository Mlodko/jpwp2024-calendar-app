package client.backend.models;

import client.backend.JsonManager;
import client.backend.RequestManager;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;

import java.util.*;
import java.util.stream.Collectors;

public class Workspace implements Savable<Workspace>{
    @Expose
    private String id = new String();
    @Expose
    private String name = new String();
    @Expose
    private String description = new String();
    @Expose
    private ArrayList<String> calendarIds = new ArrayList<>();
    @Expose
    private ArrayList<String> memberIds = new ArrayList<>();

    private ArrayList<Calendar> calendars = new ArrayList<>();
    private ArrayList<User> members = new ArrayList<>();

    public static final Workspace DEFAULT = new Workspace("DEFAULT", "DEFAULT");

    private final static Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();

    public Workspace(String id, String name, String description, ArrayList<Calendar> calendars) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.calendars = calendars;
        this.calendarIds = calendars.stream().map(Calendar::getID).collect(Collectors.toCollection(ArrayList::new));
    }

    public Workspace(String id, String name, ArrayList<String> calendarIds, String description) {
        this.id = id;
        this.name = name;
        this.calendarIds = calendarIds;
        this.description = description;
        this.calendars = new ArrayList<>();
    }

    public Workspace(String name, String description) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.description = description;
        this.calendars = new ArrayList<>();
        this.calendarIds = new ArrayList<>();
        this.members = new ArrayList<>();
        this.memberIds = new ArrayList<>();
    }

    @Override
    public Workspace loadFromString(String json_text) {
        return gson.fromJson(json_text, Workspace.class);
    }
    @Override
    public String saveToString() {
        return gson.toJson(this);
    }

    //region Getters/setters

    public String getId() {
        return id;
    }
    public Workspace setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Workspace setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Workspace setDescription(String description) {
        this.description = description;
        return this;
    }

    public ArrayList<Calendar> getCalendars() {
        return calendars;
    }

    public ArrayList<String> getCalendarIds() {
        return this.calendarIds;
    }

    public Workspace setCalendars(ArrayList<Calendar> calendars) {
        this.calendars = calendars;
        this.calendarIds = calendars.stream().map(Calendar::getID).collect(Collectors.toCollection(ArrayList::new));
        return this;
    }

    public Workspace addToCalendars(Calendar calendar) {
        if(calendars == null) this.calendars = new ArrayList<>();
        if(calendarIds == null ) this.calendarIds = new ArrayList<>();
        this.calendars.add(calendar);
        if(!calendarIds.contains(calendar.getID()))
            this.calendarIds.add(calendar.getID());
        return this;
    }

    public Workspace addToCalendars(ArrayList<Calendar> calendars) {
        if(this.calendars == null) this.calendars = new ArrayList<>();
        if(calendarIds == null ) this.calendarIds = new ArrayList<>();

        for(Calendar calendar : calendars) {
            this.calendars.add(calendar);
            if (!calendarIds.contains(calendar.getID()))
                this.calendarIds.add(calendar.getID());
        }
        return this;
    }

    public ArrayList<User> getMembers() {
        return members;
    }

    public Workspace setMembers(ArrayList<User> members) {
        this.members = members;
        this.memberIds = members.stream().map(User::getId).collect(Collectors.toCollection(ArrayList::new));
        return this;
    }

    public ArrayList<String> getMemberIds() {
        return memberIds;
    }

    //endregion

    public static Optional<Workspace> constructCompleteWorkspace(String workspaceId, String authToken) {
        try(RequestManager requestManager = new RequestManager()) {
            Optional<Workspace> optionalWorkspace = requestManager.getWorkspaces(authToken, workspaceId).orElseThrow().stream().findFirst();
            if(optionalWorkspace.isEmpty()) {
                return Optional.empty();
            }
            Workspace workspace = optionalWorkspace.get();

            workspace.calendars = requestManager.getCalendars(authToken, workspace.id, workspace.calendarIds).orElseThrow();

            for(Calendar calendar : workspace.calendars) {
                calendar.setOrphanCards(
                        requestManager.getOrphanCards(authToken, workspaceId, calendar.getID(), calendar.getOrphanIds()).orElseThrow()
                );

                calendar.setKanbanBoards(
                        requestManager.getBoards(authToken, workspaceId, calendar.getID(), calendar.getKanbanIds()).orElseThrow()
                );
                for(KanbanBoard board : calendar.getKanbanBoards()) {
                    for(String columnName : board.getItemIds().keySet()) {
                        board.addNewItemColumn(columnName);
                    }

                    board.getItemIds().forEach((columnName, cardIds) -> {
                        board.getItemsLists().put(columnName, requestManager.getKanbanCards(authToken, workspaceId,
                                calendar.getID(), board.getId(), cardIds).orElseThrow());
                    });
                }
            }

            return Optional.of(workspace);

        } catch(Exception e) {
            return Optional.empty();
        }
    }
}
