module client.backend {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;


    opens com.example.calendarapp to javafx.fxml;
    exports com.example.calendarapp;
    opens client.backend.models to com.google.gson;
    opens client.backend to com.google.gson;
    opens client.backend.serialization to com.google.gson;
}