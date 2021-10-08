package com.example.dictionary_graphic;

import java.util.ArrayList;
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
                result = check.getWord_explain();
            } else if (w.charAt(0) < check.getWord_target().charAt(0)) {
                break;
            }
        }
        return result;
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
