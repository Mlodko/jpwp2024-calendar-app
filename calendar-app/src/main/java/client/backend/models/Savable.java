package client.backend.models;

public interface Savable<T> {

    // Deserializes object from json
    T loadFromString(String json_text);

    // Serializes object to json
    String saveToString();
}
