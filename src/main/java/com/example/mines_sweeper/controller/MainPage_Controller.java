package com.example.mines_sweeper.controller;

import com.example.mines_sweeper.gridLogic.logic;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class MainPage_Controller implements Initializable {

    @FXML
    public AnchorPane mainPageAnchorPane;
    public Label mainPageLabel;
    public Button level1Button,level2Button,level3Button;



    public void button1Clicked(ActionEvent event){
        logic.loadFxml(logic.LEVEL1FXML,event);
    }
    public void button2Clicked(ActionEvent event){
        logic.loadFxml(logic.LEVEL2FXML,event);
    }
    public void button3Clicked(ActionEvent event){
        logic.loadFxml(logic.LEVEL3FXML,event);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setBackground();
    }

    private void setBackground(){

        mainPageAnchorPane.setStyle("-fx-background-color: #BFB7B7;");
    }
}
