package client.backend.models;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;


public class KanbanBoard implements Savable<KanbanBoard> {

    // TODO ArrayList<User> users; +their permissions???
    // TODO [MAYBE] String description; ???

    @Expose String id;
    @Expose String title; // Not md! >:(
    @Expose Date createTime;
    @Expose Date lastModifiedTime;
    @Expose Calendar calendar;

    // This is a structure that contains all "columns"/sub-lists of a kanban board
    // Title -> ArrayList<Item>
    @Expose HashMap<String, ArrayList<KanbanInsertable>> itemsLists;

    public KanbanBoard(String id) {
        this.id = id;
    }

    public KanbanBoard(String id, String title, Date createTime, Date lastModifiedTime, Calendar calendar,
                       HashMap<String, ArrayList<KanbanInsertable>> itemsLists) {
        this.id = id;
        this.title = title;
        this.createTime = createTime;
        this.lastModifiedTime = lastModifiedTime;
        this.calendar = calendar;
        this.itemsLists = itemsLists;
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
    }

    public HashMap<String, ArrayList<KanbanInsertable>> getItemsLists() {
        return itemsLists;
    }

    public void setItemsLists(HashMap<String, ArrayList<KanbanInsertable>> itemsLists) {
        this.itemsLists = itemsLists;
    }

    public void addToItemsList(String columnTitle, KanbanInsertable item) throws java.util.NoSuchElementException{
        if(!itemsLists.containsKey(columnTitle)) {
            throw new java.util.NoSuchElementException("The item list with title " + columnTitle + " doesn't exist." +
                    "\nTry calling addNewItemColumn()" );
        }
        itemsLists.get(columnTitle).add(item);
    }

    public void addToItemsList(String columnTitle, ArrayList<KanbanInsertable> items) throws java.util.NoSuchElementException{
        if(!itemsLists.containsKey(columnTitle)) {
            throw new java.util.NoSuchElementException("The item list with title " + columnTitle + " doesn't exist." +
                    "\nTry calling addNewItemColumn()" );
        }
        itemsLists.get(columnTitle).addAll(items);
    }

    public void addNewItemColumn(String columnTitle) throws IllegalArgumentException{
        if(itemsLists.containsKey(columnTitle)) {
            throw new IllegalArgumentException("The item list with title " + columnTitle + " already exists." +
                    "\nTry calling addToItemsList()");
        }
        itemsLists.put(columnTitle, new ArrayList<KanbanInsertable>());
    }

    public void addNewItemColumn(String columnTitle, ArrayList<KanbanInsertable> items) {
        if (itemsLists.containsKey(columnTitle)) {
            throw new IllegalArgumentException("The item list with title " + columnTitle + " already exists." +
                    "\nTry calling addToItemsList()");
        }
        itemsLists.put(columnTitle, items);
    }

    @Override
    public KanbanBoard loadFromString(String json) {
        return new Gson().fromJson(json, KanbanBoard.class);
    }

    @Override
    public String saveToString() {
        return new Gson().toJson(this);
    }
}