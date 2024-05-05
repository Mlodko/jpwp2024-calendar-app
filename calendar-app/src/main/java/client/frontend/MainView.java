package client.frontend;

import client.backend.JsonManager;
import client.backend.RequestManager;
import client.backend.models.Calendar;
import client.backend.models.KanbanBoard;
import client.backend.models.User;
import client.backend.models.Workspace;
import client.frontend.LoginView;

import com.calendarfx.model.CalendarSource;
import com.calendarfx.view.CalendarView;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

public class MainView {

    private StackPane setupLeftPane(User loggedInUser, SplitPane splitPane) {

        StackPane leftRibbon = new StackPane();
        leftRibbon.minWidthProperty().bind(splitPane.widthProperty().multiply(0.2)); //sets minimum width for the ribbon
        leftRibbon.maxWidthProperty().bind(splitPane.widthProperty().multiply(0.35)); //sets maximum width for the ribbon

        VBox vbox = new VBox();
        HBox hbox = new HBox();

        //region MENU BUTTONS
        MenuButton settings = new MenuButton("Settings");
        Button refresh = new Button("Refresh");
        Button calView = new Button("Calendar View");
        MenuItem logOut = new MenuItem("Log out");
        MenuItem changeWorkspace = new MenuItem("Change Workspace");

        refresh.setOnAction(event -> {
            System.out.println("refresh clicked UwU");
            // handle refresh...
        });

        calView.setOnAction(event -> {
            System.out.println("calView clicked T_T");
            // handle changing current view to calendar view
        });

        logOut.setOnAction(event -> {
            if (!loggedInUser.logOut()) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Couldn't log out, dunno why");
                alert.showAndWait();
                return;
            }

            // TODO logout from server too!
            JsonManager.removeAllLocalData();

            Stage loginStage = new Stage();
            loginStage.setTitle("Login");

            try {
                loginStage.setScene(new LoginView().createLoginScene());
            } catch (Exception e) {
                e.printStackTrace();
                Alert alert = new Alert(Alert.AlertType.ERROR, "Couldn't create login window after the logout");
                alert.showAndWait();
                leftRibbon.getScene().getWindow().hide();
                return;
            }

            leftRibbon.getScene().getWindow().hide();
            loginStage.show();
        });

        changeWorkspace.setOnAction(event -> {
            Stage workspaceStage = new Stage();
            workspaceStage.setTitle("Choose your workspace");

            try {
                workspaceStage.setScene(new LoginView().chooseWorkspace(loggedInUser));
            } catch (Exception e) {
                e.printStackTrace();
                Alert alert = new Alert(Alert.AlertType.ERROR, "Couldn't load workspaces and open the selection window");
                alert.showAndWait();
                return;
            }

            leftRibbon.getScene().getWindow().hide();
            workspaceStage.show();
        });

        settings.getItems().addAll(logOut, changeWorkspace);
        //endregion

        vbox.setAlignment(Pos.TOP_CENTER);
        hbox.setAlignment(Pos.TOP_CENTER);

        hbox.setSpacing(5);
        hbox.getChildren().addAll(calView, refresh, settings);

        VBox calKanbanVbox = this.readCalKanbanList(loggedInUser);
        vbox.getChildren().addAll(hbox, calKanbanVbox);
        leftRibbon.setPadding(new Insets(10,10,10,10));
        leftRibbon.getChildren().addAll(vbox);

        return leftRibbon;
    }

    private VBox readCalKanbanList(User user) {
        ArrayList<client.backend.models.Calendar> usrCalendars = new ArrayList<>();

        try (RequestManager manager = new RequestManager()) {



        } catch (Exception e) {
            e.printStackTrace();
        }

        VBox vbox = new VBox();
        vbox.setSpacing(5);

        // maybe with menu buttons?
        /*
        TODO:
            - read user's workspace
            - read all calendars (and their respective kanbans in the workspace)
            - make menu buttons like this
                calendar1:
                * kanban1
                * kanban2
                * ...
                calendar2:
                * ...
                ...
             - but how to refresh that shit???
             - still idfk how to refresh that shit
         */

        for (Calendar cal : usrCalendars) {
            MenuButton tmp = new MenuButton(cal.getID());

            for (KanbanBoard board : cal.getKanbanBoards()) {
                MenuItem tmpItem = new MenuItem(board.getTitle());

                tmpItem.setOnAction(event -> {

                    // TODO make kanbanview on this particual kanbanboard

                });

                tmp.getItems().add(tmpItem);
            }

            vbox.getChildren().add(tmp);
        }

        return vbox;
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
        StackPane leftRibbon = this.setupLeftPane(loggedInUser, splitPane);
        splitPane.getItems().addAll(leftRibbon, calendarView);
        splitPane.setDividerPosition(0, 0.2);

        return new Scene(splitPane, 1280, 720);
    }
}
