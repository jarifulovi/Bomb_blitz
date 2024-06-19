package com.example.mines_sweeper.gridLogic;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.List;

public abstract class AdjacentZeroUtil {

    // The fxml for each level must be designed by row-wise
    // meaning first add all column buttons for each row

    public static List<int[]> findAdjacentZeros(int[][] gridNumbers, int row, int col, int size) {
        List<int[]> adjacentZeros = new ArrayList<>();
        boolean[][] visited = new boolean[size][size];

        // Explore adjacent tiles recursively
        exploreAdjacent(gridNumbers, row, col, size, visited, adjacentZeros,row,col);

        return adjacentZeros;
    }
    private static void exploreAdjacent(int[][] gridNumbers, int row, int col, int size, boolean[][] visited, List<int[]> adjacentZeros, int firstClickedRow, int firstClickedCol) {
        // Base case: out of bounds or already visited
        if (row < 0 || row >= size || col < 0 || col >= size || visited[row][col]) {
            return;
        }

        // Mark current tile as visited
        visited[row][col] = true;

        // If the current tile is not 0, stop exploring
        if (gridNumbers[row][col] != 0) {
            return;
        }

        // Exclude the first clicked position
        if (row != firstClickedRow || col != firstClickedCol) {
            // Add current tile to the list of adjacent zeros
            adjacentZeros.add(new int[]{row, col});
        }



        // Explore adjacent tiles recursively
        exploreAdjacent(gridNumbers, row - 1, col, size, visited, adjacentZeros, firstClickedRow, firstClickedCol); // Up
        exploreAdjacent(gridNumbers, row + 1, col, size, visited, adjacentZeros, firstClickedRow, firstClickedCol); // Down
        exploreAdjacent(gridNumbers, row, col - 1, size, visited, adjacentZeros, firstClickedRow, firstClickedCol); // Left
        exploreAdjacent(gridNumbers, row, col + 1, size, visited, adjacentZeros, firstClickedRow, firstClickedCol); // Right

        exploreAdjacent(gridNumbers, row - 1, col - 1, size, visited, adjacentZeros, firstClickedRow, firstClickedCol); // Up-Left
        exploreAdjacent(gridNumbers, row - 1, col + 1 , size, visited, adjacentZeros, firstClickedRow, firstClickedCol); // Up-Right
        exploreAdjacent(gridNumbers, row + 1, col - 1, size, visited, adjacentZeros, firstClickedRow, firstClickedCol); // Down-Left
        exploreAdjacent(gridNumbers, row + 1, col + 1, size, visited, adjacentZeros, firstClickedRow, firstClickedCol); // Down-Right

    }


    public static Button[] getButtons(GridPane gridPane, List<int[]> coordinates) {
        ObservableList<Node> children = gridPane.getChildren();


        // Create an array to store the buttons
        Button[] buttons = new Button[coordinates.size()];

        // Get buttons for each coordinate
        for (int i = 0; i < coordinates.size(); i++) {
            int[] coord = coordinates.get(i);
            int row = coord[0];
            int col = coord[1];

            int index = col + row * gridPane.getColumnCount();
            if (index >= 0 && index < children.size() && children.get(index) instanceof Button) {
                buttons[i] = (Button) children.get(index);
            }
        }

        return buttons;
    }
}
