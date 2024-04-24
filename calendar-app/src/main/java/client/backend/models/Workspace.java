package client.backend.models;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class Workspace implements Savable<Workspace>{
    @Expose
    String id;
    @Expose
    String name;
    @Expose
    String description;
    @Expose
    ArrayList<String> calendarIds;
    @Expose
    ArrayList<String> memberIds;

    ArrayList<Calendar> calendars;
    ArrayList<User> members;

    static Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();

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

    public Workspace setCalendars(ArrayList<Calendar> calendars) {
        this.calendars = calendars;
        this.calendarIds = calendars.stream().map(Calendar::getID).collect(Collectors.toCollection(ArrayList::new));
        return this;
    }

    public Workspace addToCalendars(Calendar calendar) {
        this.calendars.add(calendar);
        this.calendarIds.add(calendar.getID());
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

    //endregion
}
