package com.example.demo1;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ReadHis {
    public List<String> readfile() throws IOException {
        List<String> word = new ArrayList<>();
        String line = null;
        BufferedReader br = new BufferedReader(new FileReader("D:\\demo1\\src\\main\\History.txt"));
        while((line = br.readLine()) != null) {
            word.add(line);
        }
        return word;
    }
    public void writeHis(String content) {
        String filePath = "D:\\demo1\\src\\main\\History.txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath,true))) {
            // Ghi nội dung vào tệp tin
            writer.write(content);
            System.out.println("Text written to file successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
