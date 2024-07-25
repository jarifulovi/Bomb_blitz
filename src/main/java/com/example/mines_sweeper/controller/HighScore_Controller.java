package com.example.mines_sweeper.controller;

import com.example.mines_sweeper.gridLogic.logic;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class HighScore_Controller implements Initializable {

    @FXML
    public AnchorPane high_score_ap;
    @FXML
    public Button high_score_button1,high_score_button2,high_score_button3,high_score_back_button;
    @FXML
    public Label high_score_label;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        set_css();
        high_score_button1.setOnAction(event -> show_high_score(1));
        high_score_button2.setOnAction(event -> show_high_score(2));
        high_score_button3.setOnAction(event -> show_high_score(3));
        high_score_back_button.setOnAction(event -> logic.loadFxml(logic.MAINPAGE,event));
    }
    private void set_css(){

        high_score_ap.setStyle("-fx-background-color: #808080;");
        high_score_label.setStyle("-fx-background-color: #A0A0A0");
        high_score_button1.setStyle("-fx-background-color: #E0E0E0");
        high_score_button2.setStyle("-fx-background-color: #E0E0E0");
        high_score_button3.setStyle("-fx-background-color: #E0E0E0");
        high_score_back_button.setStyle("-fx-background-color: #E0E0E0");
    }

    private void show_high_score(int level){

        // Clear previous scores
        high_score_label.setText("");

        List<String> highScores = readScoresFromFile(logic.SCORE_DIRECTORY+"highestScore"+level+".txt");

        // Display high scores in the label
        for (int i = 0; i < highScores.size(); i++) {
            String score = highScores.get(i);
            String[] parts = score.split(" ");
            String name = parts[0];
            String time = parts[1];

            String labelText = String.format("%d. %s (%s)%n", i + 1, name, time);
            high_score_label.setText(high_score_label.getText() + labelText);
        }

    }
    private List<String> readScoresFromFile(String fileName) {
        List<String> scores = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                scores.add(line);
            }
        } catch (IOException e) {
            // Handle file IO exception
            e.printStackTrace();
        }
        return scores;
    }
}
