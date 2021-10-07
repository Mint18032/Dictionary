package com.example.dictionary_graphic;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.awt.event.ActionEvent;
import java.sql.SQLException;

public class HelloController {
    @FXML
    private Label result;
    public TextField searchBox;

    String target;

    @FXML
    private void onAddButtonClick() {
        System.out.println("You clicked!");
    }

    @FXML
    public void onSearchButtonClick(MouseEvent mouseEvent) {
        target = searchBox.getText().trim();
        if (target == "") {
            result.setText("No word inserted!");
        } else {
            String exlpain = DictionaryManagement.dictionaryLookup(target);
            result.setText(exlpain);
        }
    }
}