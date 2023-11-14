package com.example.demo1;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class services {
    public void mean(String text) {
        try {
            String api =
                    "https://translate.google.com/?ie=UTF-8&tl="
                            + "vi"
                            + "&text="
                            + URLEncoder.encode(text, StandardCharsets.UTF_8);
            URL url = new URL(api);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            InputStream str = con.getInputStream();
            InputStreamReader strr = new InputStreamReader(str);
            BufferedReader br = new BufferedReader(strr);
                // Read the response line by line
                StringBuilder response = new StringBuilder();
                String line;
                while((line =br.readLine())!=null)

                {
                    response.append(line);
                }
            System.out.println(response);
            con.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error in getting it");
        }
    }

}
