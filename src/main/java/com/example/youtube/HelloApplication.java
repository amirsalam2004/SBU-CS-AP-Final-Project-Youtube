package com.example.youtube;

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
//        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
//        Scene scene = new Scene(fxmlLoader.load(), 1190, 627);
//        stage.setTitle("Youtube");
////        stage.getIcons().add(new Image("file:\"D:\\usb\\ap\\assignment\\YoutubeNotes\\FirstBranch(AmirAli\\YouTube\\src\\main\\resources\\com\\example\\youtube\\Images\\icon.jpeg\""));
//        stage.setScene(scene);
//        stage.show();

        Client client=new Client("localhost");
        System.out.println( client.sendRequest(8,"unnamed"));
        System.out.println( client.getImageBytes("unnamed"));

    }

    public static void main(String[] args) {
        launch();
    }
}