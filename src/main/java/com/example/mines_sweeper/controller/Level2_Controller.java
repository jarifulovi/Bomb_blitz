package com.example.mines_sweeper.controller;

import com.example.mines_sweeper.gridLogic.Grid.Level2_Level_Grid;
import com.example.mines_sweeper.gridLogic.Grid.Level_Grid;
import com.example.mines_sweeper.gridLogic.MenuManager;
import com.example.mines_sweeper.gridLogic.Timer;
import com.example.mines_sweeper.gridLogic.logic;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.ResourceBundle;

public class Level2_Controller implements Initializable {

    private Level_Grid levelGrid;
    private MenuManager menuManager;
    private Timer timer;
    public int level = 2;
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
        levelGrid = new Level2_Level_Grid();
        menuManager = new MenuManager(gridMenu,level);
        timer = new Timer();
        firstClick = true;
        isGameOver = false;
    }

    @FXML
    public void tilesFlagged(MouseEvent mouseEvent){

        if(isGameOver) return;

        if(mouseEvent.getButton() == MouseButton.SECONDARY){
            Button clickedButton = (Button) mouseEvent.getSource();

            assert clickedButton != null;
            String id = clickedButton.getId();
            clickedRow = id.charAt(7) - '0';
            clickedCol = id.charAt(8) - '0';

            if(levelGrid.isTileClicked(clickedRow,clickedCol)){
                return;
            }

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
            // count the time
            timer.stop();
            isGameOver = true;
            logic.loadFxmlModal(logic.WINPANEL,event,level,timer.getElapsedTime());
            // save it in a file further with name

        }
        firstClick = false;
    }

}
