package client.backend.models;

import client.backend.serialization.CardIdSerializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;


public class KanbanBoard implements Savable<KanbanBoard> {
    @Expose String id = new String();
    @Expose String title = new String();; // Not md! >:(
    @Expose Date createTime = new Date();
    @Expose Date lastModifiedTime = new Date();
    @Expose String calendarId = new String();;

    @Expose HashMap<String, ArrayList<String>> itemIds = new HashMap<>();
    @Expose Date startTime = new Date();
    @Expose Date endTime = new Date();
    Calendar calendar;
    HashMap<String, ArrayList<Card>> itemsLists = new HashMap<>();
    // This is a structure that contains all "columns"/sub-lists of a kanban board
    // Title -> ArrayList<Item>

    public KanbanBoard(String id) {this.id = id;}
    
    public KanbanBoard() {this.id = UUID.randomUUID().toString();}

    public KanbanBoard(String id, String title, Date createTime, Date lastModifiedTime, Calendar calendar, Date startTime,
                       Date endTime, HashMap<String, ArrayList<Card>> itemsLists) {
        this.id = id;
        this.title = title;
        this.createTime = createTime;
        this.lastModifiedTime = lastModifiedTime;
        this.calendar = calendar;
        this.calendarId = calendar.getID();
        this.itemsLists = itemsLists;
        this.itemIds = new HashMap<>();
        this.startTime = startTime;
        this.endTime = endTime;
        itemsLists.forEach((columnTitle, items) -> {
            itemIds.put(columnTitle, items.stream().map(Card::getId).collect(Collectors.toCollection(ArrayList::new)));
        });
    }


    //region Getters/setters
    public String getId() {
        return this.id;
    }

    public KanbanBoard setId(String newId) {
        this.id = newId;
        this.lastModifiedTime = new Date();
        return this;
    }

    public String getTitle() {
        return this.title;
    }

    public KanbanBoard setTitle(String newTitle) {
        this.title = newTitle;
        this.lastModifiedTime = new Date();
        return this;
    }

    public Date getCreateTime() {
        return this.createTime;
    }

    public KanbanBoard setCreateTime(Date newCreateTime) {
        this.createTime = newCreateTime;
        this.lastModifiedTime = new Date();
        return this;
    }

    public KanbanBoard setCreateTime(String newCreateTimeString) throws ParseException {
        this.createTime = new SimpleDateFormat().parse(newCreateTimeString);
        this.lastModifiedTime = new Date();
        return this;
    }

    public Date getLastModifiedTime() {
        return this.lastModifiedTime;
    }

    public Calendar getCalendar() {
        return calendar;
    }

    public KanbanBoard setCalendar(Calendar calendar) {
        this.calendar = calendar;
        this.calendarId = calendar.getID();
        this.lastModifiedTime = new Date();
        return this;
    }

    public String getCalendarId() { return this.calendarId;}

    public HashMap<String, ArrayList<Card>> getItemsLists() {
        return itemsLists;
    }

    public KanbanBoard setItemsLists(HashMap<String, ArrayList<Card>> itemsLists) {
        this.itemsLists = itemsLists;
        this.lastModifiedTime = new Date();
        itemsLists.forEach((columnTitle, items) -> {
            this.itemIds.put(columnTitle, items.stream().map(Card::getId).collect(Collectors.toCollection(ArrayList::new)));
        });
        return this;
    }

    public KanbanBoard addToItemsList(String columnTitle, Card item) throws java.util.NoSuchElementException{
        if(!itemsLists.containsKey(columnTitle)) {
            throw new java.util.NoSuchElementException("The item list with title " + columnTitle + " doesn't exist." +
                    "\nTry calling addNewItemColumn()" );
        }
        itemsLists.get(columnTitle).add(item);
        itemIds.get(columnTitle).add(item.getId());
        this.lastModifiedTime = new Date();
        return this;
    }

    public KanbanBoard addToItemsList(String columnTitle, ArrayList<Card> items) throws java.util.NoSuchElementException{
        if(this.itemsLists == null) {
            this.itemsLists = new HashMap<>();
            this.itemsLists.put(columnTitle, items);
            this.lastModifiedTime = new Date();
            return this;
        }

        if(!itemsLists.containsKey(columnTitle)) {
            throw new java.util.NoSuchElementException("The item list with title " + columnTitle + " doesn't exist." +
                    "\nTry calling addNewItemColumn()" );
        }

        itemsLists.get(columnTitle).addAll(items);
        itemIds.get(columnTitle).addAll(items.stream().map(Card::getId).toList());
        this.lastModifiedTime = new Date();
        return this;
    }

    public KanbanBoard addNewItemColumn(String columnTitle) throws IllegalArgumentException{
        if(itemsLists == null) itemsLists = new HashMap<>();
        if(itemIds == null) itemIds = new HashMap<>();
        if(itemsLists.containsKey(columnTitle)) {
            throw new IllegalArgumentException("The item list with title " + columnTitle + " already exists." +
                    "\nTry calling addToItemsList()");
        }
        itemsLists.put(columnTitle, new ArrayList<Card>());
        itemIds.put(columnTitle, new ArrayList<>());
        this.lastModifiedTime = new Date();
        return this;
    }

    public KanbanBoard addNewItemColumn(String columnTitle, ArrayList<Card> items) {
        if(itemsLists == null) itemsLists = new HashMap<>();
        if(itemIds == null) itemIds = new HashMap<>();
        if (itemsLists.containsKey(columnTitle)) {
            throw new IllegalArgumentException("The item list with title " + columnTitle + " already exists." +
                    "\nTry calling addToItemsList()");
        }
        itemsLists.put(columnTitle, items);
        itemIds.put(columnTitle, items.stream()
                .map(Card::getId)
                .collect(Collectors.toCollection(ArrayList::new)));
        this.lastModifiedTime = new Date();
        return this;
    }

    public boolean checkIfColumnExists(String columnTitle) {
        return itemIds.containsKey(columnTitle);
    }

    public HashMap<String, ArrayList<String>> getItemIds() {
        return this.itemIds;
    }

    public Date getStartTime() {
        return startTime;
    }

    public KanbanBoard setStartTime(Date startTime) {
        this.startTime = startTime;
        this.lastModifiedTime = new Date();
        return this;
    }

    public Date getEndTime() {
        return endTime;
    }

    public KanbanBoard setEndTime(Date endTime) {
        this.endTime = endTime;
        this.lastModifiedTime = new Date();
        return this;
    }

    public ArrayList<Card> getItems() {
        if(itemsLists == null)
            itemsLists = new HashMap<>();
        return this.itemsLists.values().stream()
                .flatMap(ArrayList::stream)
                .collect(Collectors.toCollection(ArrayList::new));
    }
    //endregion

    public boolean hasStartAndEndDate() {
        return this.startTime != null && this.endTime != null;
    }

    @Override
    public KanbanBoard loadFromString(String json) {
        return new Gson().fromJson(json, KanbanBoard.class);
    }

    @Override
    public String saveToString() {
        return new GsonBuilder().registerTypeAdapter(Card.class, new CardIdSerializer()).create().toJson(this);
    }

}