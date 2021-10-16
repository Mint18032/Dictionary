package com.example.dictionary_graphic;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Locale;

public class Dictionary {
    private static LinkedList<Word> words = new LinkedList<Word>();
    public static LinkedList<Word> getWords() {
        return words;
    }

    /**
     * Adds a new word to the last index of the list.
     */
    public static void addWordFromDb(Word newWord) {
        words.add(newWord);
    }

    public static void setWords(LinkedList<Word> w) {
        words = w;
    }

    /**
     * Clears the list.
     */
    public static void clear() {
        words.clear();
    }
}
