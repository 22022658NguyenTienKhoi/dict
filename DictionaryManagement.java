package com.example.demo1;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.io.BufferedWriter;
import java.io.FileWriter;
public class DictionaryManagement {
    public Dictionary dictionary = new Dictionary();
    public void insertFromFileVE() throws IOException{
        ReadFile readFile = new ReadFile();
        List<Word> words = readFile.readfile();
        for(Word word : words){
            dictionary.pushWord(word);
        }
        System.out.println("Đẩy dữ liệu từ điển việt anh");
    }

    public void insertFromFileEV() throws IOException{
        EVReadFile readFile = new EVReadFile();
        List<Word> words = readFile.readfile();
        for(Word word : words){
            dictionary.pushWord(word);
        }
        System.out.println("Push e-v dictionary data");
    }
    public void insertFromFileExtra() throws IOException{
        ExtraReadFile readFile = new ExtraReadFile();
        List<Word> words = readFile.readfile();
        for(Word word : words){
            dictionary.pushWord(word);
        }
        System.out.println("Push extra dictionary data");
    }
    /**
     * Tim kiem tu trong tu dien.
     */
    public void dictionaryLookup() {
        System.out.println("Nhập từ bạn muốn tìm kiếm:");
        Scanner scanner = new Scanner(System.in);
        String temp = scanner.nextLine();
        temp =temp.trim().toLowerCase();
        List<Word> word1 = dictionary.getWords();
        System.out.println(word1.size());
        int y_n = 0;
        for (int i = 0; i < word1.size(); i++) {
            if (word1.get(i).getFinding().equals(temp)) {
                System.out.printf("%-3s | %-20s | %-20s%n", i, word1.get(i).getFinding(), word1.get(i).getMeaning());
            } else y_n++;
        }
        if(y_n == word1.size()) {
            System.out.println("Không tìm thấy từ bạn nhập!" + temp);
        }

    }

    public String Lookup(String string) {
        List<Word> word1 = dictionary.getWords();
        String ans = "not found";
        System.out.println(word1.size());
        for (int i = 0; i < word1.size(); i++) {
            if (word1.get(i).getFinding().equals(string)) {
                System.out.printf("%-3s  %-20s  %-20s%n", i, word1.get(i).getMeaning(), word1.get(i).getMeaning());
                 ans = word1.get(i).getMeaning();
            }
        }
        return ans;
    }
    public String Lookdown(String string) {
        List<Word> word1 = dictionary.getWords();
        String ans = new String();
        String[] words = string.split("\\s+");
        System.out.println(word1.size());
        for(String word : words) {
            for (int i = 0; i < word1.size(); i++) {
                if (word.equalsIgnoreCase(word1.get(i).getFinding())) {
                    ans = word1.get(i).getMeaning();
                }
            }
        }
        return ans;
    }
    /**
     * Xuat du lieu tu dien ra file.
     */
    public void dictionaryExportToFile() throws IOException{
        File fileTxT = new File("Dictionary.txt");
        System.out.println("Đường dẫn mặc định: src/Dictionary.txt.");
        System.out.println("Bạn có muốn đổi đường dẫn không? (Y/N)");
        Scanner sc = new Scanner(System.in);
        String Ans = sc.next();
        if (Ans.equalsIgnoreCase("y")) {
            System.out.print("Nhập đường dẫn:...");
            fileTxT = new File(sc.next());
            sc.close();
        } else if (Ans.equalsIgnoreCase("n")) {
            fileTxT = new File("Dictionary.txt");
        } else {
            System.out.println("Yêu cầu không hợp lệ!");
            return;
        }

        BufferedWriter bw = new BufferedWriter(new FileWriter(fileTxT));
        List<Word> word1 = dictionary.getWords();
        for (int i = 0; i < word1.size(); i++) {
            String temp = String.format("%-3s | %-20s | %-20s", i + 1, word1.get(i).getFinding(), word1.get(i).getMeaning());
            bw.write(temp + "\n");
        }
        bw.close();
    }


    /**
     * Them 1 tu moi vao tu dien co kiem tra.
     */
    public void addDictionary() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhập từ muốn thêm: ");
        System.out.print("Tiếng Anh: ");
        String word_target = scanner.nextLine();
        System.out.print("Tiếng Việt: ");
        String word_explain = scanner.nextLine();
        Word word = new Word(word_target, word_explain);

        List<Word> Dictionary = dictionary.getWords();
        boolean check = false;
        for (Word value : Dictionary) {
            if (value.getFinding().equalsIgnoreCase(word_target)) {
                check = true;
                break;
            }
        }
        if (!check) {
            dictionary.pushWord(word);
            System.out.println("Đã thêm từ '" + word_target + "' vào từ điển!");
        }
    }

    /**
     * them luon tu moi.
     */
    public void addDictionary(Word word) {
        dictionary.pushWord(word);
    }

    /**
     * sua 1 tu trong tu dien.
     */
    public void editDictionary() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhập từ cần sửa: ");
        String word_target = scanner.nextLine();

        List<Word> Dictionary = dictionary.getWords();
        boolean check = false;
        for (int i = 0; i < Dictionary.size(); i++) {
            if (Dictionary.get(i).getFinding().equalsIgnoreCase(word_target)) {
                System.out.print("Nhập nghĩa mới: ");
                String word_explain = scanner.nextLine();
                dictionary.getWords().get(i).setMeaning(word_explain);
                check = true;
                System.out.println("Đã sửa từ '" + word_target + "'");
            }
        }
        if (!check) {
            System.out.println("Không có từ '" + word_target + "' trong từ điển");
            System.out.println("Bạn có muốn thêm từ '" + word_target + "' vào từ điển không? (Y/N)");
            String answer = scanner.nextLine();
            if (answer.equalsIgnoreCase("Y")) {
                System.out.print("Tiếng Việt: ");
                String word_explain = scanner.nextLine();
                Word word = new Word(word_target, word_explain);
                dictionary.pushWord(word);
                System.out.println("Đã thêm từ '" + word_target + "' vào từ điển");
            }
        }
    }

    /**
     * Xoa 1 tu trong tu dien co kiem tra.
     */
    public void removeDictionary() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhập từ muốn xoá: ");
        String word_target = scanner.nextLine();

        List<Word> Dictionary = dictionary.getWords();
        boolean check = false;
        for (int i = 0; i < Dictionary.size(); i++) {
            if (Dictionary.get(i).getFinding().equalsIgnoreCase(word_target)) {
                dictionary.getWords().remove(i);
                System.out.println("Đã xoá từ '" + word_target + "'");
                check = true;
            }
        }
        if ((!check)) {
            System.out.println("Không có từ '" + word_target + "' trong từ điển");
        }
    }

    /**
     * Xoa la xoa luon.
     */
    public void removeDictionary(String target) {
        dictionary.eraseWord(target);
    }

    /**
     * xoa tat ca tu dien.
     */
    public void removeAllDictionary() {
        dictionary.eraseAll();
    }

    public Dictionary getDictionary() {
        return dictionary;
    }

    public void setDictionary(Dictionary dictionary) {
        this.dictionary = dictionary;
    }
    public List<String> search_list_view = new ArrayList<>();


}

