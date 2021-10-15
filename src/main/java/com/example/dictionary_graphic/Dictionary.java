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
     * Adds a new word to Dictionary and brings it to the relevant index.
     * @param newWord to be added.
     */
    public static void addWord(Word newWord) {
        words.add(newWord);

        for (int i = words.size() - 1; i > 0; --i) {
            if (!words.get(i - 1).isBefore(words.get(i))) {
                Word temp = words.set(i, words.get(i-1));
                words.set(i-1, temp);
            } else {
                break;
            }
        }
    }

    /**
     * An arraylist of the word that will be displayed in the listview.
     */
    public static ArrayList<String> listWordTarget() {
        ArrayList<String> result = new ArrayList<>();
        for (Word check : words) {
            result.add(check.getWord_target());
        }
        return result;
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
