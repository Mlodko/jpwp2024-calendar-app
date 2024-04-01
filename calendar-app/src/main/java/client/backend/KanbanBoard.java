package calendarapp.client.backend;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;


public class KanbanBoard implements Savable<KanbanBoard> {

    @Expose String id;

    public String getId() {
        return this.id;
    }

    public void setId(String newId) {
        this.id = newId;
    }

    @Expose String title; // Not md! >:(

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String newTitle) {
        this.title = newTitle;
    }

    // TODO [MAYBE] String description; ???
    @Expose Date createTime;

    public Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Date newCreateTime) {
        this.createTime = newCreateTime;
    }

    public void setCreateTime(String newCreateTimeString) throws ParseException {
        this.createTime = new SimpleDateFormat().parse(newCreateTimeString);
    }
    @Expose Date lastModifiedTime;

    public void setLastModifiedTime(Date newLastModifiedTime) {
        this.lastModifiedTime = newLastModifiedTime;
    }

    public void setLastModifiedTime(String newLastModifiedTime) throws ParseException {
        this.lastModifiedTime = new SimpleDateFormat().parse(newLastModifiedTime);
    }

    public Date getLastModifiedTime() {
        return this.lastModifiedTime;
    }
    @Expose Calendar calendar;

    public Calendar getCalendar() {
        return calendar;
    }

    public void setCalendar(Calendar calendar) {
        this.calendar = calendar;
    }

    @Expose HashMap<String, ArrayList<KanbanInsertable>> itemsLists;

    public HashMap<String, ArrayList<KanbanInsertable>> getItemsLists() {
        return itemsLists;
    }

    public void setItemsLists(HashMap<String, ArrayList<KanbanInsertable>> itemsLists) {
        this.itemsLists = itemsLists;
    }

    // TODO ArrayList<User> users; +their permissions???

    public KanbanBoard(String id, String title, Date createTime, Date lastModifiedTime, Calendar calendar,
                       HashMap<String, ArrayList<KanbanInsertable>> itemsLists) {
        this.id = id;
        this.title = title;
        this.createTime = createTime;
        this.lastModifiedTime = lastModifiedTime;
        this.calendar = calendar;
        this.itemsLists = itemsLists;
    }

    public KanbanBoard loadFromString(String json) {
        return new Gson().fromJson(json, KanbanBoard.class);
    }

    public String saveToString() {
        return new Gson().toJson(this);
    }
}