package com.example.dictionary_graphic;

import com.example.dictionary_graphic.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import org.controlsfx.control.textfield.TextFields;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class HelloController implements Initializable {
    ArrayList<String> listword = Dictionary.listWordTarget();

    @FXML
    private WebView webView;
    @FXML
    private WebView webView2;
    @FXML
    private TextArea textArea1;
    @FXML
    private TextArea textArea2;
    @FXML
    private Text text1;
    @FXML
    private Text text2;
    @FXML
    private TextField searchBox;
    @FXML
    private Tab translate;
    @FXML
    private String target;
    @FXML
    private Button speak;
    @FXML
    private ListView<String> listView;
    @FXML
    private Button language;
    private boolean updated;
    private Tooltip tooltip1;

    @FXML
    private void onAddButtonClick() {
        System.out.println("You clicked!");
    }

    @FXML
    public void search(KeyEvent event) {
        listView.getItems().clear();
        listView.getItems().addAll(DictionaryManagement.dictionaryRelatedWord(searchBox.getText()));
        listView.setOpacity(100);
    }

    @FXML
    public void textToSpeech() {
        TTS textToSpeech = new TTS("kevin16");
        String word = searchBox.getText();
        textToSpeech.say(word);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        listView.setOpacity(0);
        listView.getItems().addAll(listword);
        speak.setOpacity(0);
        webView.setOpacity(0);
        webView.getEngine().setUserStyleSheetLocation(getClass().getResource("/com/example/dictionary_graphic/style.css").toString());
        updated = true;
        tooltip1 = new Tooltip("Dịch tiếng Anh.");
        language.setTooltip(tooltip1);
    }

    @FXML
    public void onSearchButtonClick(MouseEvent mouseEvent) {
        target = searchBox.getText().trim();
        speak.setOpacity(100);
        webView.setOpacity(100);
        if (target == "") {
            webView.getEngine().loadContent("No word inserted!");
        } else {
            String explain = DictionaryManagement.dictionaryLookup(target);
            webView.getEngine().loadContent(explain);
        }
    }

    @FXML
    public void searchOnClick(MouseEvent event) {
        target = listView.getSelectionModel().getSelectedItem();
        speak.setOpacity(100);
        webView.setOpacity(100);
        if (target == null || target.isEmpty()) {
            webView.getEngine().loadContent("Nothing Selected!");
        } else {
            searchBox.setText(target);
            String explain = DictionaryManagement.dictionaryLookup(target);
            webView.getEngine().loadContent(explain);
        }
    }

    @FXML
    public void searchOnline() {
        try {
            if (text1.getText().equals("Tiếng Anh")) {
                API api = new API();
                webView2.getEngine().loadContent((api.translate("en", "vi", textArea1.getText())));
            } else {
                API api = new API();
                webView2.getEngine().loadContent((api.translate("vi", "en", textArea1.getText())));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void changeLanguage() {
        String s = text1.getText();
        text1.setText(text2.getText());
        text2.setText(s);
        if (text1.getText().length() == 10) { // Tieng Viet
            language.setText("EN-VI");
            tooltip1.setText("Dịch tiếng Anh.");
            textArea1.setPromptText("Gõ vào đây.");
        } else {
            language.setText("VI-EN");
            tooltip1.setText("Translate Vietnamese.");
            textArea1.setPromptText("Type here.");
        }
    }

    @FXML
    private void update() {
        if (updated) return;
        listword = Dictionary.listWordTarget();
        listView.getItems().clear();
        listView.getItems().addAll(listword);
        listView.setOpacity(0);
        speak.setOpacity(0);
        searchBox.setText("");
        webView.setOpacity(0);
        webView.getEngine().loadContent("");
        updated = true;
    }

    @FXML
    private void addNewWord(MouseEvent event) throws IOException {
        SecondaryController.addNewWord(event);
        updated = false;
    }

    @FXML
    private void fixWord(MouseEvent event) throws IOException {
        SecondaryController.fixWord(event);
        updated = false;
    }

    @FXML
    private void deleteWord(MouseEvent event) throws IOException {
        SecondaryController.deleteWord(event);
        updated = false;
    }
}
    
