package com.example.mines_sweeper.gridLogic;

import com.example.mines_sweeper.controller.LosePanel_Controller;
import com.example.mines_sweeper.controller.WinPanel_Controller;
import com.example.mines_sweeper.mainApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Objects;
import java.util.Random;

public abstract class logic {
    public static int unClicked = 0;
    public static int clicked = 1;
    public static int bomb = 2;
    public static int flagged = 3;

    public static String LEVEL1FXML = "level_1_grid.fxml";
    public static String LEVEL2FXML = "level_2_grid.fxml";
    public static String LEVEL3FXML = "level_3_grid.fxml";
    public static String LOSEPANEL = "losePanel.fxml";
    public static String WINPANEL = "winPanel.fxml";
    public static String MAINPAGE = "mainPage.fxml";
    public static String HIGHSCORE = "high_score.fxml";
    public static String HOWTOPLAY = "how_to_play.fxml";
    public static String ABOUT = "aboutPanel.fxml";
    public static String CSS_FILE = "buttonUI.css";
    public static String SCORE_DIRECTORY = "src/main/resources/com/example/mines_sweeper/Files/";
    public static String MAIN_ICON = "/com/example/mines_sweeper/Icons/main_icon.png";
    public static String BOMB_ICON = "/com/example/mines_sweeper/Icons/bomb.png";
    public static String FLAG_ICON = "/com/example/mines_sweeper/Icons/flag.png";
    public static String transparent = "-fx-background-color: transparent;";
    public static String boldNumber = "-fx-font-weight: bold;";


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
    public static void cssButtonNumbers(Button button,int size){
        String color = logic.generateRandomColor();
        double fontSize;


        if(size == 5)       fontSize = 48;
        else if(size == 10) fontSize = 12;
        else                fontSize = 12;

        String css = String.format("-fx-text-fill: %s; -fx-font-size: %.1fpt;%s%s", color, fontSize,logic.boldNumber,logic.transparent);
        button.setStyle(css);

    }

    public static int generateNewRandomPosition(int size,int prePos){

        Random random = new Random();
        int newPos;
        do{
            newPos = random.nextInt(size);
        }while(newPos == prePos);

        return newPos;
    }
    public static String generateRandomColor(){

        Random random = new Random();
        int value = random.nextInt(5);

        if(value == 0)      return "red";
        else if(value == 1) return "blue";
        else if(value == 2) return "green";
        else if(value == 3) return "#b8860b";
        else                return "black";

    }
    public static void addCSSInScene(Scene scene){
        String cssPath = CSS_FILE;
        scene.getStylesheets().add(Objects.requireNonNull(mainApplication.class.getResource(cssPath)).toExternalForm());
    }

    // ********** Loading fxml methods ****************

    public static void loadFxml(String fxmlFile, ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(mainApplication.class.getResource(fxmlFile));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            addCSSInScene(scene);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Bomb Blitz");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void loadFxmlMenu(String fxmlFile, ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(mainApplication.class.getResource(fxmlFile));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            addCSSInScene(scene);

            // Get the Stage from the MenuItem's Window
            MenuItem menuItem = (MenuItem) event.getSource();
            Stage stage = (Stage) menuItem.getParentPopup().getOwnerWindow();

            stage.setScene(scene);
            stage.setTitle("Bomb Blitz");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void loadFxmlModal(String fxmlFile,ActionEvent event,int lvl,double time){
        try {
            FXMLLoader loader = new FXMLLoader(mainApplication.class.getResource(fxmlFile));
            Parent root = loader.load();


            if(fxmlFile.equals(LOSEPANEL)) {
                LosePanel_Controller losePanelController = loader.getController();
                losePanelController.setLevel(lvl);
            }
            else if(fxmlFile.equals(WINPANEL)){
                WinPanel_Controller winPanelController = loader.getController();
                winPanelController.setInit(lvl,time);
            }

            Scene scene = new Scene(root);
            addCSSInScene(scene);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Bomb Blitz");

            // Show the panel as a dialog
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
