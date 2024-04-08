package client.backend.serialization;

import client.backend.models.KanbanBoard;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

public class KanbanIdSerializer implements JsonSerializer<KanbanBoard> {
    @Override
    public JsonElement serialize(KanbanBoard kanbanBoard, Type type, JsonSerializationContext jsonSerializationContext) {
        return new JsonPrimitive(kanbanBoard.getId());
    }
}
