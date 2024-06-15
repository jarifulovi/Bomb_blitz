package com.example.mines_sweeper.gridLogic;

import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

import java.util.List;

public class Level2_Grid {

    static int size = 10;
    private int clickedTiles = 0;
    int[][] grid = new int[size][size];
    int[][] gridNumbers = new int[size][size];

    public Level2_Grid(){
        generateGrid();
        clickedTiles = 0;
    }
    private void generateGrid(){
        grid = logic.generateRandomBombPosition(size);
        gridNumbers = logic.generateGridNumbers(size,grid);
    }
    public void changeBombPosition(int row,int col){

        int newCol = logic.generateNewRandomPosition(size,col);

        grid[row][col] = logic.unClicked;
        grid[row][newCol] = logic.bomb;

        // needs to change gridNumbers also
        gridNumbers = logic.generateGridNumbers(size,grid);
    }

    public void zeroClearingTile(GridPane gridPane, int row, int col){
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
                System.out.println("Row : "+coordinate[0]+" col : "+coordinate[1]);
                incrementSaveClicked();
            }

        }
    }
    public void setTextOnButton(Button button,int row,int col){

        logic.cssButtonNumbers(button);
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

    // Didn't need right now
    public void changeTileClicked(int row,int col){
        grid[row][col] = logic.clicked;
    }
    public void changeTileFlagged(int row,int col){
        grid[row][col] = logic.flagged;
    }
    public void changeTileUnclicked(int row,int col){
        grid[row][col] = logic.unClicked;
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
