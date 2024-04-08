package client.backend.serialization;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import javafx.scene.paint.Color;

import java.lang.reflect.Type;

public class ColorSerializer implements JsonSerializer<Color> {
    public JsonElement serialize(Color color, Type typeOfColor, JsonSerializationContext context){
        return new JsonPrimitive(color.toString());
    }
}
