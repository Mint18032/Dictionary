package com.example.dictionary_graphic;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Image logo = new Image("/com/example/dictionary_graphic/logo.jpg");
        stage.getIcons().add(logo);
        stage.setTitle("Khanh & Minh Dictionary");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) throws SQLException {
        //        String input = "help";
//        String s = DictionaryManager.searchWord(input);
//        String khanh = "khanh";
//        DictionaryManager.deleteWord(khanh);
          DictionaryManager.getAllWord();
          DictionaryCommandline.showAllWords();
        //launch();
    }
}