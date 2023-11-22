package com.example.demo1;

import com.example.demo1.User;
import com.example.demo1.DictionaryController;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Controller {
    @FXML
    public Pane pnSignIn;
    @FXML
    public TextField userID;
    @FXML
    public Label loginNotifier;
    public static String userid;

    User no1 = new User("22022658");
    User no2 = new User("22026165");
    User no3 = new User("22022538");
    public List<User> users = new ArrayList<>();
    public static String saved;

    public Controller() {
    }
    public void login() {
        boolean login = false;
        users.add(no1);
        users.add(no2);
        users.add(no3);
        userid = userID.getText();
        for(User x : users) {
            if (x.id.equalsIgnoreCase(userid)) {
                login = true;
                saved = x.id;
                System.out.println(x.id);
                break;
            }
        }

        if (login) {
            DictionaryController anotherApp = new DictionaryController();
            Stage stage = new Stage();
            Stage thisstage = (Stage)this.userID.getScene().getWindow();
            thisstage.close();
            Scene scene = null;
            try {
                scene = anotherApp.initialize();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            stage.setScene(scene);
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.show();
        } else {
            this.loginNotifier.setOpacity(1.0);
        }
    }
}

