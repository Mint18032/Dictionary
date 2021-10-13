package com.example.dictionary_graphic;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;

public class DictionaryManagement {
    /**
     * Insert Word.
     */
    public static String insertWord(String target, String explain) {
        Word word = new Word(target, explain);
        Dictionary.addWord(word);
        exportToFile(word);
        return (target + " is successfully added to dictionary!");
    }

    /**
     * Lookup Word.
     */
    public static String dictionaryLookup(String w) {
        w = w.toLowerCase();
        for (Word check : Dictionary.getWords()) {
            if (check.getWord_target().equalsIgnoreCase(w)) {
                return (check.getWord_explain());
            } else if (w.charAt(0) < check.getWord_target().charAt(0)) {
                break;
            }
        }
        return "This word doesn't exist!";
    }

    /**
     * Find related words.
     */
    public static ArrayList<String> dictionaryRelatedWord(String w) {
        w = w.toLowerCase();
        ArrayList<String> list = new ArrayList<>();
        for (Word check : Dictionary.getWords()) {
            if (check.getWord_target().startsWith(w)) {
                list.add(check.getWord_target());
            } else if (w.charAt(0) < check.getWord_target().charAt(0)) {
                break;
            }
        }
        return list;
    }
    /**
     * Remove a word from the Dictionary.
     */
    public static String deleteWord(String w) {
        // Delete from list.
        LinkedList<Word> list = Dictionary.getWords();
        for (Word check : list) {
            if (check.getWord_target().equalsIgnoreCase(w)) {
                list.remove(check);
                break;
            } else if (w.charAt(0) < check.getWord_target().charAt(0)) {
                return "This word doesn't exist or is already deleted!";
            }
        }
        Dictionary.setWords(list);

        // Delete from database.
        try {
            DictionaryManager.deleteWord(w);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "The word is successfully deleted!";
    }

    /**
     * Fix Word.
     */
    public static String fixWord(String target, String explain) {
        // Fix list.
        LinkedList<Word> list = Dictionary.getWords();
        for (Word check : list) {
            if (check.getWord_target().equalsIgnoreCase(target)) {
                check.setWord_explain(explain);
                break;
            } else if (target.charAt(0) < check.getWord_target().charAt(0)) {
                return "This word doesn't exist!";
            }
        }

        // Fix in database.
        try {
            DictionaryManager.fixWord(target, explain);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "Fixed successfully!";
    }

    /**
     * Export to file.
     */
    public static void exportToFile(Word word) {
        try {
            DictionaryManager.insertWord(word.getWord_target(), word.getWord_explain());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
