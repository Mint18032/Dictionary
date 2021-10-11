package com.example.dictionary_graphic;

import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Locale;
import java.util.Scanner;

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
        w = w.toLowerCase(Locale.ROOT);
        for (Word check : Dictionary.getWords()) {
            if (check.getWord_target().equalsIgnoreCase(w)) {
                return (check.getWord_explain());
            } else if (w.charAt(0) < check.getWord_target().charAt(0)) {
                break;
            }
        }
        return "This word doesn't exist!";
    }

    public static ArrayList<String> dictionaryRelatedWord(String w) {
        w = w.toLowerCase(Locale.ROOT);
        ArrayList<String> list = new ArrayList<>();
        for (Word check : Dictionary.getWords()) {
            if (check.getWord_target().startsWith(w)) {
                list.add(check.getWord_target());
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
            if (check.getWord_target().equals(w)) {
                list.remove(check);
                break;
            } else if (w.charAt(0) < check.getWord_target().charAt(0)) {
                return "This word doesn't exist or is already deleted!";
            }
        }

        Dictionary.setWords(list);
        System.out.println();

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
        target = target.toLowerCase();
        for (Word check : list) {
            if (check.getWord_target().equals(target)) {
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
