package com.example.demo1.easyquiz;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.*;

public class Quiz3Controller extends QuizController {
    List<Image> images = new ArrayList<>();
    List<List<String>> list = new ArrayList<>();
    List<String> corrects = Arrays.asList("elephant","lion","sheep","rat","cat","mouse","beaver","killer whale","aardvark","alpaca","gerbil",
    "peacock","owl","worm","parrot","mosquito","tuna","shark","crocodile","flamingo","wasp");
    {
            Image image1 = new Image("D:\\demo1\\src\\main\\resources\\com\\example\\demo1\\easyquiz\\q1.png");
            Image image2 = new Image("D:\\demo1\\src\\main\\resources\\com\\example\\demo1\\easyquiz\\q2.png");
        Image image3 = new Image("D:\\demo1\\src\\main\\resources\\com\\example\\demo1\\easyquiz\\q3.png");
        Image image4 = new Image("D:\\demo1\\src\\main\\resources\\com\\example\\demo1\\easyquiz\\q4.png");
        Image image5 = new Image("D:\\demo1\\src\\main\\resources\\com\\example\\demo1\\easyquiz\\q5.png");
        Image image6 = new Image("D:\\demo1\\src\\main\\resources\\com\\example\\demo1\\easyquiz\\q6.png");
        Image image7 = new Image("D:\\demo1\\src\\main\\resources\\com\\example\\demo1\\easyquiz\\q7.png");
        Image image8 = new Image("D:\\demo1\\src\\main\\resources\\com\\example\\demo1\\easyquiz\\q8.png");
        Image image9 = new Image("D:\\demo1\\src\\main\\resources\\com\\example\\demo1\\easyquiz\\q9.png");
        Image image10 = new Image("D:\\demo1\\src\\main\\resources\\com\\example\\demo1\\easyquiz\\q10.png");
        Image image11 = new Image("D:\\demo1\\src\\main\\resources\\com\\example\\demo1\\easyquiz\\q11.png");
        Image image12 = new Image("D:\\demo1\\src\\main\\resources\\com\\example\\demo1\\easyquiz\\q12.png");
        Image image13 = new Image("D:\\demo1\\src\\main\\resources\\com\\example\\demo1\\easyquiz\\q13.png");
        Image image14 = new Image("D:\\demo1\\src\\main\\resources\\com\\example\\demo1\\easyquiz\\q14.png");
        Image image15 = new Image("D:\\demo1\\src\\main\\resources\\com\\example\\demo1\\easyquiz\\q15.png");
        Image image16 = new Image("D:\\demo1\\src\\main\\resources\\com\\example\\demo1\\easyquiz\\q16.png");
        Image image17 = new Image("D:\\demo1\\src\\main\\resources\\com\\example\\demo1\\easyquiz\\q17.png");
        Image image18 = new Image("D:\\demo1\\src\\main\\resources\\com\\example\\demo1\\easyquiz\\q18.png");
        Image image19 = new Image("D:\\demo1\\src\\main\\resources\\com\\example\\demo1\\easyquiz\\q19.png");
        Image image20 = new Image("D:\\demo1\\src\\main\\resources\\com\\example\\demo1\\easyquiz\\q20.png");
        Image image21 = new Image("D:\\demo1\\src\\main\\resources\\com\\example\\demo1\\easyquiz\\q21.png");
            images.add(image1);
        images.add(image2);
        images.add(image3);
        images.add(image4);
        images.add(image5);
        images.add(image6);
        images.add(image7);
        images.add(image8);
        images.add(image9);
        images.add(image10);
        images.add(image11);
        images.add(image12);
        images.add(image13);
        images.add(image14);
        images.add(image15);
        images.add(image16);
        images.add(image17);
        images.add(image18);
        images.add(image19);
        images.add(image20);
        images.add(image21);
    }

    {List<String> ans1 = Arrays.asList("rat","elephant","bear","wolf");
        List<String> ans2 = Arrays.asList("lion","mouse","elephant","dog");
        List<String> ans3 = Arrays.asList("horse","sheep","cat","rat");
        List<String> ans4 = Arrays.asList("horse","bear","rat","elephant");
        List<String> ans5 = Arrays.asList("rabbit","mouse","cat","dog");
        List<String> ans6 = Arrays.asList("mouse","cat","horse","wolf");
        List<String> ans7 = Arrays.asList("gerbil","armadillo","beaver","sheep");
        List<String> ans8 = Arrays.asList("springbok","badger","killer whale","horse");
        List<String> ans9 = Arrays.asList("aardvark","alpaca","beaver","gerbil");
        List<String> ans10 = Arrays.asList("badger","alpaca","otter","chipmunk");
        List<String> ans11 = Arrays.asList("chipmunk", "raccoon", "gerbil","aardvard");
        List<String> ans12 = Arrays.asList("swan", "eagle" ,"peacock","canary");
        List<String> ans13 = Arrays.asList("crab", "blackbird" ,"owl", "eagle");
        List<String> ans14 = Arrays.asList("slug", "crocodile","worm","swan");
        List<String> ans15 = Arrays.asList("peacock", "parrot", "flamingo","falcon");
        List<String> ans16 = Arrays.asList("mosquito","snail" ,"grasshopper","ant");
        List<String> ans17 = Arrays.asList("swan","crab","tuna","shark");
        List<String> ans18 = Arrays.asList("shark","toad","starfish","whale");
        List<String> ans19 = Arrays.asList("crab","octopus","crocodile","lizard");
        List<String> ans20 = Arrays.asList("parrot","flamingo","peacock","snail");
        List<String> ans21 = Arrays.asList("wasp", "ant","toad","grasshopper");

        list.add(ans1);
        list.add(ans2);
        list.add(ans3);
        list.add(ans4);
        list.add(ans5);
        list.add(ans6);
        list.add(ans7);
        list.add(ans8);
        list.add(ans9);
        list.add(ans10);
        list.add(ans11);
        list.add(ans12);
        list.add(ans13);
        list.add(ans14);
        list.add(ans15);
        list.add(ans16);
        list.add(ans17);
        list.add(ans18);
        list.add(ans19);
        list.add(ans20);
        list.add(ans21);
    }
    List<Integer> index = Arrays.asList(0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20);
    {
        Collections.shuffle(index);
    }
    @Override
    protected void loadQuestions(){
        ImageView imageView = new ImageView(images.get(index.get(counter)));
        question.setGraphic(imageView);
        opt1.setText(list.get(index.get(counter)).get(0));
        opt2.setText(list.get(index.get(counter)).get(1));
        opt3.setText(list.get(index.get(counter)).get(2));
        opt4.setText(list.get(index.get(counter)).get(3));
    }
    @Override
    protected boolean checkAnswer(String answer) {
        return  answer.equals(corrects.get(index.get(counter)));
    }
}