package com.example.youtube.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Border;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {
    @FXML
    private TextField emailField;
    @FXML
    private TextField passwordField;
    private Stage stage = new Stage();
    private Parent root;
    @FXML
    private Button endLogin;

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
}
