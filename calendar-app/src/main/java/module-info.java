module client.backend {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;
    requires net.sf.biweekly;


    opens com.example.calendarapp to javafx.fxml;
    opens client.backend to com.google.gson;
    exports com.example.calendarapp;
}