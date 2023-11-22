package com.example.demo1;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ReadHis {
    public List<String> readfile(String user) throws IOException {
        List<String> word = new ArrayList<>();
        String line = null;
        BufferedReader br = new BufferedReader(new FileReader("D:\\demo1\\src\\main\\"+user+"History.txt"));
        while((line = br.readLine()) != null) {
            word.add(line);
        }
        return word;
    }
    public void writeHis(String content,String user) {
        String filePath = "D:\\demo1\\src\\main\\"+user+"History.txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath,true))) {
            // Ghi nội dung vào tệp tin
            writer.write(content+ System.lineSeparator());
            System.out.println("Text written to file successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
