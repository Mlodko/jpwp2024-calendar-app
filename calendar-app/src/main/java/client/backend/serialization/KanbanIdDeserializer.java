package client.backend.serialization;

import client.backend.models.KanbanBoard;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

public class KanbanIdDeserializer implements JsonDeserializer<KanbanBoard> {
    @Override
    public KanbanBoard deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        return new KanbanBoard(jsonElement.getAsJsonPrimitive().getAsString());
    }
}
