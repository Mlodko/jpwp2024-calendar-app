package client.frontend;

import client.backend.models.*;
import javafx.application.Application;
import javafx.stage.Stage;

import javafx.scene.text.Font;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;


public class StartApp extends Application {

    private static final Font font = Font.loadFont("file:/resources/client.frontend/Roboto-Medium.tff", 22);

    @Override
    public void start(Stage stage) throws IOException {
        stage.setTitle("Login");
        //stage.setScene(new LoginView().createLoginScene());

        User usr = new User("bob", "bobowski");
/*
        Workspace workspace = new Workspace("sjjs", "dupa");

        Calendar cal1 = new Calendar("jeden");
        Calendar cal2 = new Calendar("dwa");

        KanbanBoard board1 = new KanbanBoard("board jeden").addNewItemColumn("cyce").addNewItemColumn("dupa");
        KanbanBoard board2 = new KanbanBoard("board dwa").addNewItemColumn("sranie");
        board1.setTitle("guwno");

        ArrayList<KanbanBoard> boardlist = new ArrayList<>();

        ArrayList<Card> cards1 = new ArrayList<>();
        ArrayList<Card> cards2 = new ArrayList<>();

        for (int i = 0; i < 11; i++) {
            cards1.add(new Card(
                    "title" + String.valueOf(i), "desc" + String.valueOf(i+33), new Date(), new Date())
            );

            cards2.add(cards1.get(i));
            cards2.add(cards1.get(i));
        }

        board1.addToItemsList("cyce", cards1);
        board1.addToItemsList("dupa", cards2);
        board2.addToItemsList("sranie", cards2);
        board2.addToItemsList("sranie", cards1);

        boardlist.add(board1);
        boardlist.add(board2);

        cal1.setKanbanBoards(boardlist);
        workspace.addToCalendars(cal2);
        workspace.addToCalendars(cal1);
*/
        //stage.setScene(new MainView().createMainView(new User("Aleksander B", "alkohol"), workspace));
        //stage.setScene(new MainView().createMainView(usr, workspace));
        //stage.setScene(new CardView().createCardView(cards.get(1)));
        stage.setScene(new LoginView().createLoginScene());
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}