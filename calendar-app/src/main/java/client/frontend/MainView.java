package client.frontend;

import client.backend.JsonManager;
import client.backend.models.Calendar;
import client.backend.models.User;
import com.calendarfx.model.CalendarSource;
import com.calendarfx.view.CalendarView;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;

import java.io.IOException;
import java.util.ArrayList;

public class MainView {
    public Scene createCalendarView(User loggedInUser) throws IOException {
        CalendarView calendarView = new CalendarView();
        ArrayList<Calendar> myCalendars = JsonManager.readAllCalendars();

        // Delete default calendar source
        calendarView.getCalendarSources().clear();

        for(client.backend.models.Calendar calendar : myCalendars) {
            CalendarSource source = CalendarFXFactory.create(calendar);
            calendarView.getCalendarSources().add(source);
        }

        SplitPane splitPane = new SplitPane();
        splitPane.getItems().addAll(calendarView);

        // TODO left ribbon menu

        return new Scene(splitPane, 1280, 720);
    }
}
