module com.example.ofertavacanta {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.ofertavacanta to javafx.fxml;
    exports com.example.ofertavacanta;

    opens com.example.ofertavacanta.controller to javafx.fxml;
    exports com.example.ofertavacanta.controller;

    opens com.example.ofertavacanta.domain to javafx.fxml;
    exports com.example.ofertavacanta.domain;

    opens com.example.ofertavacanta.repository to javafx.fxml;
    exports com.example.ofertavacanta.repository;

    opens com.example.ofertavacanta.service to javafx.fxml;
    exports com.example.ofertavacanta.service;

    opens com.example.ofertavacanta.utils to javafx.fxml;
    exports com.example.ofertavacanta.utils;
}