package com.example.demo1.easyquiz;

import com.example.demo1.ToolsReadFile;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.io.IOException;
import java.util.*;

public class Quiz2Controller extends QuizController{
    public static final List<String> ans = Arrays.asList("D","B","D","C","D","B","B","B", "A", "B", "B", "A", "C", "D", "C", "C", "C", "A",
            "C", "A", "A", "D", "C", "C", "B", "A", "A", "D", "C", "D");
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
    List<Integer> index = Arrays.asList(16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,
            31,32,33,34,35,36,37,38,39,40,41,42,43,44,45);
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
        return answer.equals(ans.get(index.get(counter)-16));
    }

}
