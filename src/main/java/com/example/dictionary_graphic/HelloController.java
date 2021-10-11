package com.example.dictionary_graphic;

import com.example.dictionary_graphic.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import org.controlsfx.control.textfield.TextFields;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class HelloController implements Initializable {
    ArrayList<String> listword = DictionaryCommandline.listWordTarget();

    @FXML
    private Label result;
    public TextField searchBox;
    private TextArea textArea;

    String target;

    @FXML
    private ListView<String> listView;

    @FXML
    private void onAddButtonClick() {
        System.out.println("You clicked!");
    }



    @FXML
    public void search(KeyEvent event) {
        listView.getItems().clear();
        listView.getItems().addAll(DictionaryManagement.dictionaryRelatedWord(searchBox.getText()));
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        listView.getItems().addAll(listword);
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
    public void searchOnClick(MouseEvent event) {
        String click = listView.getSelectionModel().getSelectedItem();
        if (click == null || click.isEmpty()) {
            result.setText("Nothing Selected!");
        } else {
            searchBox.setText(click);
            String exlpain = DictionaryManagement.dictionaryLookup(click);
            //result.setText(exlpain);
        }
    }

    @FXML
    private void addNewWord(MouseEvent event) throws IOException {
        SecondaryController.addNewWord(event);
    }

    @FXML
    private void fixWord(MouseEvent event) throws IOException {
        SecondaryController.fixWord(event);
    }

    @FXML
    private void deleteWord(MouseEvent event) throws IOException {
        SecondaryController.deleteWord(event);
    }
}
    
