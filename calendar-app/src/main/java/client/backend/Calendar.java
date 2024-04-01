package calendarapp.client.backend;


import java.util.ArrayList;
import java.util.function.Predicate;
import net.sf.biweekly;


// not to JSON, to .ical!!!!
public class Calendar {
    private String id;

    public void setID(String newID) {
        this.id = newID;
    }

    public String getID() {
        return this.id;
    }

    private ArrayList<KanbanBoard> kanbanBoards;

    public ArrayList<KanbanBoard> getKanbanBoards() {
        return this.kanbanBoards;
    }

    public void setKanbanBoards(ArrayList<KanbanBoard> newKanbanBoards) {
        this.kanbanBoards = newKanbanBoards;
    }

    public void addToKanbanBoards(KanbanBoard board) {
        this.kanbanBoards.add(board);
    }

    public KanbanBoard[] deleteFromKanbanBoards(Predicate<KanbanBoard> filterFunction) {
        KanbanBoard[] removedBoards = this.kanbanBoards.stream().filter(filterFunction).toArray(KanbanBoard[]::new);
        this.kanbanBoards.removeIf(filterFunction);
        return removedBoards;
    }

    private ArrayList<Card> orphanCards;

    public ArrayList<Card> getOrphanCards() {
        return this.orphanCards;
    }

    public void setOrphanCards(ArrayList<Card> newOrphanCards) {
        this.orphanCards = newOrphanCards;
    }

    public void addToOrphanCards(Card newOrphan) {
        this.orphanCards.add(newOrphan);
    }

    public Card[] deleteFromOrphanCards(Predicate<Card> filterFunc) {
        Card[] removedOrphans = this.orphanCards.stream().filter(filterFunc).toArray(Card[]::new);
        this.orphanCards.removeIf(filterFunc);
        return removedOrphans;
    }

    //TODO ArrayList<User> users;

    /*
    public static void main(String[] args) {
        Calendar calendar = new Calendar();
        calendar.kanbanBoards = new ArrayList<KanbanBoard>();
        calendar.kanbanBoards.add(new KanbanBoard("1", "", null, null, null, null));
        calendar.kanbanBoards.add(new KanbanBoard("2", "chuj ci w dupe", null, null, null, null));
        calendar.kanbanBoards.add(new KanbanBoard("3", "matka cie nie kocha", null, null, null, null));

        KanbanBoard[] removed = calendar.deleteFromKanbanBoards(kanbanBoard -> kanbanBoard.getTitle().equals("matka cie nie kocha"));

        System.out.println("calendar boards:");
        for(KanbanBoard kanbanBoard : calendar.kanbanBoards) {
           System.out.println(kanbanBoard.getId());
        }
        System.out.println("removed boards: ");
        for(KanbanBoard kanban : removed) {
            System.out.println(kanban.getId());
        }
    }
    */
}
/*
    TODO implement ical serialization
        ical should contain:
            - calendar's id
            - id's of attached kanban boards and orphans
            - kanbans and cards converted to events
            - users' ids
 */


