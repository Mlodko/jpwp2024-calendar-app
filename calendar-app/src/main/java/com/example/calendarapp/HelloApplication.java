package com.example.calendarapp;

import client.backend.models.Calendar;
import client.backend.models.User;
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

// TODO find a way to add button to a buttonbar ???

public class HelloApplication extends Application {

    //private static final Font font = new Font("file:/resources/client.frontend/Roboto-Medium.tff", 22);

    @Override
    public void start(Stage stage) throws IOException {
        // TODO webview
        CalendarView calendarView = new CalendarView();
        ArrayList<Calendar> myCalendars = new ArrayList<Calendar>();
        // Delete default calendar source
        calendarView.getCalendarSources().clear();

        for(client.backend.models.Calendar calendar : myCalendars) {
            CalendarSource source = CalendarFXFactory.create(calendar);
            calendarView.getCalendarSources().add(source);
        }

        SplitPane splitPane = new SplitPane();
        splitPane.getItems().addAll(calendarView);
        stage.setTitle("Hello!");
        stage.setScene(new LoginView().createLoginScene());
        //stage.setScene(new MainView().createCalendarView(new User("Aleksander B", "alkohol")));
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}