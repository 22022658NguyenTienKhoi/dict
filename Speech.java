package com.example.demo1;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import javazoom.jl.player.Player;
public class Speech {
    public static void TextToSpeech(String word) {
        try {
            System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us" + ".cmu_us_kal.KevinVoiceDirectory");
            VoiceManager voiceManager = VoiceManager.getInstance();
            Voice voice = voiceManager.getVoice("kevin");
            voice.allocate();
            voice.speak(word);
            voice.deallocate();
        } catch (Exception e) {
            e.printStackTrace();
        }
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
