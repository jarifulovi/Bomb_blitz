package com.example.mines_sweeper.controller;

import com.example.mines_sweeper.gridLogic.logic;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class AboutPanel_Controller implements Initializable {

    @FXML
    public Label aboutLabel;
    @FXML
    public Button backButton;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        String text = "Hi, I am Ovi. This is my first javafx project." +
                " I hope you will like it.";

        aboutLabel.setText(text);
        aboutLabel.setWrapText(true);

        backButton.setOnAction(event -> logic.loadFxml(logic.MAINPAGE,event));
    }
}
