package com.example.dictionary_graphic;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class SecondaryController {
    @FXML
    private TextField word;
    @FXML
    private TextField explain;
    @FXML
    public Label announce;

    @FXML
    public static void addNewWord(MouseEvent event) throws IOException {
        Stage AddStage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("add-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Image logo = new Image("/com/example/dictionary_graphic/logo.jpg");
        AddStage.getIcons().add(logo);
        AddStage.setTitle("Add new word");
        AddStage.setScene(scene);
        AddStage.initModality(Modality.APPLICATION_MODAL);
        AddStage.show();
    }

    @FXML
    public static void fixWord(MouseEvent event) throws IOException {
        Stage AddStage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("fix-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Image logo = new Image("/com/example/dictionary_graphic/logo.jpg");
        AddStage.getIcons().add(logo);
        AddStage.setTitle("Fix word");
        AddStage.setScene(scene);
        AddStage.initModality(Modality.APPLICATION_MODAL);
        AddStage.show();
    }

    @FXML
    public static void deleteWord(MouseEvent event) throws IOException {
        Stage AddStage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("delete-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Image logo = new Image("/com/example/dictionary_graphic/logo.jpg");
        AddStage.getIcons().add(logo);
        AddStage.setTitle("Delete word");
        AddStage.setScene(scene);
        AddStage.initModality(Modality.APPLICATION_MODAL);
        AddStage.show();
    }

    @FXML
    private void onAddClick(MouseEvent event) {
        String w = word.getText().trim().toLowerCase();
        String e = explain.getText().trim();
        if (w.length() == 0) {
            announce.setText("Please type your word!");
            return;
        } else if (e.length() == 0) {
            announce.setText("Please type the word's explanation!");
            return;
        }
        announce.setText(DictionaryManagement.insertWord(w, e));
    }

    @FXML
    private void onFixClick(MouseEvent event) {
        String w = word.getText().trim();
        String e = explain.getText().trim();
        if (w.length() == 0) {
            announce.setText("Please type your word!");
            return;
        } else if (e.length() == 0) {
            announce.setText("Please type new explanation!");
            return;
        }
        announce.setText(DictionaryManagement.fixWord(w, e));
        word.setText("");
        explain.setText("");
    }

    @FXML
    private void onDeleteClick(MouseEvent event) {
        String w = word.getText().trim();
        if (w.length() == 0) {
            announce.setText("Please type the word!");
            return;
        }
        announce.setText(DictionaryManagement.deleteWord(w));
    }

}
