package client.frontend;

import client.backend.models.*;
import javafx.application.Application;
import javafx.stage.Stage;

import javafx.scene.text.Font;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;


public class StartApp extends Application {

    private static final Font font = Font.loadFont("file:/resources/client.frontend/Roboto-Medium.tff", 22);

    @Override
    public void start(Stage stage) throws IOException {
        stage.setTitle("Login");
        stage.setScene(new LoginView().createLoginScene());
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}