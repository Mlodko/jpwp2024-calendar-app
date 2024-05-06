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
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import org.w3c.dom.events.MouseEvent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;

public class MainView {

    public Scene createMainView(User loggedInUser, Workspace selectedWorkspace) throws IOException {
        SplitPane splitPane = new SplitPane();
        StackPane leftRibbon = this.setupLeftPane(loggedInUser, selectedWorkspace, splitPane);

        CalendarView calendarView = this.createCalendarView(selectedWorkspace);

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
            // TODO handle refresh...
        });

        calView.setOnAction(event -> {
            // TODO check for issues later
            CalendarView calendar = this.createCalendarView(selectedWorkspace);
            splitPane.getItems().set(1, calendar);
        });

        logOut.setOnAction(event -> {

            try {
                if (!loggedInUser.logOut(selectedWorkspace)) {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Couldn't log out, dunno why");
                    alert.showAndWait();
                    return;
                }
            } catch (Exception e) {
                System.out.println("setupLeftPane - logOut.setOnAction -> User logout error.");
                e.printStackTrace();
                return;
            }

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

        VBox calKanbanVbox = this.readCalKanbanList(selectedWorkspace, splitPane);
        vbox.getChildren().addAll(hbox, calKanbanVbox);
        leftRibbon.setPadding(new Insets(10,10,10,10));
        leftRibbon.getChildren().addAll(vbox);

        return leftRibbon;
    }

    private CalendarView createCalendarView(Workspace selectedWorkspace) {
        CalendarView calendarView = new CalendarView();
        calendarView.getCalendarSources().clear();  // Delete default calendar source

        // TODO comeback for any issue check
        ArrayList<Calendar> myCalendars = selectedWorkspace.getCalendars();

        for(client.backend.models.Calendar calendar : myCalendars) {
            CalendarSource source = CalendarFXFactory.create(calendar);
            calendarView.getCalendarSources().add(source);
        }

        return calendarView;
    }

    private VBox readCalKanbanList(Workspace workspace, SplitPane mainSplitPane) {
        ArrayList<client.backend.models.Calendar> usrCalendars = workspace.getCalendars();

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
            // TODO handle adding and saving calendar + refresh the view
            System.out.println("addCalendar clicked O.O");
        });

        Separator sep = new Separator(Orientation.HORIZONTAL);

        HBox.setHgrow(forText, Priority.ALWAYS);
        littleBar.getChildren().addAll(forText, addCalendar);
        vbox.getChildren().addAll(sep, littleBar);

        // TODO handle refreshing issue?

        for (Calendar cal : usrCalendars) {
            HBox hbox = new HBox();
            hbox.setSpacing(3);
            hbox.setPadding(new Insets(3,3,3,3));
            hbox.setAlignment(Pos.CENTER);
            MenuButton tmp = new MenuButton(cal.getID());
            Button add = new Button("Add New Board");

            add.setOnAction(event -> {
                // TODO add new board to the calendar, save and refresh
            });

            for (KanbanBoard board : cal.getKanbanBoards()) {
                MenuItem tmpItem = new MenuItem(board.getTitle());

                tmpItem.setOnAction(event -> {
                    mainSplitPane.getItems().set(1, this.createKanbanView(board));
                });

                tmp.getItems().add(tmpItem);
            }

            hbox.getChildren().addAll(tmp, add);
            vbox.getChildren().add(hbox);
        }

        return vbox;
    }

    private ScrollPane createKanbanView(KanbanBoard board) {
        ScrollPane scrollKanbanView = new ScrollPane();
        scrollKanbanView.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);

        StackPane kanbanView = new StackPane();
        HBox listsBox = new HBox();
        listsBox.setSpacing(8);
        listsBox.setPadding(new Insets(5, 5, 5, 5));
        listsBox.setAlignment(Pos.TOP_LEFT);

        for (Map.Entry<String, ArrayList<Card>> entry : board.getItemsLists().entrySet()) {
            VBox mainVBox = new VBox();
            mainVBox.setMaxWidth(250);
            VBox.setVgrow(mainVBox, Priority.ALWAYS);
            mainVBox.setAlignment(Pos.TOP_CENTER);
            mainVBox.setSpacing(8);
            Label nameLbl = new Label(entry.getKey());
            nameLbl.setPadding(new Insets(5,5,5,5));
            VBox cardVBox = new VBox();
            cardVBox.setSpacing(5);

            for (Card card : entry.getValue()) {
                cardVBox.getChildren().add(createCard(card));
            }

            cardVBox.getChildren().add(this.createLastCard());

            Separator sep = new Separator(Orientation.HORIZONTAL);
            mainVBox.getChildren().addAll(nameLbl, sep, cardVBox);
            listsBox.getChildren().add(mainVBox);
        }

        kanbanView.setPadding(new Insets(5,5,5,5));
        kanbanView.getChildren().add(listsBox);
        scrollKanbanView.setContent(kanbanView);
        return scrollKanbanView;
    }

    private StackPane createCard(Card card) {
        StackPane stack = new StackPane();
        stack.setPadding(new Insets(3,3,3,3));
        stack.setAlignment(Pos.CENTER);
        stack.setStyle("-fx-background-color: #bebebe;");

        VBox vbox = new VBox();
        vbox.setAlignment(Pos.CENTER_LEFT);
        vbox.setPadding(new Insets(3,3,3,3));
        vbox.setSpacing(3);

        HBox hbox = new HBox();
        hbox.setAlignment(Pos.CENTER);
        hbox.setPadding(new Insets(3,3,3,3));
        hbox.setSpacing(3);

        StackPane forCardName = new StackPane();
        forCardName.setPadding(new Insets(3,3,3,3));
        Label cardName = new Label(card.getTitle());
        cardName.setPadding(new Insets(3,3,3,3));
        //cardName.setStyle("-fx-background-color: #bebebe;");
        cardName.setAlignment(Pos.CENTER);
        forCardName.getChildren().add(cardName);

        Label startDate = new Label(card.getStartTime().toString());
        startDate.setPadding(new Insets(3,3,3,3));
        //startDate.setStyle("-fx-background-color: #bebebe;");
        startDate.setAlignment(Pos.CENTER);

        Separator vSep = new Separator(Orientation.VERTICAL);
        Separator hSep = new Separator(Orientation.HORIZONTAL);

        Label endDate = new Label(card.getEndTime().toString());
        endDate.setPadding(new Insets(3,3,3,3));
        //endDate.setStyle("-fx-background-color: #bebebe;");
        endDate.setAlignment(Pos.CENTER);

        hbox.getChildren().addAll(startDate, vSep, endDate);
        vbox.getChildren().addAll(forCardName, hSep, hbox);
        stack.getChildren().add(vbox);

        stack.setOnDragDetected(event -> {
            System.out.println("Drag detected on card " + card.getId());
            //Dragboard dragboard = stack.startDragAndDrop(TransferMode.MOVE);
        });

        stack.setOnMouseClicked(event -> {
            Stage cardStage = new Stage();
            cardStage.setTitle(card.getTitle());

            try {
                cardStage.setScene(new CardView().createCardView(card));
            } catch (Exception e) {
                System.out.println("debiluuuuuu");
                e.printStackTrace();
                return;
            }

            cardStage.show();
        });

        return stack;
    }

    // how to get the fucking list index here?
    private StackPane createLastCard(/* StackPane mainStack */) {
        StackPane stack = new StackPane();
        stack.setPadding(new Insets(3,3,3,3));
        stack.setAlignment(Pos.CENTER);
        stack.setStyle("-fx-background-color: #bebebe;");

        Label plus = new Label("+");
        stack.getChildren().add(plus);

        stack.setOnMouseClicked(event -> {
            System.out.println("added new card UwU");
            // TODO handle adding new card...
        });

        return stack;
    }

}
