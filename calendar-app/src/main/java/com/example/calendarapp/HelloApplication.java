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
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.scene.text.Font;

import java.io.IOException;
import java.util.ArrayList;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Parent fxmlLoader = FXMLLoader.load(LoginView.class.getResource("calendarView.fxml"));
        // TODO webview
        WebView webView = new WebView();
        WebEngine webEngine = webView.getEngine();


        Font font = new Font("file:/resources/client.frontend/Roboto-Medium.tff", 22);
        CalendarView calendarView = new CalendarView();
        ArrayList<client.backend.models.Calendar> myCalendars = JsonManager.readAllCalendars();
        for(client.backend.models.Calendar calendar : myCalendars) {
            CalendarSource source = CalendarFXFactory.create(calendar);
            calendarView.getCalendarSources().add(source);
        }

        Scene scene = new Scene(calendarView, 1280, 720);
        //scene.getStylesheets().add(LoginView.class.getResource("login-view.css").toExternalForm());
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}