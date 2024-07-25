package com.example.mines_sweeper.controller;

import com.example.mines_sweeper.gridLogic.HighestScoreManager;
import com.example.mines_sweeper.gridLogic.logic;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class WinPanel_Controller {
    @FXML
    public Label you_won_label,timeLabel;
    public TextField nameField;

    private HighestScoreManager highestScoreManager;

    private int level;
    private double time;

    // Must be call after initialize
    public void setInit(int lvl, double time){
        this.level = lvl;
        this.time = time;
        setTimeLabel();
        setNameField();
        highestScoreManager = new HighestScoreManager();
    }

    public void changeWinLabelColor(){
        // change color of you_won_label
    }
    private void setTimeLabel() {
        timeLabel.setText(String.format("%.2f", time));
    }
    private void setNameField() {
        // Set the attributes for the nameField
        nameField.setPromptText("at least 3 char");

        // Attach event listener to restrict input to alphabetic characters
        nameField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("[a-zA-Z]*")) {
                nameField.setText(oldValue);
            }
        });
    }
    @FXML
    public void nameEntered(ActionEvent event){

        String name = nameField.getText();

        if(checkNameValidity(name)){
            // save to file and load main menu
            highestScoreManager.saveHighestScore(name,level,time);
            logic.loadFxml(logic.MAINPAGE,event);
        }
    }
    private boolean checkNameValidity(String name){
        // name should be at least 3 character
        return name.length()>=3;
    }

}
