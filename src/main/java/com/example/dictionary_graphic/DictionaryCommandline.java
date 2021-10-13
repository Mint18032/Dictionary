package com.example.dictionary_graphic;

import java.util.ArrayList;
import java.util.LinkedList;

public class DictionaryCommandline {
    /**
     * Shows all words with their definitions.
     */
    public static void showAllWords() {
        for (Word w : Dictionary.getWords()) {
            System.out.println(w.getWord_target() + ": " + w.getWord_explain());
        }
    }

    public static ArrayList<String> listWordTarget() {
        ArrayList<String> result = new ArrayList<>();
        LinkedList<Word> list = Dictionary.getWords();
        for (Word check : list) {
            result.add(check.getWord_target());
        }
        return result;
    }
}
