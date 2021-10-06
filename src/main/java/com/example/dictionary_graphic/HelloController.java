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

    @FXML
    protected void onAddButtonClick() {
        String ins = "file";
        DictionaryCommandline.dictionaryAdvanced(ins);
//        ins = "insert";
//        DictionaryCommandline.dictionaryAdvanced(ins);
//        ins = "delete";
//        DictionaryCommandline.dictionaryAdvanced(ins);
        ins = "show";
        DictionaryCommandline.dictionaryAdvanced(ins);
//        DictionaryCommandline.dictionaryAdvanced("fix");
//        ins = "lookup";
//        DictionaryCommandline.dictionaryAdvanced(ins);
//        DictionaryCommandline.dictionaryAdvanced("search");
    }
}