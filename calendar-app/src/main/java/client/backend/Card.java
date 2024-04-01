package calendarapp.client.backend;

import com.google.gson.*;
import com.google.gson.annotations.Expose;
import javafx.scene.paint.Color;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Card implements Savable<Card>, KanbanInsertable {

// fields with getters and setters:

    @Expose
    private String id;

    public String getId() {
        return this.id;
    }

    public void setID(String newID) {
        this.id = newID;
    }

    @Expose
    private String title; // not yet MD.....

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String newTitle) {
        this.title = newTitle;
    }


    // TODO ArrayList<User> assigned_idiots;
    // TODO ArrayList<GitTask> linked_from_github;
    // TODO [MAYBE] ArrayList<Tag> tags;

    @Expose
    private String description; // Markdown!! :-)))))))))))

    public String getDescription() {
        return this.description;
    }
    public void setDescription(String newDescription) {
        this.description = newDescription;
    }

    @Expose
    private Date deadline;
    
    public Date getDeadline() {
        return this.deadline;
    }

    public void setDeadline(Date newDeadline) {
        this.deadline = newDeadline;
    }

    public void setDeadline(String newDeadlineString) throws ParseException {
        this.deadline = new SimpleDateFormat().parse(newDeadlineString);
    }


    @Expose
    private Date creationTime;
    
    public Date getCreationTime() {
        return this.creationTime;
    }

    public void setCreationTime(Date newCreationTime) {
        this.creationTime = newCreationTime;
    }

    public void setCreationTime(String newCreationTimeString) throws ParseException {
        this.creationTime = new SimpleDateFormat().parse(newCreationTimeString);
    }

    @Expose
    private Date lastModifyTime;

    public Date getLastModifyTime() {
        return this.lastModifyTime;
    }

    public void setLastModifyTime(Date newLastModifyTime) {
        this.lastModifyTime = newLastModifyTime;
    }

    public void setLastModifyTime(String newLastModifyTimeString) throws ParseException {
        this.creationTime = new SimpleDateFormat().parse(newLastModifyTimeString);
    }

    @Expose
    public Color labelColor;

    public Color getLabelColor() {
        return this.labelColor;
    }

    public void setLabelColor(Color newLabelColor) {
        this.labelColor = newLabelColor;
    }

    public void setLabelColor(String newLabelColorString) {
        this.labelColor = Color.web(newLabelColorString);
    }


    // other methods:

    public Card(String id, String desc, Date deadline, Date creationTime, Date lastModifyTime, Color labelColor) {
        this.id = id;
        this.description = desc;
        this.deadline = deadline;
        this.creationTime = creationTime;
        this.lastModifyTime = lastModifyTime;
        this.labelColor = labelColor;
    }


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

    public void display() {
        // TODO
        return;
    }

    /*
    public static void main(String[] args) {
        Card card = new Card();
        card.id = "7yrfhq3ojicf";
        card.title = "Jebać Javę";
        card.deadline = new Date();
        card.labelColor = Color.RED;

        System.out.println(card.saveToString());

        Card new_card = new Card().loadFromString(card.saveToString());

        System.out.println(new_card.saveToString());
    }
    */
}

class ColorSerializer implements JsonSerializer<Color> {
    public JsonElement serialize(Color color, Type typeOfColor, JsonSerializationContext context){
        return new JsonPrimitive(color.toString());
    }
}

class ColorDeserializer implements JsonDeserializer<Color> {
    @Override
    public Color deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        return Color.web(jsonElement.getAsJsonPrimitive().getAsString());
    }
}

