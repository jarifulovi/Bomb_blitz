package com.example.mines_sweeper.controller;

import com.example.mines_sweeper.gridLogic.MenuManager;
import com.example.mines_sweeper.gridLogic.logic;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class MainPage_Controller implements Initializable {

    private MenuManager menuManager;
    @FXML
    public AnchorPane mainPageAnchorPane;
    @FXML
    public MenuBar mainPageMenu;
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
        menuManager = new MenuManager(mainPageMenu,-1);
    }

    private void setBackground(){

        mainPageAnchorPane.setStyle("-fx-background-color: #BFB7B7;");
        level1Button.getStyleClass().add("button-19");
        level2Button.getStyleClass().add("button-19");
        level3Button.getStyleClass().add("button-19");
    }
}
