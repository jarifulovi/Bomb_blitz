package com.example.mines_sweeper.gridLogic.Grid;

import com.example.mines_sweeper.gridLogic.AdjacentZeroUtil;
import com.example.mines_sweeper.gridLogic.logic;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Level_Grid {

    public int size;
    public int clickedTiles;
    int[][] grid = new int[size][size];
    int[][] gridNumbers = new int[size][size];

    public Level_Grid(int size){
        this.size = size;
        clickedTiles = 0;
        generateGrid();
    }

    public void changeBombPosition(int row,int col){

        int newCol = logic.generateNewRandomPosition(size,col);

        grid[row][col] = logic.unClicked;
        grid[row][newCol] = logic.bomb;

        // needs to change gridNumbers also
        gridNumbers = logic.generateGridNumbers(size,grid);
    }
    private void generateGrid(){
        grid = logic.generateRandomBombPosition(size);
        gridNumbers = logic.generateGridNumbers(size,grid);
    }
    public void setTextOnButton(Button button, int row, int col){

        // If the button set any graphics
        button.setGraphic(null);
        logic.cssButtonNumbers(button,size);
        int number = getGridNumber(row,col);
        button.setText(String.valueOf(number));

    }

    public int getGridNumber(int row,int col){
        return gridNumbers[row][col];
    }
    public boolean isContainsBomb(int row,int col){
        return grid[row][col] == logic.bomb;
    }
    public boolean isTileClicked(int row,int col){
        return grid[row][col] == logic.clicked;
    }
    public boolean isTileFLagged(int row,int col){
        return grid[row][col] == logic.flagged;
    }

    public void changeTileClicked(int row,int col){
        grid[row][col] = logic.clicked;
    }
    public void changeTileFlagged(GridPane gridPane,int row,int col){

        if(grid[row][col] == logic.flagged) return;

        grid[row][col] = logic.flagged;
        setFlagImage(gridPane,row,col);
    }
    public void changeTileUnclicked(GridPane gridPane,int row,int col){

        // If the tile has previously a bomb
        if(gridNumbers[row][col] == -1)
            grid[row][col] = logic.bomb;
        else
            grid[row][col] = logic.unClicked;

        unsetFlagImage(gridPane,row,col);
    }

    public void displayBombs(GridPane gridPane) {
        String imagePath = logic.BOMB_ICON;
        try {
            // Load bomb image
            Image bombImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream(imagePath)));


            double imageSize;

            if(size == 5)       imageSize = 80;
            else if(size == 10) imageSize = 40;
            else                imageSize = 20;

            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if (isContainsBomb(i, j)) {

                        // Create ImageView with the bomb image
                        ImageView imageView = new ImageView(bombImage);
                        imageView.setFitWidth(imageSize);
                        imageView.setFitHeight(imageSize);

                        // Create StackPane to center the ImageView
                        StackPane stackPane = new StackPane();
                        stackPane.setAlignment(Pos.CENTER);
                        stackPane.getChildren().add(imageView);


                        // Add StackPane to GridPane at position (i, j)
                        gridPane.add(stackPane, j, i);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setFlagImage(GridPane gridPane, int row, int col) {
        String imagePath = logic.FLAG_ICON;
        try {
            // Load flag image
            Image flagImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream(imagePath)));

            // Create a copy of the children list to avoid concurrent modification issues
            List<Node> childrenCopy = new ArrayList<>(gridPane.getChildren());

            // Iterate over the copy of the children list
            for (Node node : childrenCopy) {
                Integer rowIndex = GridPane.getRowIndex(node);
                Integer colIndex = GridPane.getColumnIndex(node);
                if (rowIndex != null && colIndex != null && rowIndex == row && colIndex == col && node instanceof Button button) {
                    double width = button.getWidth();
                    double height = button.getHeight();

                    // Create ImageView with the flag image
                    ImageView imageView = new ImageView(flagImage);

                    double fitWidth = width*((double) 5 /size)*0.8;
                    double fitHeight = height*((double) 5 /size)*0.8;
                    imageView.setFitWidth(fitWidth);
                    imageView.setFitHeight(fitHeight);

                    // Set the graphic of the button
                    button.setGraphic(imageView);

                    // Ensure only the image is displayed
                    //button.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
                    return;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void unsetFlagImage(GridPane gridPane, int row, int col) {
        try {

            // Create a copy of the children list to avoid concurrent modification issues
            List<Node> childrenCopy = new ArrayList<>(gridPane.getChildren());

            // Iterate over the copy of the children list
            for (Node node : childrenCopy) {
                Integer rowIndex = GridPane.getRowIndex(node);
                Integer colIndex = GridPane.getColumnIndex(node);
                if (rowIndex != null && colIndex != null && rowIndex == row && colIndex == col && node instanceof Button button) {
                    button.setGraphic(null);
                    return;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void bombAndLosePanelView(GridPane gp, ActionEvent e,int lvl,double time){

        displayBombs(gp);

        PauseTransition pause = new PauseTransition(Duration.seconds(1));
        pause.setOnFinished(event1 -> {
            // Code to execute after the delay (e.g., start game, proceed to next step)
            logic.loadFxmlModal(logic.LOSEPANEL,e,lvl,time);
        });
        pause.play();
    }


    public void zeroClearingTile(GridPane gridPane,int row,int col){
        // first check if it is 0
        if(gridNumbers[row][col] == 0){
            // get all the adjacent row,col

            List<int[]> adjacentZero = AdjacentZeroUtil.findAdjacentZeros(gridNumbers,row,col,size);

            // get the buttons on that position
            Button[] adjacentZeroButtons = AdjacentZeroUtil.getButtons(gridPane,adjacentZero);


            for(int i=0;i<adjacentZeroButtons.length;i++){

                setTextOnButton(adjacentZeroButtons[i],adjacentZero.get(i)[0],adjacentZero.get(i)[1]);
            }
            // make all the buttons clicked
            for(int[] coordinate : adjacentZero){

                grid[coordinate[0]][coordinate[1]] = logic.clicked;
                incrementSaveClicked();
            }

        }
    }

    public void incrementSaveClicked(){
        clickedTiles++;
    }
    public boolean checkForWon(){
        return clickedTiles == size*size-size;
    }
    public void displayGrid(){
        for(int[] x : grid){
            for(int y : x){
                System.out.print(y+" ");
            }
            System.out.println();
        }
    }
}
