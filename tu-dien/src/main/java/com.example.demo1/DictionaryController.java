
package com.example.demo1;

import com.example.demo1.easyquiz.QuizApplication;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;


public class DictionaryController {
    ObservableList<String> messages;
    ListView<String> listView = new ListView<>();
    public Scene initialize() throws IOException {
        messages = FXCollections.observableArrayList();
        TextField textField = new TextField();
        Button evButton = new Button("EV");
        Button veButton = new Button("VE");
        Button switchButton = new Button("Test");
        Button addButton = new Button("Add");
        Button tipstolearn = new Button("Tools");
        Button apibutton = new Button("API");
        Button exit = new Button("Exit");

        listView.setCellFactory(list -> new DictCell());
        listView.setItems(messages);

        DictionaryManagement dict = new DictionaryManagement();
        ReadHis rh = new ReadHis();
        String user = Controller.saved;
        veButton.setOnAction(evt -> {
            DictionaryManagement ve = new DictionaryManagement();
                try {
                    ve.insertFromFileVE();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                String search = textField.getText().trim().toLowerCase();
                messages.add(ve.Lookup(search.trim()));
                if(ve.Lookup(search) != "not found") {
                    rh.writeHis(ve.Lookup(search), user);
                }
                //dict.dictionary.eraseAll();
    });
        evButton.setOnAction(event -> {
            DictionaryManagement ev = new DictionaryManagement();
            try {
                ev.insertFromFileEV();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            String search = textField.getText().trim().toLowerCase().replace("2", "");
            messages.add(ev.Lookup(search.trim()));
            if(ev.Lookup(search) != "not found") {
                rh.writeHis(ev.Lookup(search), user);
            }
            //dict.dictionary.eraseAll();
        });
        addButton.setOnAction(event -> {
                String add = textField.getText().trim().toLowerCase();
                Word addition = new Word(add.split(",")[0], add.split(",")[1]);
                dict.writeExtra(add);
                messages.add("đã thêm " + addition.getFinding());
        });

        tipstolearn.setOnAction(event -> {
        if (textField.getText().contains("lời khuyên")) {
            ToolsReadFile tips = new ToolsReadFile();
            List<String> tip;
            try {
                tip = tips.readfile("D:\\demo1\\src\\tips.txt");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Date currentDate = new Date();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(currentDate);

            // Tạo seed từ ngày hiện tại
            long seed = calendar.get(Calendar.DAY_OF_YEAR) + calendar.get(Calendar.YEAR);

            // Sử dụng seed để tạo số ngẫu nhiên
            Random random = new Random(seed);

            // Tạo số ngẫu nhiên trong phạm vi từ 0 đến 99
            int randomNumber = random.nextInt(101);
            messages.add(tip.get(randomNumber));
        } else if (textField.getText().contains("phát âm")) {
            String sound = textField.getText().trim().replace("phát âm", "");
            Services.playSound(sound.trim());
            messages.add("đã phát âm " + sound);
        } else if (textField.getText().contains("lịch sử")) {
            List<String> his;
            try {
                his = rh.readfile(user);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            for (String i : his) {
                messages.add(i.trim());
            }
        }else if(textField.getText().contains("mẩu chuyện")){
            ToolsReadFile stories = new ToolsReadFile();
            List<String> story;
            try {
                 story = stories.readfile("D:\\demo1\\src\\story.txt");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            //Date currentDate = new Date();
            //Calendar calendar = Calendar.getInstance();
            //calendar.setTime(currentDate);

            // Tạo seed từ ngày hiện tại
            //long seed = calendar.get(Calendar.DAY_OF_YEAR) + calendar.get(Calendar.YEAR);

            // Sử dụng seed để tạo số ngẫu nhiên
            //Random random = new Random(seed);
            Random random = new Random();
            // Tạo số ngẫu nhiên trong phạm vi từ 0 đến 99
            int randomNumber = random.nextInt(3);/*random.nextInt(101)*/;
            messages.add(story.get(randomNumber));
        }else if(textField.getText().contains("my dictionary")){
            ToolsReadFile readFile = new ToolsReadFile();
            List<String> mine;
            try {
                mine = readFile.readfile("D:\\demo1\\src\\"+user+".txt");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            for (String i : mine) {
                messages.add(i.trim());
            }
        } else {
            String[] parts;
            List<Word> words = new ArrayList<>();
            String line;
            BufferedReader br;
            try {
                br = new BufferedReader(new FileReader("D:\\demo1\\src\\360.txt"));
                while((line = br.readLine()) != null) {
                    parts = line.split("\\s+");
                    String finding = parts[0];
                    String meaning = parts[1].concat(" " + parts[2]);
                    Word newWord = new Word(finding,meaning);
                    words.add(newWord);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            String search = textField.getText().trim().toLowerCase();
            String ans = "not found";
            System.out.println(words.size());
            for (int i = 0; i < words.size(); i++) {
                if (words.get(i).getFinding().equals(search)) {
                    ans = words.get(i).getMeaning();
                }
            }
            messages.add(ans);
        }
    });
        switchButton.setOnAction(evt -> {
              QuizApplication anotherApp = new QuizApplication();
              Stage stage = new Stage();
              try {
              anotherApp.start(stage);
            } catch (Exception e) {
              e.printStackTrace();
            }
//            Stage thisstage = (Stage) ((Button) evt.getSource()).getScene().getWindow();
//            thisstage.close();
//            FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("C:\\newdemo\\demo1\\src\\main\\resources\\com\\example\\demo1\\easyquiz\\home.fxml"));
//            Scene scene = null;
//            try {
//                scene = new Scene(fxmlLoader.load());
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//            Stage stage = new Stage();
//            stage.setScene(scene);
//            stage.initStyle(StageStyle.TRANSPARENT);
//            scene.setFill(Color.TRANSPARENT);
//            stage.show();
        });
        apibutton.setOnAction(evt -> {
            String search = textField.getText();
            if (search.trim().toLowerCase().contains("2")) {
                String en = search.replace("2", "");
                try {
                    messages.add(Services.googleTranslate("", "vi", en));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } else if (search.trim().toLowerCase().contains("1")) {
                String vi = search.replace("1", "");
                try {
                    messages.add(Services.googleTranslate("", "en", vi));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        exit.setOnAction(evt -> Platform.exit());

        VBox vbox = new VBox(tipstolearn, switchButton, apibutton, exit);
        ToggleButton toggleButton = new ToggleButton("Plus");
        vbox.setVisible(false);
        toggleButton.setOnAction(evt -> vbox.setVisible(toggleButton.isSelected()));
        toggleButton.getStyleClass().add("tool");
        addButton.getStyleClass().add("tool");
        evButton.getStyleClass().add("send");
        veButton.getStyleClass().add("send");
        tipstolearn.getStyleClass().add("tip");
        switchButton.getStyleClass().add("switch");
        apibutton.getStyleClass().add("api");
        exit.getStyleClass().add("exit");
        evButton.setPrefSize(61.0,22.0);
        veButton.setPrefSize(61.0,22.0);
        addButton.setPrefSize(61.0,22.0);
        tipstolearn.setPrefSize(61.0,22.0);
        switchButton.setPrefSize(61.0,22.0);
        apibutton.setPrefSize(61.0,22.0);
        exit.setPrefSize(61.0,22.0);
        toggleButton.setPrefSize(61.0,22.0);
        HBox hbox = new HBox(textField, evButton,veButton,addButton,toggleButton);
        VBox chatLayout = new VBox(listView, hbox, vbox);
        Scene scene = new Scene(chatLayout, 900, 500);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("sample.css")).toExternalForm());
        scene.setFill(Color.TRANSPARENT);
        return scene;
    }

    static class DictCell extends ListCell<String> {
        Text message = new Text();
        WebView webView = new WebView();
        WebEngine webEngine = webView.getEngine();

        StackPane bubble1 = new StackPane(message);
        StackPane bubble2 = new StackPane(webView);
        HBox graphic = new HBox(5, bubble1);
        HBox graphic2 = new HBox(5, bubble2);

        public DictCell() {
            bubble1.getStyleClass().add("incoming-bubble");
            bubble2.getStyleClass().add("ongoing-bubble");
        }

        @Override
        public void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);
            if (!empty && (item != null)) {
                if (item.contains("br")) {
                    String htmlContent = item;
                    webEngine.loadContent(htmlContent);
                    graphic2.setAlignment(Pos.CENTER_LEFT);
                    setGraphic(graphic2);
                } else {
                    message.setText(item);
                    graphic.setAlignment(Pos.CENTER_RIGHT);
                    setGraphic(graphic);
                }
            } else {
                message.setText("");
                setGraphic(null);
            }
        }
    }
}






