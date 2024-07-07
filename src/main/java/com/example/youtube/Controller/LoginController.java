package com.example.youtube.Controller;

import com.example.youtube.HelloApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Border;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    @FXML
    private TextField emailField;
    @FXML
    private TextField passwordField;
    private Stage stage = new Stage();
    private Parent root;
    @FXML
    private Button endLogin=new Button();

    @FXML
    private  Button signUp=new Button();







    @FXML
    public void emailFieldClick() {
        emailField.setStyle("-fx-border-color : #4de4ff; -fx-border-radius : 6; -fx-background-color:  #2C2829; -fx-text-inner-color : #ffff");
        passwordField.setStyle("-fx-border-radius : 6; -fx-background-color :  #2C2829; -fx-border-color : #2C2829; -fx-text-inner-color : #ffff");
    }
    @FXML
    public void passwordFieldClick() {
        passwordField.setStyle("-fx-border-radius : 6; -fx-background-color :  #2C2829; -fx-border-color :  #4de4ff; -fx-text-inner-color : #ffff");
        emailField.setStyle("-fx-border-color : #2C2829; -fx-border-radius : 6; -fx-background-color:  #2C2829; -fx-text-inner-color : #ffff");
    }

    @FXML
    public void openSignUpMenu() throws IOException {
        Stage currentStage = (Stage) emailField.getScene().getWindow();
        currentStage.close();
        root = FXMLLoader.load(getClass().getResource("signUp-view.fxml"));
        stage.setScene(new Scene(root));
        stage.setTitle("signUp Page");
        stage.show();
    }

    @FXML
    public void endLogin() {
        // closing the current scene
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {



        endLogin.setOnAction(e->{
            if(checkUser(emailField.getText(), hashPasswordSHA256(passwordField.getText()))){
                Stage currentStage = (Stage) emailField.getScene().getWindow();
                currentStage.close();
                try {
                    root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                stage.setScene(new Scene(root));
                stage.setTitle("");
                stage.show();
            }


        });



    }

    private boolean checkUser(String text, String s) {
//        send request for check user exist or not
        return true;
    }

    public static String hashPasswordSHA256 (String password){
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xFF & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
}