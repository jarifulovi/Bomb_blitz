package com.example.mines_sweeper.gridLogic;

import com.example.mines_sweeper.gridLogic.Grid.Level1_Level_Grid;
import com.example.mines_sweeper.gridLogic.Grid.Level2_Level_Grid;
import com.example.mines_sweeper.gridLogic.Grid.Level3_Level_Grid;
import com.example.mines_sweeper.gridLogic.Grid.Level_Grid;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

public class GamePlay {

    private Level_Grid levelGrid;
    private MenuManager menuManager;
    private Timer timer;

    private int level;
    private int clickedRow,clickedCol;
    private boolean isGameOver;
    private boolean firstClick;

    // From controllers
    private GridPane gridPane;

    public GamePlay(int level, GridPane gridPane,MenuBar gridMenu){
        this.level = level;
        this.gridPane = gridPane;
        setLevelGrid();
        menuManager = new MenuManager(gridMenu,level);
        timer = new Timer();
        firstClick = true;
        isGameOver = false;
    }
    private void setLevelGrid(){
        if(level == 1) levelGrid = new Level1_Level_Grid();
        else if(level == 2) levelGrid = new Level2_Level_Grid();
        else if(level == 3) levelGrid = new Level3_Level_Grid();
    }
    private void setPosition(Button clickButton){
        assert clickButton != null;

        String id = clickButton.getId();

        if(level < 3) {
            clickedRow = id.charAt(7) - '0';
            clickedCol = id.charAt(8) - '0';
        }
        else if(level == 3){
            clickedRow = Integer.parseInt(id.substring(7,8),16);
            clickedCol = Integer.parseInt(id.substring(8,9),16);
        }
    }

    public void tileFlag(MouseEvent mouseEvent){

        if(isGameOver) return;

        if(mouseEvent.getButton() == MouseButton.SECONDARY){
            Button clickedButton = (Button) mouseEvent.getSource();

            setPosition(clickedButton);

            if(levelGrid.isTileClicked(clickedRow,clickedCol)){
                return;
            }

            levelGrid.changeTileFlagged(gridPane,clickedRow,clickedCol);
        }

    }

    public void play(ActionEvent event){

        if(isGameOver) return;


        Button clickedButton = (Button) event.getSource();
        setPosition(clickedButton);


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
            // Valid tile clicked

            levelGrid.incrementSaveClicked();
            levelGrid.changeTileClicked(clickedRow,clickedCol);
            levelGrid.setTextOnButton(clickedButton,clickedRow,clickedCol);
        }

        // Checking for win
        if(levelGrid.checkForWon()){

            timer.stop();
            isGameOver = true;
            logic.loadFxmlModal(logic.WINPANEL,event,level,timer.getElapsedTime());

        }
        firstClick = false;
    }
}
