package com.example.dictionary_graphic;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.net.URL;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

public class HelloController implements Initializable {
    ArrayList<String> list = DictionaryCommandline.listWordTarget();
    @FXML
    private Label result;
    public TextField searchBox;

    String target;

    @FXML
    private ListView<String> listView;

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
//            search(mouseEvent);
            String exlpain = DictionaryManagement.dictionaryLookup(target);
            result.setText(exlpain);
        }
    }

    @FXML
    public void search(KeyEvent event) {
        listView.getItems().clear();
        listView.getItems().addAll(searchList(searchBox.getText(), list));
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        listView.getItems().addAll(list);
    }

    private List<String> searchList(String searchWords, List<String> listOfStrings) {
        List<String> searchWordArray = Arrays.asList(searchWords.trim().split(" "));
        return listOfStrings.stream().filter(input ->{
            return searchWordArray.stream().allMatch(word -> input.toLowerCase().contains(word.toLowerCase()));
        }).collect(Collectors.toList());
    }
}
    
