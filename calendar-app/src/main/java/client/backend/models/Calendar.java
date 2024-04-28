package client.backend.models;

import client.backend.serialization.CardIdDeserializer;
import client.backend.serialization.CardIdSerializer;
import client.backend.serialization.KanbanIdDeserializer;
import client.backend.serialization.KanbanIdSerializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Calendar implements Savable<Calendar> {

    @Expose private String id;
    private ArrayList<KanbanBoard> kanbanBoards;
    @Expose private ArrayList<String> kanbanIds;
    private ArrayList<Card> orphanCards;
    @Expose private ArrayList<String> orphanCardIds;

    private ArrayList<User> members;

    @Expose private ArrayList<String> memberIds;

    private Workspace workspace;

    @Expose private String workspaceId;

    private final static Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();

    //TODO ArrayList<User> users;

    public Calendar(String id) {
        this.id = id;
    }

    public Calendar() {
        this.id = UUID.randomUUID().toString();
    }

    public Calendar(String id, ArrayList<Card> orphanCards, ArrayList<KanbanBoard> kanbanBoards, ArrayList<User> members, Workspace workspace) {
        this.id = id;
        this.orphanCards = orphanCards;
        this.kanbanBoards = kanbanBoards;
        this.orphanCardIds = orphanCards.stream().map(Card::getId).collect(Collectors.toCollection(ArrayList::new));
        this.kanbanIds = kanbanBoards.stream().map(KanbanBoard::getId).collect(Collectors.toCollection(ArrayList::new));
        this.members = members;
        this.memberIds = members.stream().map(User::getId).collect(Collectors.toCollection(ArrayList::new));
        this.workspace = workspace;
        this.workspaceId = workspace.getId();
    }

    //region Getters/setters
    public Calendar setID(String newID) {
        this.id = newID;
        return this;
    }

    public String getID() {
        return this.id;
    }

    public ArrayList<KanbanBoard> getKanbanBoards() {
        return this.kanbanBoards;
    }

    public List<String> getKanbanIds() { return this.kanbanIds; }

    public Calendar setKanbanBoards(ArrayList<KanbanBoard> newKanbanBoards) {
        this.kanbanBoards = newKanbanBoards;
        this.kanbanIds = this.kanbanBoards.stream().map(KanbanBoard::getId).collect(Collectors.toCollection(ArrayList::new));
        return this;
    }

    public Calendar setKanbanBoard(KanbanBoard newSingleKanbanBoard, int index) {
        this.kanbanBoards.set(index, newSingleKanbanBoard);
        this.kanbanIds.set(index, newSingleKanbanBoard.getId());
        return this;
    }

    public Calendar addToKanbanBoards(KanbanBoard board) {
        this.kanbanBoards.add(board);
        this.kanbanIds.add(board.id);
        return this;
    }

    public KanbanBoard[] deleteFromKanbanBoards(Predicate<KanbanBoard> filterFunction) {
        KanbanBoard[] removedBoards = this.kanbanBoards.stream().filter(filterFunction).toArray(KanbanBoard[]::new);
        this.kanbanBoards.removeIf(filterFunction);
        this.kanbanIds = this.kanbanBoards.stream().map(KanbanBoard::getId).collect(Collectors.toCollection(ArrayList::new));
        return removedBoards;
    }

    public ArrayList<Card> getOrphanCards() {
        return this.orphanCards;
    }

    public Calendar setOrphanCards(ArrayList<Card> newOrphanCards) {
        this.orphanCards = newOrphanCards;
        this.orphanCardIds = newOrphanCards.stream().map(Card::getId).collect(Collectors.toCollection(ArrayList::new));
        return this;
    }

    public Calendar setOrphanCard(Card card, int index) {
        this.orphanCards.set(index, card);
        this.orphanCardIds.set(index, card.getId());
        return this;
    }

    public Calendar addToOrphanCards(Card newOrphan) {
        this.orphanCards.add(newOrphan);
        this.orphanCardIds.add(newOrphan.getId());
        return this;
    }

    public Card[] deleteFromOrphanCards(Predicate<Card> filterFunc) {
        Card[] removedOrphans = this.orphanCards.stream().filter(filterFunc).toArray(Card[]::new);
        this.orphanCards.removeIf(filterFunc);
        this.orphanCardIds = orphanCards.stream().map(Card::getId).collect(Collectors.toCollection(ArrayList::new));
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

    public ArrayList<User> getMembers() {
        return members;
    }

    public Calendar setMembers(ArrayList<User> members) {
        this.members = members;
        this.memberIds = members.stream().map(User::getId).collect(Collectors.toCollection(ArrayList::new));
        return this;
    }

    public Workspace getWorkspace() {
        return this.workspace;
    }

    public Calendar setWorkspace(Workspace workspace) {
        workspace.addToCalendars(this);
        this.workspace = workspace;
        this.workspaceId = workspace.getId();
        return this;
    }
    //endregion

    @Override
    public Calendar loadFromString(String json_text) {
        return gson.fromJson(json_text, Calendar.class);
    }

    @Override
    public String saveToString() {
        return gson.toJson(this);
    }

}

