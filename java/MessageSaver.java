package com.example.demo1;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MessageSaver {
    private static final String FILE_PATH = "messages.ser";

    public static void saveMessages(List<Chatter.ChatEntry> messages) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            outputStream.writeObject(messages);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Chatter.ChatEntry> loadMessages() {
        List<Chatter.ChatEntry> messages = new ArrayList<>();
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
            Object object = inputStream.readObject();
            if (object instanceof List<?>) {
                messages = (List<Chatter.ChatEntry>) object;
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return messages;
    }
}
