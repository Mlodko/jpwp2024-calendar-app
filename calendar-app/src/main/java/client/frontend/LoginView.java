package client.frontend;

import client.backend.models.User;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import java.util.regex.Pattern;

public class LoginView {

    TextField userTextField;
    Button loginButton = new Button("Login");
    Button registerButton = new Button("Register");

    public Scene createLoginScene() {
        Label userLabel = new Label("Username");
        TextField userTextField = new TextField();

        Label passwordLabel = new Label("Password");
        PasswordField passwordField = new PasswordField();

        loginButton.setOnAction(event -> {
            String username = userTextField.getText();
            String password = passwordField.textProperty().getValue();

            if (username.isEmpty() || password.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "No username and/or password provided.", ButtonType.OK);
                alert.showAndWait();
                return;
            }

            Optional<User> loggedInUser;

            try {
                loggedInUser = User.login(username, password);
            }
            catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Server connection error", ButtonType.OK);
                alert.showAndWait();
                return;
            }

            if (loggedInUser.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "User not found in database!", ButtonType.OK);
                alert.showAndWait();
                return;
            }

            /*
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Successfully logged in!\n" +
                    "Username: " + loggedInUser.get().getUsername() +
                    "Email: " + loggedInUser.get().getEmail(), ButtonType.OK);
            alert.showAndWait();
            */

            Stage workspaceStage = new Stage();
            workspaceStage.setTitle("Choose your workspace");

            try {
                workspaceStage.setScene(new LoginView().chooseWorkspace(loggedInUser.get()));
            } catch (Exception e) {
                e.printStackTrace();
                new Alert(Alert.AlertType.ERROR, "Couldn't read workspaces and open selection window").showAndWait();
                return;
            }

            loginButton.getScene().getWindow().hide();
            workspaceStage.show();
        });

        registerButton.setOnAction(event -> {
            Stage registerStage = new Stage();
            registerStage.setTitle("Register");
            registerStage.setScene(createRegisterScene());
            registerButton.getScene().getWindow().hide();
            registerStage.show();
        });

        HBox hBox = new HBox(50);
        hBox.setAlignment(Pos.CENTER);
        hBox.getChildren().addAll(this.loginButton, this.registerButton);

        VBox vBox = new VBox();
        vBox.setSpacing(10);
        vBox.setPadding(new Insets(10, 10, 10, 10));
        vBox.getChildren().addAll(userLabel, userTextField, passwordLabel, passwordField, hBox);

        return new Scene(vBox, 300, 200);
    }

    public Scene createRegisterScene() {
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setAlignment(Pos.CENTER);

        // Username
        TextField userTextField = new TextField();
        gridPane.add(new Label("Username"), 0, 0);
        gridPane.add(userTextField, 1, 0);

        // Email
        TextField emailTextField = new TextField();
        gridPane.add(new Label("Email"), 0, 1);
        gridPane.add(emailTextField, 1, 1);

        // Password
        PasswordField passwordField = new PasswordField();
        gridPane.add(new Label("Password"), 0, 2);
        gridPane.add(passwordField, 1, 2);

        // Confirm password
        PasswordField confirmPasswordField = new PasswordField();
        gridPane.add(new Label("Confirm Password"), 0, 3);
        gridPane.add(confirmPasswordField, 1, 3);

        // Buttons
        Button okButton = new Button("OK");
        Button cancelButton = new Button("Cancel");

        StackPane okPane = new StackPane(okButton);
        //okPane.setAlignment(Pos.CENTER);

        StackPane cancelPane = new StackPane(cancelButton);
        //cancelPane.setAlignment(Pos.CENTER);

        gridPane.add(okPane, 0, 4);
        gridPane.add(cancelPane, 1, 4);


        cancelButton.setOnAction(event -> {
           Stage loginStage = new Stage();
           loginStage.setTitle("Login");
           loginStage.setScene(createLoginScene());
           cancelButton.getScene().getWindow().hide();
           loginStage.show();
        });

        okButton.setOnAction(event -> {
            // If any field is empty
            if (Arrays.stream((new TextField[] {userTextField, emailTextField, passwordField, confirmPasswordField}))
                    .anyMatch(textField -> textField.getText().isEmpty())) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "At least one of fields is empty.");
                alert.showAndWait();
                return;
            }

            // If email is not an email
            if (!isEmail(emailTextField.getText())) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Enter correct email");
                alert.showAndWait();
                return;
            }

            // If passwords don't match
            if (!passwordField.getText().equals(confirmPasswordField.getText())) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Passwords don't match");
                alert.showAndWait();
                return;
            }

            Optional<User> registeredUser;
            // Everything, a-ok - register
            try {
                registeredUser = User.register(userTextField.getText(), passwordField.getText(), emailTextField.getText());

                if (registeredUser.isEmpty()) {
                    Alert alert = new Alert(Alert.AlertType.WARNING, "Couldn't register, either a server error or a user with given username already exists");
                    alert.showAndWait();
                    return;
                }

            } catch(Exception e) {
                e.printStackTrace();
                Alert alert = new Alert(Alert.AlertType.ERROR, "Couldn't register, request error - try again or restart the application.");
                alert.showAndWait();
                return;
            }

            Stage mainStage = new Stage();
            mainStage.setTitle("Calendar App");
            try {
                mainStage.setScene(new MainView().createCalendarView(registeredUser.get()));
            } catch (IOException e) {
                e.printStackTrace();
                Alert alert = new Alert(Alert.AlertType.ERROR, "Couldn't read calendar data.");
                alert.showAndWait();
                return;
            }
            okButton.getScene().getWindow().hide();
            mainStage.show();
        });

        return new Scene(gridPane);
    }

    public Scene chooseWorkspace(User user) {
        Button cancel = new Button("Cancel");

        cancel.setOnAction(event -> {
            // TODO logout the user

            Stage loginStage = new Stage();
            loginStage.setTitle("Login");
            loginStage.setScene(createLoginScene());
            cancel.getScene().getWindow().hide();
            loginStage.show();
        });

        MenuButton choice = new MenuButton("Choose your workspace:");
        ArrayList<String> workspaceIDs = new ArrayList<>(); // here read all user's workspace ids

        for (String id : workspaceIDs) {
            MenuItem tmp = new MenuItem(id);

            tmp.setOnAction(event -> {
                // TODO read "tmp.getText()" workspace

                Stage mainStage = new Stage();
                mainStage.setTitle("Calendar App");

                try { // TODO somehow download the workspace to local and load it
                    mainStage.setScene(new MainView().createCalendarView(user));
                } catch (IOException e) {
                    e.printStackTrace();
                    new Alert(Alert.AlertType.ERROR, "Couldn't read calendar data.")
                            .showAndWait();
                    return;
                }

                loginButton.getScene().getWindow().hide();
                mainStage.show();
            });

            choice.getItems().add(tmp);
        }

        VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.getChildren().addAll(choice, cancel);

        return new Scene(vbox);
    }

    private static boolean isEmail(String email) {
        return Pattern.matches("^(.+)@(\\S+)$", email);
    }
}
