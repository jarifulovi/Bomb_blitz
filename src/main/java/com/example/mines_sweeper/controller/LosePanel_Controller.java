package com.example.mines_sweeper.controller;

import com.example.mines_sweeper.gridLogic.logic;
import com.example.mines_sweeper.mainApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class LosePanel_Controller {
    @FXML
    public AnchorPane losePanelAnchorPane;
    @FXML
    public Button losePanelPGButton,losePanelExitButton;

    private int level;

    public void setBackground(){
        // use anchor-pane
    }

    // must be called after initialize
    public void setLevel(int lvl){
        level = lvl;
    }
    public void playAgain(ActionEvent event){

        // Check level and load its FXML
        String fxmlFile = switch (level) {
            case 1 -> logic.LEVEL1FXML;
            case 2 -> logic.LEVEL2FXML;
            case 3 -> logic.LEVEL3FXML;
            default -> null;
        };

        if (fxmlFile != null) {
            try {
                FXMLLoader loader = new FXMLLoader(mainApplication.class.getResource(fxmlFile));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.setTitle("Bomb Blitz");
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
    public void exit(ActionEvent event){
        // Goes to main menu fxml controller
        logic.loadFxml(logic.MAINPAGE,event);
    }

}
