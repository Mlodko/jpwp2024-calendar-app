package client.frontend;

import client.backend.models.User;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.Optional;

public class LoginView {

    TextField userTextField;

    public static Scene createScene() {
        Label userLabel = new Label("Username");
        TextField userTextField = new TextField();

        Label passwordLabel = new Label("Password");
        PasswordField passwordField = new PasswordField();

        Button loginButton = new Button("Login");
        Button registerButton = new Button("Register");

        HBox hBox = new HBox(50);
        hBox.setAlignment(Pos.CENTER);
        hBox.getChildren().addAll(loginButton, registerButton);

        VBox vBox = new VBox();
        vBox.setSpacing(10);
        vBox.setPadding(new Insets(10, 10, 10, 10));
        vBox.getChildren().addAll(userLabel, userTextField, passwordLabel, passwordField, hBox);

        Scene scene = new Scene(vBox, 300, 200);
        return scene;
    }

    /*
    private static Optional<User> login(String username, String password) {

    }*/
}
