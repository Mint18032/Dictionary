package com.example.dictionary_graphic;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onSearchButtonClick() {
        welcomeText.setText("You searched!");
    }
}