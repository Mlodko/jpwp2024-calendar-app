package client.backend.models;

import client.backend.serialization.CardIdSerializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;


public class KanbanBoard implements Savable<KanbanBoard> {

    // TODO ArrayList<User> users; +their permissions???
    // TODO [MAYBE] String description; ???

    @Expose String id;
    @Expose String title; // Not md! >:(
    @Expose Date createTime;
    @Expose Date lastModifiedTime;
    Calendar calendar;
    @Expose private String calendarId;

    // This is a structure that contains all "columns"/sub-lists of a kanban board
    // Title -> ArrayList<Item>
    HashMap<String, ArrayList<Card>> itemsLists;
    @Expose private HashMap<String, ArrayList<String>> itemIds;

    @Expose Date startTime;
    @Expose Date endTime;

    public KanbanBoard(String id) {this.id = id;}

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

    public String getId() {
        return this.id;
    }

    public void setId(String newId) {
        this.id = newId;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String newTitle) {
        this.title = newTitle;
    }

    public Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Date newCreateTime) {
        this.createTime = newCreateTime;
    }

    public void setCreateTime(String newCreateTimeString) throws ParseException {
        this.createTime = new SimpleDateFormat().parse(newCreateTimeString);
    }

    public void setLastModifiedTime(Date newLastModifiedTime) {
        this.lastModifiedTime = newLastModifiedTime;
    }

    public void setLastModifiedTime(String newLastModifiedTime) throws ParseException {
        this.lastModifiedTime = new SimpleDateFormat().parse(newLastModifiedTime);
    }

    public Date getLastModifiedTime() {
        return this.lastModifiedTime;
    }

    public Calendar getCalendar() {
        return calendar;
    }

    public void setCalendar(Calendar calendar) {
        this.calendar = calendar;
        this.calendarId = calendar.getID();
    }

    public HashMap<String, ArrayList<Card>> getItemsLists() {
        return itemsLists;
    }

    public void setItemsLists(HashMap<String, ArrayList<Card>> itemsLists) {
        this.itemsLists = itemsLists;
        itemsLists.forEach((columnTitle, items) -> {
            this.itemIds.put(columnTitle, items.stream().map(Card::getId).collect(Collectors.toCollection(ArrayList::new)));
        });
    }

    public void addToItemsList(String columnTitle, Card item) throws java.util.NoSuchElementException{
        if(!itemsLists.containsKey(columnTitle)) {
            throw new java.util.NoSuchElementException("The item list with title " + columnTitle + " doesn't exist." +
                    "\nTry calling addNewItemColumn()" );
        }
        itemsLists.get(columnTitle).add(item);
        itemIds.get(columnTitle).add(item.getId());
    }

    public void addToItemsList(String columnTitle, ArrayList<Card> items) throws java.util.NoSuchElementException{
        if(!itemsLists.containsKey(columnTitle)) {
            throw new java.util.NoSuchElementException("The item list with title " + columnTitle + " doesn't exist." +
                    "\nTry calling addNewItemColumn()" );
        }
        itemsLists.get(columnTitle).addAll(items);
        itemIds.get(columnTitle).addAll(items.stream().map(Card::getId).toList());
    }

    public void addNewItemColumn(String columnTitle) throws IllegalArgumentException{
        if(itemsLists.containsKey(columnTitle)) {
            throw new IllegalArgumentException("The item list with title " + columnTitle + " already exists." +
                    "\nTry calling addToItemsList()");
        }
        itemsLists.put(columnTitle, new ArrayList<Card>());
        itemIds.put(columnTitle, new ArrayList<>());
    }

    public void addNewItemColumn(String columnTitle, ArrayList<Card> items) {
        if (itemsLists.containsKey(columnTitle)) {
            throw new IllegalArgumentException("The item list with title " + columnTitle + " already exists." +
                    "\nTry calling addToItemsList()");
        }
        itemsLists.put(columnTitle, items);
        itemIds.put(columnTitle, items.stream().map(Card::getId).collect(Collectors.toCollection(ArrayList::new)));
    }

    public HashMap<String, ArrayList<String>> getItemIds() {
        return this.itemIds;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

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