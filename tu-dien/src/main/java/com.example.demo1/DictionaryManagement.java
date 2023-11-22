package com.example.demo1;


import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DictionaryManagement {
    public Dictionary dictionary = new Dictionary();
    public void insertFromFileNormal() throws IOException{
        List<Word> words = new ArrayList<>();
        String[] parts;
        String line = null;
        BufferedReader br = new BufferedReader(new FileReader("D:\\dict\\newdemo\\demo1\\src\\dict.txt"));
        while((line = br.readLine()) != null) {
            parts = line.split("\t");
            String finding = parts[0];
            String meaning = parts[1];
            Word newWord = new Word(finding,meaning);
            words.add(newWord);
        }
        for(Word word : words){
        dictionary.pushWord(word);
    }
    }
    public void insertFromFileVE() throws IOException{
        List<Word> words = new ArrayList<>();
        ReadFile readFile = new ReadFile();
        words = readFile.readfile("D:\\dict\\newdemo\\demo1\\src\\V_E.txt");
        for(Word word : words){
            dictionary.pushWord(word);
        }
        System.out.println("push ve");
    }

    public void insertFromFileEV() throws IOException{
        List<Word> words = new ArrayList<>();
        ReadFile readFile = new ReadFile();
        words = readFile.readfile("D:\\dict\\newdemo\\demo1\\src\\E_V.txt");
        for(Word word : words){
            dictionary.pushWord(word);
        }
        System.out.println("push ev");
    }
    public void insertFromFileExtra() throws IOException{
        List<Word> words = new ArrayList<>();
        ExtraReadFile readFile = new ExtraReadFile();
        words = readFile.readfile();
        for(Word word : words){
            dictionary.pushWord(word);
        }
        System.out.println("push extra");
    }
    public void writeExtra(String content) {
        String saved = Controller.saved;
        String filePath = "D:\\dict\\newdemo\\demo1\\src\\"+saved+".txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath,true))) {
            // Ghi nội dung vào tệp tin
            writer.write(content+System.lineSeparator());
            System.out.println("Text written to file successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Tim kiem tu trong tu dien.
     */

    public String Lookup(String string) {
        List<Word> words = new ArrayList<>();
        words = dictionary.getWords();
        String ans = "not found";
        System.out.println(words.size());
        for (int i = 0; i < words.size(); i++) {
            if (words.get(i).getFinding().equals(string)) {
                 ans = words.get(i).getMeaning();
            }
        }
        System.out.printf(ans);
        return ans;
    }
    public String Lookdown() {
        List<Word> words = new ArrayList<>();
        System.out.println("Nhập từ bạn muốn tìm kiếm:");
        Scanner scanner = new Scanner(System.in);
        String string = scanner.nextLine();
        words = dictionary.getWords();
        String ans = "not found";
        System.out.println(words.size());
            for (int i = 0; i < words.size(); i++) {
                if (string.equalsIgnoreCase(words.get(i).getFinding())) {
                    ans = words.get(i).getMeaning();
                    System.out.printf("%-3s | %-9s | %-9s%n", i, words.get(i).getFinding(), words.get(i).getMeaning());
                } else if (string.equalsIgnoreCase(words.get(i).getMeaning())) {
                    ans = words.get(i).getFinding();
                    System.out.printf("%-3s | %-9s | %-9s%n", i, words.get(i).getFinding(), words.get(i).getMeaning());
                }
            }
        System.out.printf(ans);
        return ans;
    }
    public Word binaryLookup(int start, int end,String finding) {
        List<Word> words = new ArrayList<>();
        words = dictionary.getWords();
        if (end < start) return null;
        int mid = (start + end) / 2;
        Word word = words.get(mid);
        String currentSpelling = word.getFinding();
        int compare = currentSpelling.compareTo(finding);
        if (compare == 0) return word;
        if (compare > 0) return binaryLookup(start, mid - 1,finding);
        return binaryLookup(mid + 1, end,finding);
    }
    public void addDictionary(Word word) {
        dictionary.pushWord(word);
    }

    public Dictionary getDictionary() {
        return dictionary;
    }

    public void setDictionary(Dictionary dictionary) {
        this.dictionary = dictionary;
    }
}

