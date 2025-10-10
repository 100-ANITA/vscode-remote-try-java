package com.example.banking.ui;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class AccountView extends GridPane {
    private final ListView<String> accounts = new ListView<>();
    private final Label balance = new Label("Balance: --");
    private final TextField amount = new TextField();
    private final Button deposit = new Button("Deposit");
    private final Button withdraw = new Button("Withdraw");

    public AccountView() {
        setHgap(8);
        setVgap(8);
        add(new Label("Accounts"), 0, 0);
        add(accounts, 0, 1, 2, 1);
        add(balance, 0, 2, 2, 1);
        add(new Label("Amount"), 0, 3);
        add(amount, 1, 3);
        add(deposit, 0, 4);
        add(withdraw, 1, 4);
    }
}
