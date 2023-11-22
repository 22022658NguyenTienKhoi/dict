package com.example.demo1.easyquiz;

import com.example.demo1.ToolsReadFile;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Quiz4Controller extends QuizController{
    public static final List<String> ans = Arrays.asList("B","A","D","C","B","A","A","A","B","D","D","A",
            "C","A","A","A","D","C","D","D","A","B","A","B","A","C","C","A","B","A");
    WebView webView = new WebView();
    WebEngine webEngine = webView.getEngine();

    ToolsReadFile readFile = new ToolsReadFile();

    List<String> finals;
    {
        try {
            finals = readFile.readfile("D:\\demo1\\src\\Test.txt");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    List<Integer> index = Arrays.asList(58,59,60,61,62,63,64,65,66,67,68,69,70,71,72,
            73,74,75,76,77,78,79,80,81,82,83,83,85,86,87);
    {
        Collections.shuffle(index);
    }
    @Override
    protected void loadQuestions() {
        webEngine.loadContent(finals.get(index.get(counter)));
        question.setGraphic(webView);
        opt1.setText("A");
        opt2.setText("B");
        opt3.setText("C");
        opt4.setText("D");
    }

    @Override
    protected boolean checkAnswer(String answer) {
        return answer.equals(ans.get(index.get(counter) -58));
    }

}
