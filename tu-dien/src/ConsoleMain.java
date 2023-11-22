package com.example.demo1;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class ConsoleMain {

    public static void main(String[] args) throws IOException {

        DictionaryCommandline dictionaryCommandline = new DictionaryCommandline();
        dictionaryCommandline.dictionaryManagement.insertFromFileNormal();
        int choices;
        System.out.println("TỪ ĐIỂN");
        System.out.println("1: THÊM TỪ");
        System.out.println("2: SỬA TỪ TRONG TỪ ĐIỂN");
        System.out.println("3: TÌM KIẾM TỪ TRONG TỪ ĐIỂN");
        System.out.println("4: TÌM KIẾM TỪ CÁC TỪ GỢI Ý TRONG TỪ ĐIỂN");
        System.out.println("5: HIỂN THỊ TỪ ĐIỂN");
        System.out.println("6: XUẤT DỮ LIỆU TỪ ĐIỂN RA FILE");
        System.out.println("7: STUPID GAME");
        System.out.println("8: THOÁT CHƯƠNG TRÌNH");
        System.out.println("YOUR CHOICE:");
        do {
            Scanner sc = new Scanner(System.in);
            while (true) {
                try {
                    choices = sc.nextInt();
                    break;
                } catch (java.util.InputMismatchException e) {
                    System.out.println("Nhập số từ 1->8 ngu ạ");
                    sc.nextLine();
                }
            }
            switch (choices) {
                case 1: {
                    dictionaryCommandline.addDictionary();
                    break;
                }
                case 2: {
                    dictionaryCommandline.editDictionary();
                    break;
                }
                case 3: {
                    dictionaryCommandline.dictionaryManagement.Lookdown();
                    break;
                }
                case 4: {
                    dictionaryCommandline.dictionarySearcher();
                    break;
                }
                case 5: {
                    dictionaryCommandline.showAllWords();
                    break;
                }
                case 6: {
                    dictionaryCommandline.dictionaryExportToFile();
                    break;
                }
                case 7: {
                    dictionaryCommandline.engine();
                    break;
                }
                case 8: {
                    System.out.println("----- Exit program -----");
                    System.exit(0);
                }
                default: {
                    System.out.println("\n[Bạn đã nhập sai lựa chọn]");
                }
            }
        } while (true);
    }
}

