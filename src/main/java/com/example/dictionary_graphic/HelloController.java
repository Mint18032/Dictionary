package com.example.dictionary_graphic;

import com.example.dictionary_graphic.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
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
    @FXML
    private TextArea textArea1;
    @FXML
    private  TextArea textArea2;
    @FXML
    private Text text1;
    @FXML
    private Text text2;
    @FXML
    private Tab translate;
    @FXML
    private String target;
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

    @FXML
    public void textToSpeech() {
        TTS textToSpeech = new TTS("kevin16");
        String word = searchBox.getText();
        textToSpeech.say(word);
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
        searchBox.setText("");
    }

    @FXML
    public void searchOnClick(MouseEvent event) {
        String click = listView.getSelectionModel().getSelectedItem();
        if (click == null || click.isEmpty()) {
            result.setText("Nothing Selected!");
        } else {
            searchBox.setText(click);
            String exlpain = DictionaryManagement.dictionaryLookup(click);
            result.setText(exlpain);
        }
    }

    @FXML
    public void searchOnline() {
        try {
            if (text1.getText().equals("Tiếng Anh")) {
                API api = new API();
                textArea2.setText(api.translate("en", "vi", textArea1.getText()));
            } else {
                API api = new API();
                textArea2.setText(api.translate("vi", "en", textArea1.getText()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void changeLanguage() {
        String s = text1.getText();
        text1.setText(text2.getText());
        text2.setText(s);
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
    
