package client.backend.models;

import com.google.gson.*;
import javafx.scene.paint.Color;

import java.lang.reflect.Type;

class CardSerializer implements JsonSerializer<Card> {
    @Override
    public JsonElement serialize(Card card, Type type, JsonSerializationContext jsonSerializationContext) {
        return new JsonPrimitive(card.getId());
    }
}

class CardDeserializer implements JsonDeserializer<Card> {

    @Override
    public Card deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        return new Card(jsonElement.getAsJsonPrimitive().getAsString());
    }
}

class KanbanSerializer implements JsonSerializer<KanbanBoard> {
    @Override
    public JsonElement serialize(KanbanBoard kanbanBoard, Type type, JsonSerializationContext jsonSerializationContext) {
        return new JsonPrimitive(kanbanBoard.id);
    }
}

class KanbanDeserializer implements JsonDeserializer<KanbanBoard> {
    @Override
    public KanbanBoard deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        return new KanbanBoard(jsonElement.getAsJsonPrimitive().getAsString());
    }
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
