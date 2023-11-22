package com.example.demo1.easyquiz;

import com.example.demo1.ExtraReadFile;
import com.example.demo1.ToolsReadFile;
import com.example.demo1.Word;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.*;

public class QuizController {

    @FXML
    public Label question;

    @FXML
    public Button opt1, opt2, opt3, opt4,next;
    static ObservableList<Label> quests = FXCollections.observableArrayList();
    Label saved = new Label();
    @FXML
    Label notifier,notifiertrue;

    static int counter = 0;
    static int correct = 0;
    static int wrong = 0;

    @FXML
    protected void initialize() {
        loadQuestions();
        Setting();
    }
    public static final List<String> ans = Arrays.asList("B","A","D","B","C","A","B","C",
            "A","B","B","B","B","D","D");
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
    List<Integer> index = Arrays.asList(0,1,2,3,4,5,6,7,8,9,10,11,12,13,14);
    {
        Collections.shuffle(index);
    }
    protected void loadQuestions() {
        webEngine.loadContent(finals.get(index.get(counter)));
        question.setGraphic(webView);
        saved.setGraphic(webView);
        quests.add(saved);
        opt1.setText("A");
        opt2.setText("B");
        opt3.setText("C");
        opt4.setText("D");
    }
    public void showResult(ActionEvent event) {
    try {
        System.out.println(correct);
        Stage thisstage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        thisstage.close();
        FXMLLoader quiz = new FXMLLoader(getClass().getResource("result.fxml"));
        Scene quizscene = new Scene(quiz.load());
        quizscene.setFill(Color.TRANSPARENT);
        Stage quizstage = new Stage();
        quizstage.setScene(quizscene);
        quizstage.initStyle(StageStyle.TRANSPARENT);
        quizstage.show();
    } catch(IOException e) {
        e.printStackTrace();
    }
}

    @FXML
    public void opt1clicked(ActionEvent event) {
        opt1.setVisible(false);
        opt2.setVisible(false);
        opt3.setVisible(false);
        opt4.setVisible(false);
        if (checkAnswer(opt1.getText().toString())) {
            correct += 1;
            this.notifiertrue.setOpacity(1.0);
        } else {
            wrong += 1;
            this.notifier.setOpacity(1.0);
        }
    }

    protected boolean checkAnswer(String answer) {
        return answer.equals(ans.get(index.get(counter)));
    }

    @FXML
    public void opt2clicked(ActionEvent event) {
        opt2.setVisible(false);
        opt1.setVisible(false);
        opt3.setVisible(false);
        opt4.setVisible(false);
        if (checkAnswer(opt2.getText())) {
            correct = correct + 1;
            this.notifiertrue.setOpacity(1.0);
        } else {
            wrong = wrong + 1;
            this.notifier.setOpacity(1.0);
        }
    }
    @FXML
    public void nextclicked(ActionEvent event) {
            opt1.setVisible(true);
            opt2.setVisible(true);
            opt3.setVisible(true);
            opt4.setVisible(true);
            if (counter == 9) {
                showResult(event);
                counter = 0;
            } else {
                this.notifier.setOpacity(0.0);
                this.notifiertrue.setOpacity(0.0);
                counter++;
                loadQuestions();
            }
    }


    @FXML
    public void opt3clicked(ActionEvent event) {
        opt3.setVisible(false);
        opt2.setVisible(false);
        opt1.setVisible(false);
        opt4.setVisible(false);
        if (checkAnswer(opt3.getText())) {
            correct = correct + 1;
            this.notifiertrue.setOpacity(1.0);
        } else {
            wrong = wrong + 1;
            this.notifier.setOpacity(1.0);
        }
    }

    @FXML
    public void opt4clicked(ActionEvent event) {
        opt4.setVisible(false);
        opt2.setVisible(false);
        opt3.setVisible(false);
        opt1.setVisible(false);
        if (checkAnswer(opt4.getText())) {
            correct = correct + 1;
            this.notifiertrue.setOpacity(1.0);
        } else {
            wrong = wrong + 1;
            this.notifier.setOpacity(1.0);
        }
    }
    protected void Setting() {
        opt1.setOnMouseEntered(event -> {
            opt1.setStyle("-fx-background-color: #2980b9;"); // Màu khi di chuột vào
        });

        opt1.setOnMouseExited(event -> {
            opt1.setStyle("-fx-background-color: #003366;"); // Màu khi chuột rời khỏi
        });

        opt1.setOnMousePressed(event -> {
            opt1.setStyle("-fx-background-color: #1f618d;"); // Màu khi nút được nhấn giữ
        });

        opt1.setOnMouseReleased(event -> {
            opt1.setStyle("-fx-background-color:  #003366;"); // Màu khi nút được nhả ra
        });
        opt2.setOnMouseEntered(event -> {
            opt2.setStyle("-fx-background-color: #2980b9;"); // Màu khi di chuột vào
        });

        opt2.setOnMouseExited(event -> {
            opt2.setStyle("-fx-background-color:  #003366;"); // Màu khi chuột rời khỏi
        });

        opt2.setOnMousePressed(event -> {
            opt2.setStyle("-fx-background-color: #1f618d;"); // Màu khi nút được nhấn giữ
        });

        opt2.setOnMouseReleased(event -> {
            opt2.setStyle("-fx-background-color:  #003366;"); // Màu khi nút được nhả ra
        });
        opt3.setOnMouseEntered(event -> {
            opt3.setStyle("-fx-background-color: #2980b9;"); // Màu khi di chuột vào
        });

        opt3.setOnMouseExited(event -> {
            opt3.setStyle("-fx-background-color:  #003366;"); // Màu khi chuột rời khỏi
        });

        opt3.setOnMousePressed(event -> {
            opt3.setStyle("-fx-background-color: #1f618d;"); // Màu khi nút được nhấn giữ
        });

        opt3.setOnMouseReleased(event -> {
            opt3.setStyle("-fx-background-color:  #003366;"); // Màu khi nút được nhả ra
        });
        opt4.setOnMouseEntered(event -> {
            opt4.setStyle("-fx-background-color: #2980b9;"); // Màu khi di chuột vào
        });

        opt4.setOnMouseExited(event -> {
            opt4.setStyle("-fx-background-color:  #003366;"); // Màu khi chuột rời khỏi
        });

        opt4.setOnMousePressed(event -> {
            opt4.setStyle("-fx-background-color: #1f618d;"); // Màu khi nút được nhấn giữ
        });

        opt4.setOnMouseReleased(event -> {
            opt4.setStyle("-fx-background-color:  #003366;"); // Màu khi nút được nhả ra
        });
    }

}

