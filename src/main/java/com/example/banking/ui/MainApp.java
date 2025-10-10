package com.example.banking.ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class MainApp extends Application {
    @Override
    public void start(Stage stage) {
        GridPane root = new GridPane();
        root.setHgap(8);
        root.setVgap(8);

        Label userLabel = new Label("Username");
        TextField userField = new TextField();
        Label passLabel = new Label("Password");
        TextField passField = new TextField();
        Button loginButton = new Button("Login");

        root.add(userLabel, 0, 0);
        root.add(userField, 1, 0);
        root.add(passLabel, 0, 1);
        root.add(passField, 1, 1);
        root.add(loginButton, 1, 2);

        stage.setTitle("Banking System - Login");
        stage.setScene(new Scene(root, 360, 180));
        stage.show();
    }
}
