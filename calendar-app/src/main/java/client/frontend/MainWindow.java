package client.frontend;

import javafx.application.Application;
import javafx.stage.Stage;

public class MainWindow extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        // Show login screen at startup
        stage.setTitle("Calendar app - log in");
        stage.setScene(new LoginView().createLoginScene());
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
