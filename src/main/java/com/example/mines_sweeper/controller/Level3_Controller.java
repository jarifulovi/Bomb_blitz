package com.example.mines_sweeper.controller;

import com.example.mines_sweeper.gridLogic.Grid.Level2_Level_Grid;
import com.example.mines_sweeper.gridLogic.Grid.Level3_Level_Grid;
import com.example.mines_sweeper.gridLogic.Grid.Level_Grid;
import com.example.mines_sweeper.gridLogic.MenuManager;
import com.example.mines_sweeper.gridLogic.Timer;
import com.example.mines_sweeper.gridLogic.logic;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.ResourceBundle;

public class Level3_Controller implements Initializable {

    public Level_Grid levelGrid;
    private MenuManager menuManager;
    public Timer timer;
    public int level = 3;
    public int clickedRow,clickedCol;
    public boolean isGameOver;
    public boolean firstClick;
    @FXML
    public GridPane gridPane;
    @FXML
    public MenuBar gridMenu;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Generate the grid with bomb
        levelGrid = new Level3_Level_Grid();
        menuManager = new MenuManager(gridMenu,level);
        timer = new Timer();
        firstClick = true;
        isGameOver = false;
        levelGrid.displayGrid();
    }
    @FXML
    public void tilesClicked(ActionEvent event){

        // if game over
        if(isGameOver) return;


        Button clickedButton = (Button) event.getSource();


        assert clickedButton != null;
        String id = clickedButton.getId();
        clickedRow = Integer.parseInt(id.substring(7,8),16);
        clickedCol = Integer.parseInt(id.substring(8,9),16);



        if(levelGrid.isTileClicked(clickedRow,clickedCol)){
            System.out.println("already clicked");
            return;
        }
        if(levelGrid.isTileFLagged(clickedRow,clickedCol)){
            levelGrid.changeTileUnclicked(gridPane,clickedRow,clickedCol);
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
            // count the time
            timer.stop();
            System.out.println("Time : "+timer.getElapsedTime());
            isGameOver = true;
            logic.loadFxmlModal("winPanel.fxml",event,level,timer.getElapsedTime());
            // save it in a file further with name

        }
        firstClick = false;
        levelGrid.displayGrid();
    }
}
