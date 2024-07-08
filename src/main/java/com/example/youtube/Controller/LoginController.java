package com.example.youtube.Controller;

import com.example.youtube.HelloApplication;
import com.example.youtube.HelloController;
import com.example.youtube.Model.Channel;
import com.example.youtube.Model.User;
import com.example.youtube.Server.Client;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Border;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
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
    private Label check=new Label();

    //-----------------------------------------------------------------------------
   public Client client=null;





    //------------------------------------------------------------------------------
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
                    FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));//go to Home page
                    Scene scene = null;
                    try {
                        scene = new Scene(fxmlLoader.load(), 1190, 627);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    //get user
                    try {
                        User user = client.getUserRequest(emailField.getText(), hashPasswordSHA256(passwordField.getText()));
                        System.out.println("[GET  USER--> COMPLETE  ]");

                        //get chanel
                        Channel channel = client.getChannelRequest(user.getUsername(), 0);
                        System.out.println("[GET  CHANNEL--> COMPLETE  ]");

                    HelloController helloController=new HelloController();
                    helloController.user = user;
                    helloController.channel = channel;
                    helloController.loginOn = true;
                    stage.setTitle("Youtube");
                    stage.setScene(scene);
                    stage.show();
                    }catch (Exception r){
                        r.printStackTrace();
                    }




                }catch (Exception ee){
                    ee.printStackTrace();
                }
            }else {
                check.setText("Not Correct ");
                check.setTextFill(Color.RED);
                check.setFont(Font.font("Californian FB"));

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