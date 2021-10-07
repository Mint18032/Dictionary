package com.example.dictionary_graphic;

import java.util.LinkedList;
import java.util.Scanner;

public class DictionaryCommandline {
    /**
     * Shows all words with their definitions.
     */
    public static void showAllWords() {
        for (Word w : Dictionary.getWords()) {
            System.out.println(w.getWord_target() + ": " + w.getWord_explain());
        }
    }

    /**
     * Find the matching words.
     */
    public static String dictionarySearcher(String w) {
        String result = "";
        LinkedList<Word> list = Dictionary.getWords();
        for (Word check : list) {
            if (check.getWord_target().contains(w)) {
                result += check.getWord_explain() + "\n";
            } else if (w.charAt(0) < check.getWord_target().charAt(0)) {
                return "No matching word is found!";
            }
        }
        return result;
    }
}
