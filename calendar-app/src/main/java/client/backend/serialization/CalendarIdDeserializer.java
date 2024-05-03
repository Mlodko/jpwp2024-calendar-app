package client.backend.serialization;

import client.backend.models.Calendar;
import client.backend.models.Card;
import client.backend.models.KanbanBoard;
import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class CalendarIdDeserializer implements JsonDeserializer<Calendar> {

    @Override
    public Calendar deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Calendar calendar = new Calendar();
        JsonObject jsonObject = json.getAsJsonObject();
        calendar.setID(jsonObject.get("id").getAsString());
        ArrayList<KanbanBoard> boards = new ArrayList<>();

        for (JsonElement boardId : jsonObject.getAsJsonArray("kanbanIds").asList()) {
            boards.add(new KanbanBoard(boardId.getAsString()));
        }

        calendar.setKanbanBoards(boards);

        ArrayList<Card> cards = new ArrayList<>();

        calendar.setOrphanCards(cards);


        return calendar;
    }
}

