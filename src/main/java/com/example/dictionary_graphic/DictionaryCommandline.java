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
    public static void dictionarySearcher() {
        System.out.println("Type the word you want to search:");
        Scanner in = new Scanner(System.in);
        String w = in.next().toLowerCase();
        LinkedList<Word> list = Dictionary.getWords();
        boolean present = false;
        for (Word check : list) {
            if (check.getWord_target().contains(w)) {
                present = true;
                System.out.println(check.getWord_target() + ": " + check.getWord_explain());
            } else if (w.charAt(0) < check.getWord_target().charAt(0)) {
                break;
            }
        }
        if (!present) {
            System.out.println("No matching word is found!");
        }
        in.close();
    }
}
