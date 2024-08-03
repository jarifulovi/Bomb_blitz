package com.example.mines_sweeper.controller;

import com.example.mines_sweeper.gridLogic.logic;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;


import java.net.URL;
import java.util.ResourceBundle;

public class HowToPlay implements Initializable {

    @FXML
    private TextArea textArea;
    @FXML
    Button backButton;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        String text = """
                1. Objective: Your goal is to uncover all tiles on the board without clicking on any tiles that contain bombs. If you click on a tile with a bomb, the game ends.

                2. Gameplay:
                   - Click on tiles to uncover them. Each tile may reveal a number indicating how many bombs are adjacent to it.
                   - Use the numbers revealed to deduce which tiles are safe to uncover.
                   - The game is won when all non-bomb tiles are uncovered.

                3. Special Rules:
                   - If you click on a tile that doesn't contain a bomb and the adjacent tiles are also clear (indicated by zeros), those tiles will clear automatically.

                4. Hints for Success:
                   - Proceed cautiously and use logic to avoid clicking on tiles with bombs.
                   - Pay attention to the numbers revealed, as they provide vital clues to avoid bombs.""";


        textArea.setText(text);
        textArea.setStyle("-fx-background-color: #D3EAF7; -fx-border-color: transparent;");
        textArea.setWrapText(true);
        textArea.setEditable(false);

        backButton.getStyleClass().add("button-29");
        backButton.setOnAction(event -> logic.loadFxml(logic.MAINPAGE,event));

    }

}
