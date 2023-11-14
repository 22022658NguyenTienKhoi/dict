package com.example.demo1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DictionaryCommandline {
    DictionaryManagement dictionaryManagement = new DictionaryManagement();

    /**
     * hien thi tu dien.
     */
    public void showAllWords() {
        List<Word> dictionary = dictionaryManagement.getDictionary().getWords();
        System.out.println("\tTừ điển");
        System.out.printf("%-3s | %-20s | %-20s%n", "No", "English", "Vietnamese");
        for (int i = 1; i <= dictionary.size(); i++) {
            System.out.printf("%-3s | %-20s | %-20s%n", i, dictionary.get(i - 1).getFinding(), dictionary.get(i - 1).getMeaning());
        }
    }


    public void dictionaryAdvanced() throws IOException {
        dictionaryManagement.insertFromFileVE();
        showAllWords();
        dictionaryManagement.dictionaryLookup();
    }

    /**
     * tim kiem va hien thi tu goi y.
     */
    public void dictionarySeacher() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhập từ muốn tìm kiếm: ");
        String word_target = scanner.nextLine();
        List<Word> Dictionary = dictionaryManagement.dictionary.getWords();
        boolean check = false;
        int wordLength = word_target.length();
        int j = 1;
        for (Word word : Dictionary) {
            String wordSearcher = word.getFinding().substring(0, wordLength);
            if (wordSearcher.equalsIgnoreCase(word_target)) {
                System.out.printf("%-3s | %-20s | %-20s%n", j++, word.getFinding(), word.getMeaning());
                check = true;
            }
        }
        if ((!check)) {
            System.out.println("Không có từ nào bắt đầu bằng '" + word_target + "' trong từ điển");
        }
    }

}
