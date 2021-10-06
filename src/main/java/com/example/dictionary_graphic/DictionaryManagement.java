package com.example.dictionary_graphic;

import java.io.*;
import java.util.ArrayList;
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
        String file = "src/Dictionary.txt";
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
//        w = w.toLowerCase();
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
        ArrayList<Word> list = Dictionary.getWords();
        boolean present = false;
        for (Word check : list) {
            if (check.getWord_target().equals(w)) {
                present = true;
                list.remove(check);
                break;
            } else if (w.charAt(0) < check.getWord_target().charAt(0)) {
                break;
            }
        }
        if (!present) {
            System.out.println("This word doesn't exist or is already deleted!");
        } else {
            Dictionary.setWords(list);
            System.out.println("The word is successfully deleted!");
        }
        in.close();
    }

    /**
     * Fix Word.
     */
    public static void fixWord() {
        System.out.println("Type the word that you want to fix:");
        Scanner in = new Scanner(System.in);
        String w = in.next().toLowerCase();
        String wexplain = in.next();
        ArrayList<Word> list = Dictionary.getWords();
        boolean present = false;
        for (Word check : list) {
            if (check.getWord_target().equals(w)) {
                present = true;
                check.setWord_explain(wexplain);
                break;
            } else if (w.charAt(0) < check.getWord_target().charAt(0)) {
                break;
            }
        }
        if (!present) {
            System.out.println("This word doesn't exist!");
        } else {
            Dictionary.setWords(list);
            System.out.println("The word is successfully fixed!");
        }
        in.close();
    }

    /**
     * Export to file.
     */
    public static void exportToFile(Word word) {
        String file = "src/Dictionary.txt";
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file , true));
            writer.write("\n" + word.getWord_target() + ": " + word.getWord_explain());
            writer.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
