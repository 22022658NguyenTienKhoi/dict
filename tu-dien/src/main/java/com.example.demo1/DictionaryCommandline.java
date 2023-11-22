package com.example.demo1;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class DictionaryCommandline {
    public final DictionaryManagement dictionaryManagement = new DictionaryManagement();

    // Display the dictionary
    public void showAllWords() {
        List<Word> dictionary = dictionaryManagement.getDictionary().getWords();
        System.out.println("\tTừ điển");
        System.out.printf("%-3s | %-9s | %-9s%n", "No", "English", "Vietnamese");
        for (int i = 0; i < dictionary.size(); i++) {
            Word word = dictionary.get(i);
            System.out.printf("%-3s | %-9s | %-9s%n", i + 1, word.getFinding(), word.getMeaning());
        }
    }

    // Search and display words with suggestions
    public void dictionarySearcher() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhập từ muốn tìm kiếm: ");
        String word_target = scanner.nextLine().toLowerCase();

        List<Word> dictionary = dictionaryManagement.getDictionary().getWords();
        boolean check = false;

        for (Word word : dictionary) {
            String wordSearcher = word.getFinding().toLowerCase();
            if (wordSearcher.startsWith(word_target)) {
                System.out.printf("| %-9s | %-9s%n", word.getFinding(), word.getMeaning());
                check = true;
            }
        }

        if (!check) {
            System.out.println("Không có từ nào bắt đầu bằng '" + word_target + "' trong từ điển");
        }
    }

    // Export dictionary to a file
    public void dictionaryExportToFile() throws IOException {
        Scanner sc = new Scanner(System.in);

        System.out.println("Đường dẫn mặc định: src/Dictionary.txt.");
        System.out.println("Bạn có muốn đổi đường dẫn không? (Y/N)");
        String ans = sc.next();

        File fileTxt = new File(ans.equalsIgnoreCase("y") ? sc.next() : "Dictionary.txt");

        BufferedWriter bw = new BufferedWriter(new FileWriter(fileTxt));

        List<Word> words = dictionaryManagement.getDictionary().getWords();

        for (int i = 0; i < words.size(); i++) {
            String temp = String.format("| %-9s | %-9s", words.get(i).getFinding(), words.get(i).getMeaning());
            bw.write(temp + "\n");
        }

        bw.close();
        sc.close();
    }

    // Add a new word to the dictionary without checking for duplicates
    public void addDictionary() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhập từ muốn thêm: ");
        String finding = scanner.nextLine();
        System.out.print("Nhập nghĩa: ");
        String meaning = scanner.nextLine();

        Word word = new Word(finding, meaning);
        dictionaryManagement.getDictionary().pushWord(word);

        System.out.println("Đã thêm " + finding);
    }

    // Edit the meaning of an existing word in the dictionary
    public void editDictionary() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Từ cần sửa: ");
        String finding = scanner.nextLine().toLowerCase();

        List<Word> dictionary = dictionaryManagement.getDictionary().getWords();
        boolean check = false;

        for (int i = 0; i < dictionary.size(); i++) {
            Word word = dictionary.get(i);
            if (word.getFinding().equalsIgnoreCase(finding)) {
                System.out.print("New meaning: ");
                String word_explain = scanner.nextLine();
                word.setMeaning(word_explain);
                check = true;
                System.out.println("Đã sửa " + finding);
                break;
            }
        }

        if (!check) {
            System.out.println("Không có từ " + finding + " trong từ điển");
            System.out.println("Thêm " + finding + " ? (Y/N)");
            String answer = scanner.nextLine();
            if (answer.equalsIgnoreCase("Y")) {
                System.out.print("Tiếng Việt: ");
                String meaning = scanner.nextLine();
                Word word = new Word(finding,meaning);
                dictionaryManagement.getDictionary().pushWord(word);
                System.out.println("Đã thêm từ " + finding);
            }
        }
    }
    //game
    public void engine() throws IOException {
        dictionaryManagement.insertFromFileNormal();
        List<Word> words = dictionaryManagement.dictionary.getWords();
        Random random = new Random();
        Collections.shuffle(words);
        for(int t=0;t< 10;t++) {
            System.out.println(words.get(t).getMeaning()+"\n");
        }
        int point = 0;
        for(int i = 0; i < 10;i++) {
            Scanner scanner = new Scanner(System.in);
            String temp = scanner.nextLine();
            if(temp.equalsIgnoreCase(words.get(i).getFinding())) {
                point++;
            }
        }
        System.out.println(point+"/10");
    }
}

