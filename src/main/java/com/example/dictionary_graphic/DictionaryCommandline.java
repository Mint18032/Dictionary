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

    /**
     * Executes all commandline dictionary functions. Calls insertFromCommandline() and showAllWords().
     */
    public static void dictionaryBasic(String instruction) {
        if (instruction.equals("insert")) {
            DictionaryManagement.insertFromCommandline();
        } else if (instruction.equals("show")) {
            showAllWords();
        }
    }

    /**
     * Executes all commandline dictionary functions.
     * Calls insertFromFile(), insertFromCommandline(), showAllWords() and dictionaryLookup().
     */
    public static void dictionaryAdvanced(String instruction) {
        switch (instruction.toLowerCase()) {
            case "insert":
                DictionaryManagement.insertFromCommandline();
                break;
            case "file":
                DictionaryManagement.insertFromFile();
                break;
            case "show":
                showAllWords();
                break;
            case "delete":
                DictionaryManagement.deleteWord();
                break;
            case "search":
                dictionarySearcher();
                break;
            case "fix":
                DictionaryManagement.fixWord();
                break;
            default:
                DictionaryManagement.dictionaryLookup();
                break;
        }
    }
}
