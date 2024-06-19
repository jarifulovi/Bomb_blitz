package com.example.mines_sweeper.controller;

import com.example.mines_sweeper.gridLogic.*;
import com.example.mines_sweeper.gridLogic.Grid.Level1_Level_Grid;
import com.example.mines_sweeper.gridLogic.Grid.Level_Grid;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class Level1_Controller implements Initializable {

    private Level_Grid levelGrid;
    private MenuManager menuManager;
    private Timer timer;

    private final int level = 1;
    private int clickedRow,clickedCol;
    private boolean isGameOver;
    private boolean firstClick;
    @FXML
    public GridPane gridPane;
    @FXML
    public MenuBar gridMenu;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Generate the grid with bomb
        levelGrid = new Level1_Level_Grid();
        menuManager = new MenuManager(gridMenu,level);
        timer = new Timer();
        firstClick = true;
        isGameOver = false;
        levelGrid.displayGrid();
    }

    @FXML
    public void tileFlagged(MouseEvent mouseEvent){

        if(isGameOver) return;

        Button clickedButton = (Button) mouseEvent.getSource();

        assert clickedButton != null;
        String id = clickedButton.getId();
        clickedRow = id.charAt(7) - '0';
        clickedCol = id.charAt(8) - '0';

        if(levelGrid.isTileClicked(clickedRow,clickedCol)){
            System.out.println("already clicked");
            return;
        }

        if(mouseEvent.getButton() == MouseButton.SECONDARY){
            levelGrid.changeTileFlagged(gridPane,clickedRow,clickedCol);
        }

    }
    @FXML
    public void tilesClicked(ActionEvent event){

        // if game over
        if(isGameOver) return;


        Button clickedButton = (Button) event.getSource();


        assert clickedButton != null;
        String id = clickedButton.getId();
        clickedRow = id.charAt(7) - '0';
        clickedCol = id.charAt(8) - '0';


        if(levelGrid.isTileClicked(clickedRow,clickedCol)){
            System.out.println("already clicked");
            return;
        }

        if(levelGrid.isTileFLagged(clickedRow,clickedCol)){
            levelGrid.changeTileUnclicked(clickedRow,clickedCol);
            return;
        }

        if(firstClick){
            timer.start();
            if(levelGrid.isContainsBomb(clickedRow,clickedCol)){
                levelGrid.changeBombPosition(clickedRow,clickedCol);
            }

            levelGrid.zeroClearingTile(gridPane,clickedRow,clickedCol);
        }


        if(levelGrid.isContainsBomb(clickedRow,clickedCol)){
            System.out.println("bomb!!");
            isGameOver = true;

            levelGrid.bombAndLosePanelView(gridPane,event,level,timer.getElapsedTime());

        }

        else {
            // valid tile clicked

            levelGrid.incrementSaveClicked();
            levelGrid.changeTileClicked(clickedRow,clickedCol);
            levelGrid.setTextOnButton(clickedButton,clickedRow,clickedCol);
        }

        // checking for win
        if(levelGrid.checkForWon()){
            System.out.println("You won!!!");

            timer.stop();
            isGameOver = true;
            logic.loadFxmlModal(logic.WINPANEL,event,level,timer.getElapsedTime());

        }
        firstClick = false;
        // For debugging purpose
        levelGrid.displayGrid();
    }

}