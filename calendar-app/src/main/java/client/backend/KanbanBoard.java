package client.backend;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Date;

public class KanbanBoard implements Saveable {

    String id;
    String title; // Not md! >:(
    // TODO [MAYBE] String description; ???
    Date createTime;
    Date lastModifiedTime;
    Calendar calendar;
    HashMap<String, ArrayList<KanbanInsertable>> itemsLists;

    // TODO ArrayList<User> users; +their permissions???


    public void load(String json) {
        // TODO
    }

    public String save() {
        // TODO
        return null;
    }
}

