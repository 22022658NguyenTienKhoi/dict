package com.example.demo1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadFile {
    public List<Word> readfile(String path) throws IOException {
        String[] parts;
        List<Word> words = new ArrayList<>();
        String line = null;
        BufferedReader br = new BufferedReader(new FileReader(path));
        while((line = br.readLine()) != null) {
            parts = line.split("<html>");
            String finding = parts[0];
            String meaning = "<html>"+parts[1];
            Word newWord = new Word(finding,meaning);
            words.add(newWord);
        }
        return words;
    }
}