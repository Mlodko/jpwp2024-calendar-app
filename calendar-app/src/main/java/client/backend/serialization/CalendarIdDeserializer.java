package client.backend.serialization;

import client.backend.models.Calendar;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

public class CalendarIdDeserializer implements JsonDeserializer<Calendar> {

    @Override
    public Calendar deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        return new Calendar(json.getAsJsonPrimitive().getAsString());
    }
}
