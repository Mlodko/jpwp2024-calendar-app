package client.frontend;

import client.backend.JsonManager;
import client.backend.models.Calendar;
import client.backend.models.User;
import com.calendarfx.model.CalendarSource;
import com.calendarfx.view.CalendarView;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.util.ArrayList;

public class MainView {

    private StackPane setupLeftPane(SplitPane splitPane) {

        StackPane leftRibbon = new StackPane();
        leftRibbon.minWidthProperty().bind(splitPane.widthProperty().multiply(0.2)); //sets minimum width for the ribbon
        leftRibbon.maxWidthProperty().bind(splitPane.widthProperty().multiply(0.35)); //sets maximum width for the ribbon

        VBox vbox = new VBox();
        HBox hbox = new HBox();

        //region MENU
        MenuButton menu = new MenuButton("Settings");
        MenuItem refresh = new MenuItem("Refresh");
        MenuItem logOut = new MenuItem("Log out");

        refresh.setOnAction(event -> {
            System.out.println("refresh clicked UwU");
            // handle refresh...
        });

        logOut.setOnAction(event -> {
            System.out.println("logout clicked OwO");
            // handle logout, close the main screen and open log in window...
        });

        menu.getItems().addAll(refresh, logOut);
        //VBox.setMargin(menu, new Insets(10)); // Adds a margin around given Node
        //endregion

        //region KANBAN_TOGGLE
        Button kanbanToggle = new Button("Calendar/Kanban view");

        kanbanToggle.setOnAction(event -> {
            System.out.println("kanbanToggle clicked -.-");
            // handle toggling...
        });
        //endregion

        vbox.setAlignment(Pos.TOP_CENTER);
        hbox.setAlignment(Pos.TOP_CENTER);

        hbox.getChildren().addAll(menu, kanbanToggle);
        vbox.getChildren().addAll(hbox);
        leftRibbon.setPadding(new Insets(10,10,10,10));
        leftRibbon.getChildren().addAll(vbox);
        return leftRibbon;
    }

    public Scene createCalendarView(User loggedInUser) throws IOException {
        CalendarView calendarView = new CalendarView();
        ArrayList<Calendar> myCalendars = new ArrayList<>(); //JsonManager.readWorkspace();

        // Delete default calendar source
        calendarView.getCalendarSources().clear();

        for(client.backend.models.Calendar calendar : myCalendars) {
            CalendarSource source = CalendarFXFactory.create(calendar);
            calendarView.getCalendarSources().add(source);
        }

        SplitPane splitPane = new SplitPane();
        StackPane leftRibbon = this.setupLeftPane(splitPane);
        splitPane.getItems().addAll(leftRibbon, calendarView);
        splitPane.setDividerPosition(0, 0.2);

        return new Scene(splitPane, 1280, 720);
    }
}
