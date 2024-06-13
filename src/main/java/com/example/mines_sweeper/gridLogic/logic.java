package com.example.mines_sweeper.gridLogic;

import java.util.Random;

public abstract class logic {
    static int unClicked = 0;
    static int clicked = 1;
    static int bomb = 2;
    static int flagged = 3;

    public static int[][] generateRandomBombPosition(int size) {
        int[][] grid = new int[size][size];
        Random random = new Random();

        // Place a bomb in each row at a random column
        for (int row = 0; row < size; row++) {
            int bombColumn = random.nextInt(size);
            grid[row][bombColumn] = bomb;
        }

        return grid;
    }
    public static int[][] generateGridNumbers(int size, int[][] grid) {
        int[][] gridNumbers = new int[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {

                if (grid[i][j] == bomb) {
                    gridNumbers[i][j] = -1;
                    continue; // Skip the rest of the iteration for this cell
                }

                // Count the number of adjacent bombs
                int adjacentBombs = 0;
                for (int row = i - 1; row <= i + 1; row++) {
                    for (int col = j - 1; col <= j + 1; col++) {
                        // Check if the current neighbor cell is within the grid boundaries
                        if (row >= 0 && row < size && col >= 0 && col < size) {
                            // Check if the neighbor cell contains a bomb
                            if (grid[row][col] == bomb) {
                                adjacentBombs++;
                            }
                        }
                    }
                }

                // Set the grid number to the count of adjacent bombs
                gridNumbers[i][j] = adjacentBombs;
            }
        }

        return gridNumbers;
    }
    public static String generateRandomColor(){
        String color = "";
        Random random = new Random();
        int value = random.nextInt(5);

        if(value == 0)      return "red";
        else if(value == 1) return "blue";
        else if(value == 2) return "green";
        else if(value == 3) return "#b8860b";
        else                return "black";

    }
}
