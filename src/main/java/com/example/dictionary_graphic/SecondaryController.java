package com.example.dictionary_graphic;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class SecondaryController {
    @FXML
    private TextField word;
    @FXML
    private TextField explain;
    @FXML
    public Label announce;

    /**
     * Creates a new window that executes add word function.
     */
    @FXML
    public static void addNewWord(MouseEvent event) throws IOException {
        Stage AddStage = new Stage(); // Additional Stage
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("add-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        AddStage.getIcons().add(Main.logo);
        AddStage.setTitle("Add new word");
        AddStage.setScene(scene);
        AddStage.initModality(Modality.APPLICATION_MODAL);
        AddStage.setResizable(false);
        AddStage.show();
    }

    /**
     * Creates a new window that executes fix word function.
     */
    @FXML
    public static void fixWord(MouseEvent event) throws IOException {
        Stage AddStage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("fix-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        AddStage.getIcons().add(Main.logo);
        AddStage.setTitle("Fix word");
        AddStage.setScene(scene);
        AddStage.initModality(Modality.APPLICATION_MODAL);
        AddStage.setResizable(false);
        AddStage.show();
    }

    /**
     * Creates a new window that executes delete word function.
     */
    @FXML
    public static void deleteWord(MouseEvent event) throws IOException {
        Stage AddStage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(Main .class.getResource("delete-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        AddStage.getIcons().add(Main.logo);
        AddStage.setTitle("Delete word");
        AddStage.setScene(scene);
        AddStage.initModality(Modality.APPLICATION_MODAL);
        AddStage.setResizable(false);
        AddStage.show();
    }

    /**
     * Executes add word function.
     */
    @FXML
    private void onAddClick(MouseEvent event) throws SQLException {
        String w = word.getText().trim();
        String e = explain.getText().trim();
        if (w.length() == 0) {
            announce.setText("Please type your word!");
            return;
        } else if (e.length() == 0) {
            announce.setText("Please type the word's explanation!");
            return;
        }
        announce.setText(DictionaryManagement.insertWord(w, e));
        word.setText("");
        explain.setText("");
    }

    /**
     * Executes fix word function.
     */
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

    /**
     * Executes delete word function.
     */
    @FXML
    private void onDeleteClick(MouseEvent event) {
        String w = word.getText().trim();
        if (w.length() == 0) {
            announce.setText("Please type the word!");
            return;
        }
        announce.setText(DictionaryManagement.deleteWord(w));
        word.setText("");
    }

}
