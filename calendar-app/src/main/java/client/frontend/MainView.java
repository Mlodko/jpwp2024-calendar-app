package client.frontend;

import client.backend.JsonManager;
import client.backend.RequestManager;
import client.backend.models.*;
import client.frontend.LoginView;

import com.calendarfx.model.CalendarSource;
import com.calendarfx.view.CalendarView;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;

public class MainView {

    public Scene createMainView(User loggedInUser, Workspace selectedWorkspace) throws IOException {
        SplitPane splitPane = new SplitPane();
        StackPane leftRibbon = this.setupLeftPane(loggedInUser, selectedWorkspace, splitPane);
        CalendarView calendarView = this.createCalendarView(loggedInUser);

        splitPane.getItems().addAll(leftRibbon, calendarView);
        splitPane.setDividerPosition(0, 0.22);

        return new Scene(splitPane, 1280, 720);
    }

    private StackPane setupLeftPane(User loggedInUser, Workspace selectedWorkspace, SplitPane splitPane) {

        StackPane leftRibbon = new StackPane();
        leftRibbon.minWidthProperty().bind(splitPane.widthProperty().multiply(0.22)); //sets minimum width for the ribbon
        leftRibbon.maxWidthProperty().bind(splitPane.widthProperty().multiply(0.35)); //sets maximum width for the ribbon

        VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(5,5,5,5));
        HBox hbox = new HBox();

        //region MENU BUTTONS
        MenuButton settings = new MenuButton("Settings");
        Button refresh = new Button("Refresh");
        Button calView = new Button("Calendar View");
        MenuItem logOut = new MenuItem("Log out");
        MenuItem changeWorkspace = new MenuItem("Change Workspace");

        refresh.setOnAction(event -> {
            System.out.println("refresh clicked UwU");
            StackPane tmp = new StackPane();
            Label lbl = new Label("big boobs");
            tmp.getChildren().add(lbl);
            splitPane.getItems().set(1, tmp);
            // handle refresh...
        });

        calView.setOnAction(event -> {
            // TODO check for issues later
            CalendarView calendar = this.createCalendarView(loggedInUser);
            splitPane.getItems().set(1, calendar);
        });

        logOut.setOnAction(event -> {

            // TODO save all work to server, then logout from server and clear local data
            // TODO logout locally too!

            if (!loggedInUser.logOut()) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Couldn't log out, dunno why");
                alert.showAndWait();
                return;
            }

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
        hbox.setPadding(new Insets(5,5,5,5));
        hbox.getChildren().addAll(calView, refresh, settings);

        VBox calKanbanVbox = this.readCalKanbanList(loggedInUser, splitPane);
        vbox.getChildren().addAll(hbox, calKanbanVbox);
        leftRibbon.setPadding(new Insets(10,10,10,10));
        leftRibbon.getChildren().addAll(vbox);

        return leftRibbon;
    }

    private CalendarView createCalendarView(User loggedInUser) {
        CalendarView calendarView = new CalendarView();
        ArrayList<Calendar> myCalendars = new ArrayList<>(); //JsonManager.readWorkspace();

        // Delete default calendar source
        calendarView.getCalendarSources().clear();

        for(client.backend.models.Calendar calendar : myCalendars) {
            CalendarSource source = CalendarFXFactory.create(calendar);
            calendarView.getCalendarSources().add(source);
        }

        return calendarView;
    }

    private VBox readCalKanbanList(User user, SplitPane mainSplitPane) {
        ArrayList<client.backend.models.Calendar> usrCalendars = new ArrayList<>();

        try (RequestManager manager = new RequestManager()) {

            // TODO there ya manager.read

        } catch (Exception e) {
            e.printStackTrace();
        }

        VBox vbox = new VBox();
        vbox.setSpacing(8);

        HBox littleBar = new HBox();
        littleBar.setAlignment(Pos.CENTER);
        littleBar.setSpacing(5);
        littleBar.setPadding(new Insets(5,5,5,5));
        AnchorPane forText = new AnchorPane();
        Label text = new Label("Your calendars");
        text.setAlignment(Pos.CENTER);
        forText.getChildren().add(text);
        Button addCalendar = new Button("Add New Calendar");
        addCalendar.setAlignment(Pos.CENTER);

        addCalendar.setOnAction(event -> {
            System.out.println("addCalendar clicked O.O");
        });

        Separator sep = new Separator(Orientation.HORIZONTAL);

        HBox.setHgrow(forText, Priority.ALWAYS);
        littleBar.getChildren().addAll(forText, addCalendar);
        vbox.getChildren().addAll(sep , littleBar);

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

                    StackPane kanbanView = new StackPane();
                    HBox listsBox = new HBox();
                    listsBox.setSpacing(5);
                    listsBox.setPadding(new Insets(5, 5, 5, 5));

                    for (Map.Entry<String, ArrayList<Card>> entry : board.getItemsLists().entrySet()) {
                        VBox mainVBox = new VBox();
                        mainVBox.setAlignment(Pos.CENTER);
                        mainVBox.setSpacing(5);
                        Label nameLbl = new Label(entry.getKey());
                        VBox cardVBox = new VBox();
                        cardVBox.setSpacing(3);

                        for (Card card : entry.getValue()) {
                            Label cardLbl = new Label(card.getTitle());
                            cardVBox.getChildren().add(cardLbl);
                        }

                        mainVBox.getChildren().addAll(nameLbl, cardVBox);
                        listsBox.getChildren().add(mainVBox);
                    }

                    kanbanView.getChildren().add(listsBox);
                    mainSplitPane.getItems().set(1, kanbanView);
                });

                tmp.getItems().add(tmpItem);
            }

            vbox.getChildren().add(tmp);
        }

        return vbox;
    }
}
