package client.backend.models;

import client.backend.serialization.CardIdDeserializer;
import client.backend.serialization.CardIdSerializer;
import client.backend.serialization.KanbanIdDeserializer;
import client.backend.serialization.KanbanIdSerializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.function.Predicate;

public class Calendar implements Savable<Calendar> {

    @Expose private String id;
    private ArrayList<KanbanBoard> kanbanBoards;
    private ArrayList<Card> orphanCards;

    //TODO ArrayList<User> users;

    public Calendar(String id) {
        this.id = id;
    }

    public Calendar() {}

    public Calendar(String id, ArrayList<Card> orphanCards, ArrayList<KanbanBoard> kanbanBoards) {
        this.id = id;
        this.orphanCards = orphanCards;
        this.kanbanBoards = kanbanBoards;
    }

    public static Calendar createNew() {
        return new Calendar(UUID.randomUUID().toString());
    }

    public void setID(String newID) {
        this.id = newID;
    }

    public String getID() {
        return this.id;
    }

    public ArrayList<KanbanBoard> getKanbanBoards() {
        return this.kanbanBoards;
    }

    public void setKanbanBoards(ArrayList<KanbanBoard> newKanbanBoards) {
        this.kanbanBoards = newKanbanBoards;
    }

    public void setKanbanBoard(KanbanBoard newSingleKanbanBoard, int index) {
        this.kanbanBoards.set(index, newSingleKanbanBoard);
    }

    public void addToKanbanBoards(KanbanBoard board) {
        this.kanbanBoards.add(board);
    }

    public KanbanBoard[] deleteFromKanbanBoards(Predicate<KanbanBoard> filterFunction) {
        KanbanBoard[] removedBoards = this.kanbanBoards.stream().filter(filterFunction).toArray(KanbanBoard[]::new);
        this.kanbanBoards.removeIf(filterFunction);
        return removedBoards;
    }

    public ArrayList<Card> getOrphanCards() {
        return this.orphanCards;
    }

    public void setOrphanCards(ArrayList<Card> newOrphanCards) {
        this.orphanCards = newOrphanCards;
    }

    public void setOrphanCard(Card card, int index) {
        this.orphanCards.set(index, card);
    }

    public void addToOrphanCards(Card newOrphan) {
        this.orphanCards.add(newOrphan);
    }

    public Card[] deleteFromOrphanCards(Predicate<Card> filterFunc) {
        Card[] removedOrphans = this.orphanCards.stream().filter(filterFunc).toArray(Card[]::new);
        this.orphanCards.removeIf(filterFunc);
        return removedOrphans;
    }

    public boolean moveCardToKanban(Card card, KanbanBoard board, String kanbanColumnTitle) {
        Card[] cards = deleteFromOrphanCards(current_card -> current_card.getId().equals(card.getId()));

        // TODO maybe revisit
        try {
            board.addToItemsList(kanbanColumnTitle, new ArrayList<>(Arrays.asList(cards)));
        } catch (NoSuchElementException e) {
            return false;
        }
        return true;
    }

    @Override
    public Calendar loadFromString(String json_text) {
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(Card.class, new CardIdDeserializer());
        builder.registerTypeAdapter(KanbanBoard.class, new KanbanIdDeserializer());
        Gson gson = builder.create();

        return gson.fromJson(json_text, Calendar.class);
    }

    @Override
    public String saveToString() {
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(Card.class, new CardIdSerializer());
        builder.registerTypeAdapter(KanbanBoard.class, new KanbanIdSerializer());
        Gson gson = builder.create();

        return gson.toJson(this);
    }

}

