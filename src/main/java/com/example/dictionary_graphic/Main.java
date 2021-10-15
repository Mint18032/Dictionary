package com.example.dictionary_graphic;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class Main extends Application {
    public static Image logo;
    /**
     * Starts application's primary window.
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("primary-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        logo = new Image("/com/example/dictionary_graphic/image/logo.jpg");
        stage.getIcons().add(logo);
        stage.setTitle("Khanh & Minh Dictionary");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) throws SQLException {
          DictionaryManager.getAllWord();
          launch();
    }
}