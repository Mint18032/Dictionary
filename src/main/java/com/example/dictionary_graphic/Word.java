package com.example.dictionary_graphic;

/**
 * Represents a word with its definitions.
 */
public class Word {
    //Variable
    private String word_target;
    private String word_explain;

    /**
     * Setters and Getters.
     */
    public String getWord_explain() {
        return this.word_explain;
    }

    public void setWord_explain(String word_explain) {
        this.word_explain = word_explain;
    }

    public String getWord_target() {
        return this.word_target;
    }

    public void setWord_target(String word_target) {
        this.word_target = word_target;
    }

    /**
     * Non-args Constructor for Word.
     */
    public Word() {
    }

    /**
     * Word Constructor.
     */
    public Word(String word_target, String word_explain) {
        setWord_target(word_target);
        setWord_explain(word_explain);
    }

    /**
     * Compares 2 words.
     * @return false if "after" should not be placed after "before".
     */
    public boolean isBefore(Word other) {
        if (word_target.compareTo(other.getWord_target()) <= 0) {
            return true;
        }
        return false;
    }
}
