package com.example.demo1.easyquiz;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class ResultController {

    @FXML
    public Label remark, marks, markstext, correcttext, wrongtext;
    @FXML
    public Button back, exit;

    @FXML
    public ProgressIndicator correct_progress, wrong_progress;

    int correct;
    int wrong;

    @FXML
    private void initialize() {
        correct = QuizController.correct;
        QuizController.correct = 0;
        wrong = QuizController.wrong;
        QuizController.wrong = 0;
        correcttext.setText("Correct Answers : " + correct);
        wrongtext.setText("Incorrect Answers : " + wrong);

        marks.setText(correct + "/10");
        float correctf = (float) correct/10;
        correct_progress.setProgress(correctf);

        float wrongf = (float) wrong/10;
        wrong_progress.setProgress(wrongf);


        markstext.setText(correct + " Marks Scored");

        if (correct<2) {
            remark.setText("What a pity! Practice daily and you will get better score");
        } else if (correct<5) {
            remark.setText("You almost made it,try a little bit harder");
        } else if (correct<=7) {
            remark.setText("Good! No pain, no gain. Practice is the key to success.");
        } else if (correct==8 || correct==9) {
            remark.setText("Congratulations! Its your hardwork and determination which helped you to go this far.");
        } else if (correct==10) {
            remark.setText("Congratulations! Be confident! You have conquered the test!");
        }
        back.setOnAction(evt -> {
            Stage thisstage = (Stage) ((Button) evt.getSource()).getScene().getWindow();
            thisstage.close();
            FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("home.fxml"));
            Scene scene = null;
            try {
                scene = new Scene(fxmlLoader.load());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.TRANSPARENT);
            scene.setFill(Color.TRANSPARENT);
            stage.show();
        });
        exit.setOnAction(evt ->{
            Stage thisstage = (Stage) ((Button) evt.getSource()).getScene().getWindow();
            thisstage.close();
        });

    }

}
