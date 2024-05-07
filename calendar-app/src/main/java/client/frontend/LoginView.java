package client.frontend;

import client.backend.JsonManager;
import client.backend.models.User;
import client.backend.models.Workspace;
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

    public Scene createLoginScene() {
        Button loginButton = new Button("Login");
        Button registerButton = new Button("Register");

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
        hBox.getChildren().addAll(loginButton, registerButton);

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
        StackPane cancelPane = new StackPane(cancelButton);

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
                mainStage.setScene(new MainView().createMainView(registeredUser.get(), new Workspace("Default", "Default")));
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
        Button cancel = new Button("Log out");
        cancel.setOnAction(event -> {
            try {
                if (!user.logOut()) {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Couldn't log out, dunno why");
                    alert.showAndWait();
                    return;
                }
            } catch (Exception e) {
                System.out.println("chooseWorkspace - cancel.setOnAction -> User logout error.");
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
                cancel.getScene().getWindow().hide();
                return;
            }

            cancel.getScene().getWindow().hide();
            loginStage.show();
        });

        MenuButton choice = new MenuButton("Choose your workspace:");
        ArrayList<String> workspaceIDs = user.getAssignedWorkspaceIds();

        for (String id : workspaceIDs) {
            MenuItem tmp = new MenuItem(id);

            tmp.setOnAction(event -> {
                Stage mainStage = new Stage();
                mainStage.setTitle("Calendar App");

                Workspace selected;

                try {
                    Optional<Workspace> completeWorkspace = Workspace.constructCompleteWorkspace(id, user.getAuthToken());

                    if (completeWorkspace.isEmpty()){
                        Alert alert = new Alert (Alert.AlertType.ERROR, "Couldn't read your workspace :(");
                        alert.showAndWait();
                        return;
                    }

                    selected = completeWorkspace.get();
                    JsonManager.writeALLdata(selected);
                    mainStage.setScene(new MainView().createMainView(user, selected));

                } catch (IOException e) {
                    e.printStackTrace();
                    new Alert(Alert.AlertType.ERROR, "Couldn't read calendar data.")
                            .showAndWait();
                    return;
                }

                cancel.getScene().getWindow().hide();
                mainStage.show();
            });

            choice.getItems().add(tmp);
        }

        VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(5));
        vbox.setAlignment(Pos.CENTER);
        vbox.getChildren().addAll(choice, cancel);

        return new Scene(vbox, 300, 200);
    }


    private static boolean isEmail(String email) {
        return Pattern.matches("^(.+)@(\\S+)$", email);
    }
}
