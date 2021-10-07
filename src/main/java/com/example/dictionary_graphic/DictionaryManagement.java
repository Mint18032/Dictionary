package com.example.dictionary_graphic;

import java.io.*;
import java.sql.SQLException;
import java.util.LinkedList;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class DictionaryManagement {
    /**
     * Insert Word.
     */
    public static void insertFromCommandline() {
        System.out.println("Type the number of new words:");
        Scanner sc = new Scanner(System.in);
        int numberOfWord = sc.nextInt();
        while (numberOfWord != 0) {
            System.out.println("Type your new word:");
            String target = sc.next();
            System.out.println("Type Vietnamese explanations:");
            String explain = sc.nextLine();
            explain = sc.nextLine();
            Word word = new Word(target, explain);
            Dictionary.addWord(word);
            exportToFile(word);
            System.out.println(target + " is successfully added to dictionary!");
            numberOfWord--;
        }
        sc.close();
    }

    /**
     * Insert from File.
     */
    public static void insertFromFile() {
        String file = "src/main/resources/Dictionary.txt";
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] build = line.split(": ");
                Word word = new Word(build[0], build[1]);
                Dictionary.addWord(word);
            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Lookup Word.
     */
    public static void dictionaryLookup() {
        System.out.println("Type the word you want to look up: ");
        Scanner scan = new Scanner(System.in);
        String w = scan.nextLine();
        w = w.toLowerCase();
        boolean present = false;
        for (Word check : Dictionary.getWords()) {
            if (check.getWord_target().equals(w)) {
                present = true;
                System.out.println(check.getWord_explain());
                break;
            } else if (w.charAt(0) < check.getWord_target().charAt(0)) {
                break;
            }
        }
        if (!present) {
            System.out.println("This word doesn't exist!");
        }
        scan.close();
    }

    /**
     * Remove a word from the Dictionary.
     */
    public static void deleteWord() {
        System.out.println("Type the word that you want to delete:");
        Scanner in = new Scanner(System.in);
        String w = in.next().toLowerCase();
        try {
            DictionaryManager.deleteWord(w);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Fix Word.
     */
    public static void fixWord() {
        System.out.println("Type the word that you want to fix:");
        Scanner in = new Scanner(System.in);
        String w = in.next().toLowerCase();
        String wexplain = in.next();
        try {
            DictionaryManager.fixWord(w, wexplain);
        } catch (SQLException e) {
            e.printStackTrace();
        }

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
