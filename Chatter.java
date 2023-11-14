package com.example.demo1;

import easyquiz.QuizApplication;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.scene.text.*;
import javafx.stage.StageStyle;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Chatter extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    ObservableList<ChatEntry> messages;
    @Override
    public void start(Stage primaryStage) {
        messages = FXCollections.observableArrayList();
        TextField textField = new TextField();
        Button localButton = new Button("Send");
        Button remoteButton = new Button("See");
        Button switchButton = new Button("Test");
        Button tipstolearn = new Button("Tip");
        Button exit = new Button("Exit");
        ListView<ChatEntry> listView = new ListView<>();
        listView.setCellFactory(list -> new ChatCell());
        listView.setItems(messages);

        localButton.setOnAction(evt->messages.add(new ChatEntry(textField.getText(), MessageType.LOCAL)));

        DictionaryManagement dict = new DictionaryManagement();
        ReadHis rh = new ReadHis();
        remoteButton.setOnAction(evt -> {
            if(textField.getText().contains("chào")||
                    textField.getText().contains("xin chào")||
                    textField.getText().contains("ơi")||
                    textField.getText().contains("này")) {
                messages.add(new ChatEntry("chào bạn, tôi là chat bot dịch từ." +"\n"+ "tôi giúp gì được cho bạn",MessageType.REMOTE));
            } else if ((textField.getText().trim().toLowerCase().contains("nghĩa")||textField.getText().trim().toLowerCase().contains("dịch"))
                    && textField.getText().trim().toLowerCase().contains("anh")){
                try {
                    dict.insertFromFileEV();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                String search = textField.getText().trim().toLowerCase().replace("sang","");
                //messages.add(new ChatEntry(dict.Lookup(search.trim()),MessageType.REMOTE));
                messages.add(new ChatEntry(dict.Lookdown(search),MessageType.REMOTE ));
                rh.writeHis(dict.Lookdown(search));
            } else if ((textField.getText().trim().toLowerCase().contains("nghĩa")||textField.getText().trim().toLowerCase().contains("dịch"))
                    && textField.getText().trim().toLowerCase().contains("việt")){
                try {
                    dict.insertFromFileVE();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                String search = textField.getText().trim().toLowerCase().replace("sang","");
                //messages.add(new ChatEntry(dict.Lookup(search.trim()),MessageType.REMOTE));
                messages.add(new ChatEntry(dict.Lookdown(search),MessageType.REMOTE ));
                rh.writeHis(dict.Lookdown(search));
            } else if(textField.getText().trim().toLowerCase().contains("add")) {
                try {
                    dict.insertFromFileEV();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                String add = textField.getText().trim().toLowerCase().replace("add","");
                String[] cut = add.split(",");
                String finding = cut[0].trim().toLowerCase();
                String meaning = cut[1].trim().toLowerCase();
                dict.addDictionary(new Word(finding,meaning));
                messages.add(new ChatEntry("đã thêm "+finding,MessageType.REMOTE));
            } else if(textField.getText().contains("his")){
                List<String> his = new ArrayList<>();
                try {
                    his = rh.readfile();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                for(String i : his) {
                    messages.add(new ChatEntry(i.trim(), MessageType.REMOTE));
                }
            } else if(textField.getText().contains("phát âm")) {

                    String sound = textField.getText().trim().replace("phát âm","");
                    new Speech();
                    Speech.playSound(sound.trim());
                    messages.add(new ChatEntry("đã phát âm",MessageType.REMOTE));

            } else {
                messages.add(new ChatEntry("không thể phản hồi",MessageType.REMOTE));
            }
        });

        tipstolearn.setOnAction(event -> {
             {
                TipsReadFile tips = new TipsReadFile();
                List<String> tip;
                try {
                    tip = tips.readfile();
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
                int randomNumber = random.nextInt(100);
                messages.add(new ChatEntry(tip.get(randomNumber),MessageType.REMOTE));}
        });

        switchButton.setOnAction(evt -> {
            QuizApplication anotherApp = new QuizApplication();
            Stage stage = new Stage();
            try {
                anotherApp.start(stage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        exit.setOnAction(ect->{
            Platform.exit();
        });

        VBox vbox = new VBox(tipstolearn,switchButton,exit);
        ToggleButton toggleButton = new ToggleButton("Tools");
        vbox.setVisible(false);
        toggleButton.setOnAction(evt -> {
            if (toggleButton.isSelected()) {
                vbox.setVisible(true);
            } else {
                vbox.setVisible(false);
            }
        });
        localButton.getStyleClass().add("buttongo");
        textField.getStyleClass().add("textfield");
        toggleButton.getStyleClass().add("other");
        remoteButton.getStyleClass().add("buttonto");
        tipstolearn.getStyleClass().add("another");
        switchButton.getStyleClass().add("another1");
        exit.getStyleClass().add("another2");
        HBox hbox = new HBox(textField,localButton,remoteButton,toggleButton);
        //hbox.setAlignment(Pos.BASELINE_CENTER);
        VBox chatLayout = new VBox(listView,hbox,vbox);
        Scene scene = new Scene(chatLayout,900,500);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("samples.css")).toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.show();
    }

    enum MessageType{
        LOCAL, REMOTE
    }

    static class ChatCell extends ListCell<ChatEntry> {
        Text message = new Text();
        WebView webView = new WebView();
        WebEngine webEngine = webView.getEngine();

        StackPane bubble1 = new StackPane(message);
        StackPane bubble2 = new StackPane(webView);
        HBox graphic = new HBox(5,bubble1);
        HBox graphic2 = new HBox(5,bubble2);

        public ChatCell() {
            bubble1.getStyleClass().add("incoming-bubble");
        }
        @Override
        public void updateItem(ChatEntry item, boolean empty) {
            super.updateItem(item, empty);
            if (!empty && (item != null)) {
                if(item.typeProperty().get().equals(MessageType.LOCAL)) {
                    message.setText(item.textProperty());
                    graphic.setAlignment(Pos.CENTER_LEFT);
                    setGraphic(graphic);
                } else {
                    String htmlContent = item.textProperty();
                    webEngine.loadContent(htmlContent);
                    graphic.setAlignment(Pos.CENTER_RIGHT);
                    setGraphic(graphic2);
                }
            } else {
                message.setText("");
                setGraphic(null);
            }
        }
    }


    static class ChatEntry  {
        private  final String text;
        private final ObjectProperty<MessageType> type = new SimpleObjectProperty<>();
        public ChatEntry(String text, MessageType type) {
            this.text = text;
            this.type.set(type);
        }
        public String textProperty() {
            return text;
        }

        public ObjectProperty<MessageType> typeProperty() {
            return type;
        }
    }

}








