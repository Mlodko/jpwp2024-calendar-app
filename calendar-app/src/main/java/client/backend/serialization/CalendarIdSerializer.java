package client.backend.serialization;

import client.backend.models.Calendar;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

public class CalendarIdSerializer implements JsonSerializer<Calendar> {
    @Override
    public JsonElement serialize(Calendar calendar, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonElement() {
            @Override
            public JsonElement deepCopy() {
                return null;
            }
        };
    }
}
