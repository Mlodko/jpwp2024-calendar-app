package client.backend;

public interface Saveable {

    // Deserializes object from json/ical
    void load(String json_text);
    // Serializes object to json/ical
    String save();
}
