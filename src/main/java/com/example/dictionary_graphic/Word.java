package com.example.dictionary_graphic;


/**
 * Represent a word with its definitions.
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
}
