package calendarapp.client.backend;

public interface Savable<T> {

    // Deserializes object from json/ical
    T loadFromString(String json_text);
    // Serializes object to json/ical
    String saveToString();
}
