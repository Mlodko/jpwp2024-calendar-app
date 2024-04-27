package com.example.calendarapp;

import client.backend.JsonManager;
import client.frontend.CalendarFXFactory;
import client.frontend.LoginView;
import client.frontend.MarkdownParser;
import com.calendarfx.model.Calendar;
import com.calendarfx.model.CalendarSource;
import com.calendarfx.view.CalendarView;
import com.calendarfx.view.page.MonthPage;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.scene.text.Font;

import java.io.IOException;
import java.util.ArrayList;

// TODO find a way to add button to a buttonbar ???

public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        Parent fxmlLoader = FXMLLoader.load(LoginView.class.getResource("calendarView.fxml"));
        // TODO webview
        WebView webView = new WebView();
        WebEngine webEngine = webView.getEngine();


        Font font = new Font("file:/resources/client.frontend/Roboto-Medium.tff", 22);
        CalendarView calendarView = new CalendarView();
        // TODO if there are no calendars available, the app will crash :)
        ArrayList<client.backend.models.Calendar> myCalendars = JsonManager.readAllCalendars();

        // Delete default calendar source
        calendarView.getCalendarSources().clear();

        for(client.backend.models.Calendar calendar : myCalendars) {
            CalendarSource source = CalendarFXFactory.create(calendar);
            calendarView.getCalendarSources().add(source);
        }

        SplitPane splitPane = new SplitPane();
        splitPane.getItems().addAll(calendarView);

        Scene scene = new Scene(splitPane, 1280, 720);
        //scene.getStylesheets().add(LoginView.class.getResource("login-view.css").toExternalForm());
        stage.setTitle("Hello!");
        stage.setScene(LoginView.createScene());
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}