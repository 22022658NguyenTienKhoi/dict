package com.example.demo1;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ExtraReadFile extends ReadFile{
    public List<Word> readfile() throws IOException {
        String[] parts;
        List<Word> words = new ArrayList<>();
        String line = null;
        BufferedReader br = new BufferedReader(new FileReader("phrasalverb.txt"));
        while((line = br.readLine()) != null) {
            parts = line.split(":");
            String finding = parts[0];
            String meaning = ":" + parts[1];
            Word newWord = new Word(finding,meaning);
            words.add(newWord);
        }
        BufferedReader br1 = new BufferedReader(new FileReader("collocation.txt"));
        while((line = br1.readLine()) != null) {
            parts = line.split(":");
            String finding = parts[0];
            String meaning = ":" + parts[1];
            Word newWord = new Word(finding,meaning);
            words.add(newWord);
        }
        return words;
    }

}
