package com.example.youtube;

import com.example.youtube.Model.User;
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
        User user=new User("rouzbeh","R.84.askari@gamil.com","2024-07-05","1234","iran","1232","2024-07-05");

        String response="";
        try {
             response =gson1.toJson(user);
        }catch (Exception e){
            e.printStackTrace();
        }
        client.sendRequest(2,21,response);
        System.out.println(client.getResponse());
    }
    public static void main(String[] args) {
        launch();
    }
}