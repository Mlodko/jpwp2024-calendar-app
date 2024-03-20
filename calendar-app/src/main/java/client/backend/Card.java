package client.backend;

import java.util.Date;
import javafx.scene.paint.Color;

public class Card implements Saveable, KanbanInsertable {

    String id;
    String title; // not yet MD.....

    // TODO ArrayList<User> assigned_idiots;
    // TODO ArrayList<GitTask> linked_from_github;
    // TODO [MAYBE] ArrayList<Tag> tags;

    String description; // Markdown!! :-)))))))))))
    Date deadline;
    Date creationTime;
    Date lastModifyTime;
    Color labelColor;


    public void load(String json_text) {
        // TODO
    }

    public String save(){
        // TODO
        return null;
    };

    public void display() {
        // TODO
        return;
    }
}
