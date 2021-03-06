package com.example.dictionary_graphic.controllers;

import com.example.dictionary_graphic.assistance.API;
import com.example.dictionary_graphic.managers.DictionaryManagement;
import com.example.dictionary_graphic.assistance.TTS;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.scene.web.WebView;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PrimaryController implements Initializable {
    @FXML
    private WebView webView;
    @FXML
    private WebView webView2;
    @FXML
    private TabPane tabPane;
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

    /**
     * Updates the listview every key release event.
     */
    @FXML
    public void search(KeyEvent event) {
        if (searchBox.getText().trim().length() == 0) {
            listView.setOpacity(0);
            listView.setDisable(true);
            return;
        }
        listView.setOpacity(100);
        listView.setDisable(false);
        listView.scrollTo(0);
        listView.getItems().clear();
        listView.getItems().addAll(DictionaryManagement.dictionaryRelatedWord(searchBox.getText().trim()));
        if (listView.getItems().isEmpty())
            listView.setOpacity(0);
    }

    /**
     * Reads the word's target aloud.
     */
    @FXML
    public void textToSpeech() {
        TTS textToSpeech = new TTS("kevin16");
        textToSpeech.say(target);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        listView.setOpacity(0);
        speak.setOpacity(0);
        webView.setOpacity(0);
        webView.getEngine().setUserStyleSheetLocation(getClass().getResource("/com/example/dictionary_graphic/styleSheet/style.css").toString());
        updated = true;
        tooltip1 = new Tooltip("Translate Vietnamese.");
        language.setTooltip(tooltip1);
    }

    /**
     * A look up method that excutes after the search button is clicked.
     */
    @FXML
    public void onSearchButtonClick(MouseEvent mouseEvent) {
        target = searchBox.getText().trim();
        if (target == "") {
            webView.setOpacity(100);
            webView.getEngine().loadContent("No word inserted!");
            listView.setOpacity(0);
            listView.setDisable(true);
        } else {
            String explain = DictionaryManagement.dictionaryLookup(target);
            if (explain.equals("This word doesn't exist!")) {
                alert(mouseEvent);
            } else {
                listView.setOpacity(0);
                listView.setDisable(true);
                speak.setOpacity(100);
                webView.setOpacity(100);
                webView.getEngine().loadContent(explain);
            }
        }
    }

    /**
     * A look up method that excutes when a word in listview is clicked.
     */
    @FXML
    public void searchOnClick(MouseEvent event) {
        target = listView.getSelectionModel().getSelectedItem();
        webView.setOpacity(100);
        if (target == null || target.isEmpty()) {
            webView.getEngine().loadContent("Nothing Selected!");
        } else {
            searchBox.setText(target);
            String explain = DictionaryManagement.dictionaryLookup(target);
            webView.getEngine().loadContent(explain);
            speak.setOpacity(100);
            listView.setOpacity(0);
            listView.setDisable(true);
        }
    }

    /**
     * A look up method using Google API.
     */
    @FXML
    public void searchOnline() {
        try {
            if (text1.getText().equals("English")) {
                API api = new API();
                webView2.getEngine().loadContent((api.translate("en", "vi", textArea1.getText())));
            } else {
                API api = new API();
                webView2.getEngine().loadContent((api.translate("vi", "en", textArea1.getText())));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        updated = false;
    }

    /**
     * Controls language.
     */
    public void changeLanguage() {
        String s = text1.getText();
        text1.setText(text2.getText());
        text2.setText(s);
        if (text1.getText().equals("Vietnamese")) {
            language.setText("EN-VI");
            tooltip1.setText("D???ch ti???ng Anh.");
            textArea1.setPromptText("G?? v??o ????y.");
        } else {
            language.setText("VI-EN");
            tooltip1.setText("Translate Vietnamese.");
            textArea1.setPromptText("Type here.");
        }
    }

    /**
     * Updates data and graphic after changes.
     */
    @FXML
    private void update() {
        if (updated) return;
        listView.scrollTo(0);
        listView.getItems().clear();
        listView.setOpacity(0);
        listView.setDisable(true);
        speak.setOpacity(0);
        searchBox.setText("");
        webView.setOpacity(0);
        webView.getEngine().loadContent("");
        target = "";
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

    /**
     * Shows an alert when the word inserted in the search box does not exist in the dictionary.
     * Suggests using Google API.
     */
    @FXML
    public void alert(MouseEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add(getClass().getResource("/com/example/dictionary_graphic/styleSheet/style.css").toExternalForm());
        alert.setTitle("Word doesn't exist.");
        alert.setHeaderText("This word doesn't exist!");
        alert.setContentText("Do you want to search it online?");
        if (alert.showAndWait().get() == ButtonType.OK) {
            textArea1.setText(target);
            webView.setOpacity(0);
            listView.setOpacity(0);
            listView.setDisable(true);
            speak.setOpacity(0);
            tabPane.getSelectionModel().selectNext();
            searchOnline();
        }
    }
}
    
