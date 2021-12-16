package com.example.dictionary_graphic.managers;

import com.example.dictionary_graphic.words.Dictionary;
import com.example.dictionary_graphic.words.Word;

public class DictionaryCommandline {
    /**
     * Shows all words with their definitions.
     */
    public static void showAllWords() {
        for (Word w : Dictionary.getWords()) {
            System.out.println(w.getWord_target() + ": " + w.getWord_explain());
        }
    }
}
