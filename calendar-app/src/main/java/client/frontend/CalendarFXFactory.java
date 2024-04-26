 package client.frontend;

import client.backend.models.Card;
import client.backend.models.KanbanBoard;
import com.calendarfx.model.Calendar;
import com.calendarfx.model.CalendarSource;
import com.calendarfx.model.Entry;
import com.calendarfx.model.Interval;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

public abstract class CalendarFXFactory {

    public static CalendarSource create(client.backend.models.Calendar myCalendar) {

        CalendarSource source = new CalendarSource(myCalendar.getID());
        // Add all kanbans from myCalendar as entries
        Calendar kanbanCalendar = new Calendar("Kanban Boards");
        kanbanCalendar.setStyle(Calendar.Style.STYLE1);

        ArrayList<Entry> kanbanEntries = myCalendar.getKanbanBoards().stream()
            .filter(KanbanBoard::hasStartAndEndDate)
            .map(kanbanBoard -> {
                Interval interval = new Interval(
                    LocalDateTime.ofInstant(kanbanBoard.getStartTime().toInstant(), ZoneId.systemDefault()),
                    LocalDateTime.ofInstant(kanbanBoard.getEndTime().toInstant(), ZoneId.systemDefault()),
                    ZoneId.systemDefault());
                return new Entry(kanbanBoard.getTitle(), interval);
        }).collect(Collectors.toCollection(ArrayList::new));

        kanbanCalendar.addEntries(kanbanEntries);

        // Add all cards from calendar's kanbans
        Calendar cardCalendar = new Calendar("Cards");
        cardCalendar.setStyle(Calendar.Style.STYLE2);
        ArrayList<Entry> cardEntries = new ArrayList<>();

        for(KanbanBoard board : myCalendar.getKanbanBoards()) {
            cardEntries.addAll(board.getItemsLists().values().stream().flatMap(Collection::stream)
                .filter(Card::hasStartAndEndDate)
                .map(card -> {
                    Interval interval = new Interval(
                         LocalDateTime.ofInstant(card.getStartTime().toInstant(), ZoneId.systemDefault()),
                         LocalDateTime.ofInstant(card.getStartTime().toInstant(), ZoneId.systemDefault()),
                         ZoneId.systemDefault()
                    );
                    return new Entry(card.getTitle(), interval);
                }).collect(Collectors.toCollection(ArrayList::new)));
        }

        //Add from orphan cards
        cardEntries.addAll(myCalendar.getOrphanCards().stream()
                .filter(Card::hasStartAndEndDate)
                .map(card -> {
                    Interval interval = new Interval(
                            LocalDateTime.ofInstant(card.getStartTime().toInstant(), ZoneId.systemDefault()),
                            LocalDateTime.ofInstant(card.getEndTime().toInstant(), ZoneId.systemDefault()),
                            ZoneId.systemDefault()
                    );
                    return new Entry(card.getTitle(), interval);
                }).collect(Collectors.toCollection(ArrayList::new))
        );

        cardCalendar.addEntries(cardEntries);

        source.getCalendars().add(kanbanCalendar);
        source.getCalendars().add(cardCalendar);

        return source;
    }

}
