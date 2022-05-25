package com.example.vocabulary.Entity;

public class Word {
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Word(int id) {
        this.id = id;
    }

    private String word;
    private String translation;

    public Word(String word, String translation) {
        this.word = word;
        this.translation = translation;
    }

    public Word() {
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }
}
