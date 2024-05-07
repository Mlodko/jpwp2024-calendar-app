package client.backend.models;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Calendar implements Savable<Calendar> {

    @Expose private String id = new String();
    private ArrayList<KanbanBoard> kanbanBoards = new ArrayList<>();
    @Expose private ArrayList<String> kanbanIds = new ArrayList<>();
    private ArrayList<Card> orphanCards = new ArrayList<>();
    @Expose private ArrayList<String> orphanIds = new ArrayList<>();

    private ArrayList<User> members = new ArrayList<>();

    @Expose private ArrayList<String> memberIds = new ArrayList<>();

    private Workspace workspace;

    @Expose private String workspaceId = new String();

    private final static Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();

    public Calendar(String id) {
        this.id = id;
    }

    public Calendar() {
        this.id = UUID.randomUUID().toString();
    }

    public Calendar(String id, ArrayList<Card> orphanCards, ArrayList<KanbanBoard> kanbanBoards, ArrayList<User> members, Workspace workspace) {
        this.id = id;
        this.orphanCards = orphanCards;
        this.orphanIds = orphanCards.stream().map(Card::getId).collect(Collectors.toCollection(ArrayList::new));
        this.kanbanBoards = kanbanBoards;
        this.kanbanIds = kanbanBoards.stream().map(KanbanBoard::getId).collect(Collectors.toCollection(ArrayList::new));
        this.members = members;
        this.memberIds = members.stream().map(User::getId).collect(Collectors.toCollection(ArrayList::new));
        this.workspace = workspace;
        this.workspaceId = workspace.getId();
    }

    public Calendar(ArrayList<Card> orphanCards, ArrayList<KanbanBoard> kanbanBoards, ArrayList<User> users, Workspace workspace) {
        this.id = UUID.randomUUID().toString();
        this.kanbanBoards = kanbanBoards;
        this.kanbanIds = kanbanBoards.stream().map(KanbanBoard::getId).collect(Collectors.toCollection(ArrayList::new));
        this.orphanCards = orphanCards;
        this.orphanIds = orphanCards.stream().map(Card::getId).collect(Collectors.toCollection(ArrayList::new));
        this.members = users;
        this.memberIds = users.stream().map(User::getId).collect(Collectors.toCollection(ArrayList::new));
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
        if(kanbanBoards == null) kanbanBoards = new ArrayList<>();
        return this.kanbanBoards;
    }

    public List<String> getKanbanIds() {
        if (this.kanbanIds == null)
            this.kanbanIds = new ArrayList<>();

        return this.kanbanIds;
    }

    public Calendar setKanbanBoards(ArrayList<KanbanBoard> newKanbanBoards) {
        if (newKanbanBoards == null) {
            this.kanbanBoards = new ArrayList<>();
            this.kanbanIds = new ArrayList<>();
            return this;
        }

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

    public Calendar addToKanbanBoards(ArrayList<KanbanBoard> boards) {
        if(this.kanbanBoards == null) this.kanbanBoards = new ArrayList<>();
        if(this.kanbanIds == null) this.kanbanIds = new ArrayList<>();
        this.kanbanBoards.addAll(boards);
        this.kanbanIds.addAll(boards.stream().map(KanbanBoard::getId).collect(Collectors.toCollection(ArrayList::new)));
        return this;
    }

    public KanbanBoard[] deleteFromKanbanBoards(Predicate<KanbanBoard> filterFunction) {
        KanbanBoard[] removedBoards = this.kanbanBoards.stream().filter(filterFunction).toArray(KanbanBoard[]::new);
        this.kanbanBoards.removeIf(filterFunction);
        this.kanbanIds = this.kanbanBoards.stream().map(KanbanBoard::getId).collect(Collectors.toCollection(ArrayList::new));
        return removedBoards;
    }

    public ArrayList<Card> getOrphanCards() {
        if (this.orphanCards == null)
            this.orphanCards = new ArrayList<>();
        return this.orphanCards;
    }

    public ArrayList<String> getOrphanIds() {
        return orphanIds;
    }

    public Calendar setOrphanCards(ArrayList<Card> newOrphanCards) {
        this.orphanCards = newOrphanCards;
        this.orphanIds = orphanCards.stream().map(Card::getId).collect(Collectors.toCollection(ArrayList::new));

        return this;
    }

    public Calendar setOrphanCard(Card card, int index) {
        this.orphanCards.set(index, card);
        this.orphanIds.set(index, card.getId());
        return this;
    }

    public Calendar addToOrphanCards(Card newOrphan) {
        this.orphanCards.add(newOrphan);
        this.orphanIds.add(newOrphan.getId());
        return this;
    }

    public Calendar addToOrphanCards(ArrayList<Card> newOrphans) {
        if(this.orphanCards == null)
            this.orphanCards = new ArrayList<>();
        if(this.orphanIds == null)
            this.orphanIds = new ArrayList<>();
        this.orphanCards.addAll(newOrphans);
        for (Card card : newOrphans) {
            if(!orphanIds.contains(card.getId()))
                orphanIds.add(card.getId());
        }
        return this;
    }

    public Card[] deleteFromOrphanCards(Predicate<Card> filterFunc) {
        Card[] removedOrphans = this.orphanCards.stream().filter(filterFunc).toArray(Card[]::new);
        this.orphanCards.removeIf(filterFunc);
        this.orphanIds.removeIf(cardId -> Arrays.stream(removedOrphans).map(Card::getId).toList().contains(cardId));
        return removedOrphans;
    }

    public boolean moveCardToKanban(Card card, KanbanBoard board, String kanbanColumnTitle) {
        Card[] cards = deleteFromOrphanCards(current_card -> current_card.getId().equals(card.getId()));

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

    public ArrayList<String> getMemberIds() {
        return this.memberIds;
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