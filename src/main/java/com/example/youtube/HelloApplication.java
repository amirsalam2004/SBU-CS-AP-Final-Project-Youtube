package com.example.youtube;

import com.example.youtube.Model.User;
import com.example.youtube.Server.Client;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1300, 627);
        stage.setTitle("Youtube");
        stage.setScene(scene);
//        Add icon
//        stage.getIcons().add(new Image("file:icon.png"));
//        stage.getIcons().add(new Image("src/main/resources/com/example/youtube/Images/unnamed.jpg"));
// Add icon
        stage.getIcons().add(new Image("file:src/main/resources/com/example/youtube/Images/unnamed.jpg"));

        stage.show();
        Client client=new Client("localhost");
        HelloController helloController=new HelloController();
        System.out.println(client.addUserRequest(new User("123","!@3","!23","!23","!23","!23","!23")));

//        System.out.println( client.sendRequest(8,"unnamed"));
//        System.out.println( client.getImageBytes("unnamed"));

    }

    public static void main(String[] args) {
        launch();
    }
}