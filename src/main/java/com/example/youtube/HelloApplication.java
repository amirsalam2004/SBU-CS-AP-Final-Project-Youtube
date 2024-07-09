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
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("signUp-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1190, 627);
        scene.getStylesheets().addAll(
                getClass().getResource("stylecss.css").toExternalForm(),
                getClass().getResource("DarkStyles.css").toExternalForm()
        );
        stage.setTitle("Youtube");
        stage.setScene(scene);
//        Add icon
        stage.show();
        Client client=new Client("localhost");

//        System.out.println( client.sendRequest(8,"unnamed"));
//        System.out.println( client.getImageBytes("unnamed"));

    }

    public static void main(String[] args) {
        launch();
    }
}