package client.backend.serialization;

import client.backend.models.Card;
import com.google.gson.*;

import java.lang.reflect.Type;

public class CardIdSerializer implements JsonSerializer<Card> {
    @Override
    public JsonElement serialize(Card card, Type type, JsonSerializationContext jsonSerializationContext) {
        return new JsonPrimitive(card.getId());
    }
}



