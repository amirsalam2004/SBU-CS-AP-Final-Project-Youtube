package com.example.youtube;

import com.example.youtube.Model.*;
import com.example.youtube.Server.Client;
import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    private static final Gson gson1 = new Gson();
    @Override
    public void start(Stage stage) throws IOException {

//        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
//        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
//        stage.setTitle("Hello!");
//        stage.setScene(scene);
//        stage.show();
        Client client=new Client("localhost");
//        User user=new User("rouzbeh","R.84.askari@gamil.com","2024-07-05","1234","iran","1232","2024-07-05");
//        Video video=new Video("33312","12","rouzbeh","12221212","2000-01-01",1,1,11,12);
//        PlayList playList=new PlayList("roouzbeh","12","12312","!23123","12312");
//        Channel channel=new Channel("rouzbeh","das","Asd","Asd","asdasd");

//
            String rouz="0#12";
//          String response="";

            client.sendRequest(1,11,rouz);
//          User user=client.getResponse();
            rouz=client.getResponse();
//            User =rouz.



//        Comment commnet=new Comment("this is test for Server ","ruzbeh","rouzbeh","rouhzhbhe","123",1,1,"!2312");
//        String response1="11323#rouzbeh#1";
//        try {
////            response1 =gson1.toJson(commnet);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        client.sendRequest(2,27,response1);
//        System.out.println(client.getResponse());
    }
    public static void main(String[] args) {
        launch();
    }
}