package com.example.demo1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Game {
    public List<Word> engineread() throws IOException {
        ExtraReadFile readFile = new ExtraReadFile();
        List<Word> words = readFile.readfile();
        return words;
    }
    public void engine() throws IOException {
        List<Word> words = this.engineread();
        Random random = new Random();
        int range_down = random.nextInt(15)+1;
        for(int t=0;t< range_down;t++) {
            System.out.println(words.get(t).getMeaning()+"\n");
        }
        int point = 0;
        for(int i = 0; i < range_down;i++) {
        Scanner scanner = new Scanner(System.in);
        String temp = scanner.nextLine();
        if(temp.equalsIgnoreCase(words.get(i).getFinding())) {
                point++;
            }
        }
        System.out.println(point);
    }
}

