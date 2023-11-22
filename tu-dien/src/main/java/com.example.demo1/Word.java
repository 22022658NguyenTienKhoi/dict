package com.example.demo1;

public class Word {
    private String finding;
    private String meaning;

    public Word(String finding, String meaning) {
        this.finding = finding.trim().toLowerCase();
        this.meaning = meaning.trim().toLowerCase();
    }

    public String getFinding() {
        return finding;
    }

    public void setFinding(String finding) {
        this.finding = finding;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }
}