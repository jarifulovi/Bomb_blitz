package com.example.mines_sweeper;

import com.example.mines_sweeper.gridLogic.logic;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class mainApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(mainApplication.class.getResource(logic.MAINPAGE));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Bomb Blitz");
        stage.setScene(scene);

        logic.addCSSInScene(scene);
        // Load application icon
        Image icon = new Image(mainApplication.class.getResourceAsStream(logic.MAIN_ICON));
        stage.getIcons().add(icon);

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}