package com.example.demo1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ToolsReadFile {
    public List<String> readfile(String filepath) throws IOException {
        List<String> ans = new ArrayList<>();
        String line ;
        BufferedReader br = new BufferedReader(new FileReader(filepath));
        while((line = br.readLine()) != null) {
            ans.add(line);
        }
        return ans;
    }
}
