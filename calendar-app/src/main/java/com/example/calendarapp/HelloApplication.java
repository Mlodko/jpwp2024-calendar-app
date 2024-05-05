package com.example.calendarapp;

import client.backend.models.*;
import client.frontend.CalendarFXFactory;
import client.frontend.LoginView;
import client.frontend.MainView;
import com.calendarfx.model.CalendarSource;
import com.calendarfx.view.CalendarView;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.scene.text.Font;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;


public class HelloApplication extends Application {

    //private static final Font font = new Font("file:/resources/client.frontend/Roboto-Medium.tff", 22);

    @Override
    public void start(Stage stage) throws IOException {
        stage.setTitle("Login");
        //stage.setScene(new LoginView().createLoginScene());

        User usr = new User("bob", "bobowski");

        Workspace workspace = new Workspace("dupa", "dupa");
        Calendar cal = new Calendar("id2137");
        HashMap<String, ArrayList<Card>> items = new HashMap<>();
        ArrayList<Card> cards = new ArrayList<>();

        ArrayList<KanbanBoard> boards = new ArrayList<>();

        for (int j = 3; j < 8; j++) {
            Card tmpCard = new Card("dupa" + String.valueOf(j));
            cards.add(tmpCard);
        }

        items.put("key1337", cards);

        for (int i = 0; i < 3; i++) {
            KanbanBoard tmpBoard = new KanbanBoard(
                    String.valueOf(i), "nazwa" + String.valueOf(i), new Date(), new Date(), cal, new Date(), new Date(), items
            );
            boards.add(tmpBoard);
        }

        cal.setKanbanBoards(boards);
        workspace.addToCalendars(cal);

        // TODO link calendar to user

        //stage.setScene(new MainView().createMainView(new User("Aleksander B", "alkohol"), workspace));
        stage.setScene(new MainView().createMainView(usr, workspace));
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}