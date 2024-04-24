package client.backend.models;

import com.google.gson.GsonBuilder;

public interface Savable<T> {

    // Deserializes object from json
    T loadFromString(String json_text);

    // Serializes object to json
    String saveToString();
}
