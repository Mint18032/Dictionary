package com.example.dictionary_graphic.managers;

import com.example.dictionary_graphic.words.Dictionary;
import com.example.dictionary_graphic.words.Word;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;

public class DictionaryManagement {
    /**
     * Inserts Word.
     */
    public static String insertWord(String target, String explain) {
        if (!dictionaryLookup(target).equals("This word doesn't exist!")) {
            return "This word is already exist!";
        }

        explain = "<C><F><I><N><Q><big><b id='txt'>"+target+"</b></big><br />-"+explain+"</Q></N></I></F></C>";
        Word word = new Word(target, explain);
        try {
            // Add word to database.
            DictionaryManager.insertWord(target, explain);
            // Update list.
            Dictionary.clear();
            DictionaryManager.getAllWord();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return (target + " is successfully added to dictionary!");
    }

    /**
     * Looks up Word to find their explanations.
     */
    public static String dictionaryLookup(String w) {
        for (Word check : Dictionary.getWords()) {
            if (check.getWord_target().equalsIgnoreCase(w)) {
                return (check.getWord_explain());
            } else if (w.compareToIgnoreCase(check.getWord_target()) < 0) {
                break;
            }
        }
        return "This word doesn't exist!";
    }

    /**
     * Finds related words to be showed in listview.
     */
    public static ArrayList<String> dictionaryRelatedWord(String w) {
        ArrayList<String> list = new ArrayList<>();
        if (w.length() == 0) return list;
        w = w.toLowerCase();
        for (Word check : Dictionary.getWords()) {
            if (check.getWord_target().toLowerCase().startsWith(w) && list.size() <= 100) {
                list.add(check.getWord_target());
            } else if (w.charAt(0) < check.getWord_target().toLowerCase().charAt(0) || list.size() > 100) {
                break;
            }
        }

        return list;
    }

    /**
     * Removes a word from the Dictionary.
     */
    public static String deleteWord(String w) {
        LinkedList<Word> list = Dictionary.getWords();
        for (Word check : list) {
            if (check.getWord_target().equalsIgnoreCase(w)) {
                // Remove from list.
                list.remove(check);
                Dictionary.setWords(list);
                // Remove from database.
                try {
                    DictionaryManager.deleteWord(w);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            } else if (w.compareToIgnoreCase(check.getWord_target()) < 0) {
                return "This word doesn't exist or is already deleted!";
            }
        }

        return "The word is successfully deleted!";
    }

    /**
     * Fixes Word.explain.
     */
    public static String fixWord(String target, String explain) {
        // Fix list.
        LinkedList<Word> list = Dictionary.getWords();
        explain = "<C><F><I><N><Q><big><b id='txt'>"+target+"</b></big><br />-"+explain+"</Q></N></I></F></C>";
        for (Word check : list) {
            if (check.getWord_target().equalsIgnoreCase(target)) {
                check.setWord_explain(explain);
                break;
            } else if (target.compareToIgnoreCase(check.getWord_target()) < 0) {
                return "This word doesn't exist!";
            }
        }

        // Fix database.
        try {
            DictionaryManager.fixWord(target, explain);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return "Fixed successfully!";
    }
}
