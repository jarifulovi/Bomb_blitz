module com.example.mines_sweeper {
    requires javafx.controls;
    requires javafx.graphics;
    requires java.base;
    requires javafx.fxml;

    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.ikonli.core;

    opens com.example.mines_sweeper to javafx.fxml;
    exports com.example.mines_sweeper;
    exports com.example.mines_sweeper.controller;
    opens com.example.mines_sweeper.controller to javafx.fxml;
}