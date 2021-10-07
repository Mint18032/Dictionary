package com.example.dictionary_graphic;
import java.util.LinkedList;

public class Dictionary {
    private static LinkedList<Word> words = new LinkedList<>();

    public static LinkedList<Word> getWords() {
        return words;
    }

    /**
     * Compares 2 words.
     * @return false if "after" should not be placed after "before".
     */
    public static boolean rightPlace(Word before, Word after) {
        int edge = Math.min(before.getWord_target().length(), after.getWord_target().length());
        for (int i = 0; i < edge; i++) {
            if (after.getWord_target().charAt(i) < before.getWord_target().charAt(i)) {
                if (i == 0 || after.getWord_target().charAt(i - 1) == before.getWord_target().charAt(i - 1))
                    return false;
            }
        }
        return true;
    }

    /**
     * Adds a new word to Dictionary and brings ít to the relevant index.
     * @param newWord to be added.
     */
    public static void addWord(Word newWord) {
        words.add(newWord);

        for (int i = words.size() - 1; i > 0; --i) {
            if (!rightPlace(words.get(i - 1), words.get(i))) {
                Word temp = words.get(i);
                words.set(i, words.get(i-1));
                words.set(i-1, temp);
            } else {
                break;
            }
        }
    }
    public static void addWordFromDb(Word newWord) {
        words.add(newWord);
    }
    public static void setWords(LinkedList<Word> w) {
        words = w;
    }
}
