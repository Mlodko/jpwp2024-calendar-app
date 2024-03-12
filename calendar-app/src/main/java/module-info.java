module com.example.calendarapp {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.calendarapp to javafx.fxml;
    exports com.example.calendarapp;
}