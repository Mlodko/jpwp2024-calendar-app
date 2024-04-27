module client.backend {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;
    requires org.commonmark;
    requires javafx.web;
    requires org.apache.commons.codec;
    requires com.calendarfx.view;
    requires org.eclipse.jetty.http;
    requires org.eclipse.jetty.server;
    requires org.eclipse.jetty.io;
    requires javax.servlet.api;


    opens com.example.calendarapp to javafx.fxml;
    exports client.frontend;
    exports client.backend.models;
    exports com.example.calendarapp;
    opens client.backend.models to com.google.gson;
    opens client.backend to com.google.gson;
    opens client.backend.serialization to com.google.gson;
    opens client.frontend to com.google.gson;
}
