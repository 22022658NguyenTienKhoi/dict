package com.example.demo1;

import java.util.ArrayList;
import java.util.List;

public class Dictionary {
    private List<Word> words = new ArrayList<>();

    public void setWords(List<Word> words) {
        this.words = words;
    }

    public List<Word> getWords() {
        return words;
    }
    public void pushWord(Word word){
        words.add(word);
    }
    public void eraseAll(){
        words.removeAll(words);
    }
    public void eraseWord(String finding) {
        for(Word word:words) {
            if(word.getFinding().equalsIgnoreCase(finding)){
                words.remove(word);
            }
        }
    }
}