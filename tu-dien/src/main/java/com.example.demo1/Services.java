package com.example.demo1;

import javazoom.jl.player.Player;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class Services {
    public static String googleTranslate(String langFrom, String langTo, String text) throws IOException {
        String api = "https://script.google.com/macros/s/AKfycbz749_PWt_y406G9m7P_0f9_7BSPQICHO3kX3CNb2fheBF3g0g/exec" +
                "?q=" + URLEncoder.encode(text, StandardCharsets.UTF_8) +
                "&target=" + langTo +
                "&source=" + langFrom;
        URL url = new URL(api);
        StringBuffer stringbuff = new StringBuffer();
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        while ((inputLine = bufferedReader.readLine()) != null) {
            stringbuff.append(inputLine);
        }
        bufferedReader.close();
        return stringbuff.toString();
    }

    public static void playSound(String text) {
        try {
            String api = "https://translate.google.com/translate_tts?ie=UTF-8&tl=en"
                    + "&client=tw-ob&q="
                    + URLEncoder.encode(text, StandardCharsets.UTF_8);
            URL url = new URL(api);
            HttpURLConnection connect = (HttpURLConnection) url.openConnection();
            InputStream audio = connect.getInputStream();
            new Player(audio).play();
            connect.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error in getting voices");
        }
    }
}

