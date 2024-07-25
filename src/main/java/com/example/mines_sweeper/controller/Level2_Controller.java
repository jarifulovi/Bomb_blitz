package com.example.mines_sweeper.controller;

import com.example.mines_sweeper.gridLogic.GamePlay;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuBar;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.ResourceBundle;

public class Level2_Controller implements Initializable {

    private GamePlay gamePlay;
    public int level = 2;
    @FXML
    public GridPane gridPane;
    @FXML
    public MenuBar gridMenu;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        gamePlay = new GamePlay(level,gridPane,gridMenu);
    }

    @FXML
    public void tilesFlagged(MouseEvent mouseEvent){
        gamePlay.tileFlag(mouseEvent);
    }
    @FXML
    public void tilesClicked(ActionEvent event){
        gamePlay.play(event);
    }

}
