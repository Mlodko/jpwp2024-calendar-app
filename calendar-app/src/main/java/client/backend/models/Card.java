package client.backend.models;

import client.backend.serialization.ColorDeserializer;
import client.backend.serialization.ColorSerializer;
import com.google.gson.*;
import com.google.gson.annotations.Expose;
import javafx.scene.paint.Color;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import java.util.UUID;

/*
    TODO - it all comes with FILE PATHS ISSUE:
        - [ ] single kanban's data reading from JSON and upgrading/writing into JSON
        - [ ] single card's data reading from JSON and upgrading/writing into JSON
        - [ ] single calendar's data reading from JSON and upgrading/writing into JSON
 */


public class Card implements Savable<Card> {
    // fields with getters and setters:

    @Expose
    private String id;
    @Expose
    private String title; // not yet MD.....
    @Expose
    private String description; // Markdown!! :-)))))))))))
    @Expose
    private Date startTime;
    @Expose
    private Date endTime;
    @Expose
    private Date creationTime;
    @Expose
    private Date lastModifyTime;
    @Expose
    private ArrayList<String> assignedUserIds;

    private ArrayList<User> assignedUsers;

    public Card(String id) {
        this.id = id;
    }

    public Card(String id, String title, String desc, Date creationTime, Date lastModifyTime, Date startTime, Date endTime) {
        this.id = id;
        this.title = title;
        this.description = desc;
        this.startTime = startTime;
        this.endTime = endTime;
        this.creationTime = creationTime;
        this.lastModifyTime = lastModifyTime;
    }

    public Card(String title, String description, Date startTime, Date endTime) {
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
        this.creationTime = new Date();
        this.lastModifyTime = new Date();
    }

    public Card() {
        this.id = UUID.randomUUID().toString();
    }

    //region Getters/setters
    public String getId() {
        return this.id;
    }

    public Card setID(String newID) {
        this.id = newID;
        this.lastModifyTime = new Date();
        return this;
    }
    public String getTitle() {
        return this.title;
    }

    public Card setTitle(String newTitle) {
        this.title = newTitle;
        this.lastModifyTime = new Date();
        return this;
    }

    public String getDescription() {
        return this.description;
    }

    public Card setDescription(String newDescription) {
        this.description = newDescription;
        this.lastModifyTime = new Date();
        return this;
    }

    public Date getEndTime() {
        return this.endTime;
    }

    public Card setEndTime(Date newDeadline) {
        this.endTime = newDeadline;
        this.lastModifyTime = new Date();
        return this;
    }

    public Card setEndTime(String newDeadlineString) throws ParseException {
        this.endTime = new SimpleDateFormat().parse(newDeadlineString);
        this.lastModifyTime = new Date();
        return this;
    }

    public Date getStartTime() {
        return startTime;
    }

    public Card setStartTime(Date startTime) {
        this.startTime = startTime;
        this.lastModifyTime = new Date();
        return this;
    }

    public Card setStartTime(String newStartTimeString) throws ParseException {
        this.startTime = new SimpleDateFormat().parse(newStartTimeString);
        this.lastModifyTime = new Date();
        return this;
    }

    public Date getCreationTime() {
        return this.creationTime;
    }

    public Card setCreationTime(Date newCreationTime) {
        this.creationTime = newCreationTime;
        this.lastModifyTime = new Date();
        return this;
    }

    public Date getLastModifyTime() {
        return this.lastModifyTime;
    }
    //endregion

    public boolean hasStartAndEndDate() {
        return this.startTime != null && this.endTime != null;
    }
    // other methods:




    public Card loadFromString(String json_text) {
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(Color.class, new ColorDeserializer());
        Gson gson = builder.create();

        return gson.fromJson(json_text, Card.class);
    }

    public String saveToString() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Color.class, new ColorSerializer());
        Gson gson = gsonBuilder.create();
        return gson.toJson(this);
    }



    /*
    public static Card main(String[] args) {
        Card card = new Card();
        card.id = "7yrfhq3ojicf";
        card.title = "Jebać Javę";
        card.endTime = new Date();
        card.labelColor = Color.RED;

        System.out.println(card.saveToString());

        Card new_card = new Card().loadFromString(card.saveToString());

        System.out.println(new_card.saveToString());
    }
    */

    public static void main(String[] args) {
        UUID uuid = UUID.randomUUID();
        System.out.println(uuid.toString());
        //System.out.println(new Date(uuid.timestamp()).toString());
    }
}



