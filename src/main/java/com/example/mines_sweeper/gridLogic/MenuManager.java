package com.example.mines_sweeper.gridLogic;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

public class MenuManager {

    // Set onAction on each controller and mainPage
    // get parameter level or -1 for mainPage ( no new game option )
    private MenuBar menuBar;
    private int level;

    public MenuManager(MenuBar menuBar,int level){
        this.menuBar = menuBar;
        this.level = level;
        setOnAction();
    }
    private void setOnAction(){
        for (Menu menu : menuBar.getMenus()) {
            for (MenuItem menuItem : menu.getItems()) {
                menuItem.setOnAction(this::handleMenuItemAction);
            }
        }
    }
    private void handleMenuItemAction(ActionEvent event){

        String menuText = ((MenuItem) event.getSource()).getText();

        switch (menuText){
            case "New Game" :

                if     (level == 1)
                    logic.loadFxmlMenu(logic.LEVEL1FXML,event);
                else if(level == 2)
                    logic.loadFxmlMenu(logic.LEVEL2FXML,event);
                else if(level == 3)
                    logic.loadFxmlMenu(logic.LEVEL3FXML,event);

                break;
            case "Sound" :
                System.out.println("sounded");
                break;
            case "Exit" :
                Platform.exit();
                break;
            case "How to play" :
                System.out.println("how to play");
                break;
            case "About" :
                System.out.println("about us");
                break;
        }
    }
}
