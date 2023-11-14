package com.example.demo1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TipsReadFile  {
    public List<String> readfile() throws IOException {
        List<String> tips = new ArrayList<>();
        String line ;
        BufferedReader br = new BufferedReader(new FileReader("D:\\demo1\\src\\tips.txt"));
        while((line = br.readLine()) != null) {
            tips.add(line);
        }
        return tips;
    }
}
