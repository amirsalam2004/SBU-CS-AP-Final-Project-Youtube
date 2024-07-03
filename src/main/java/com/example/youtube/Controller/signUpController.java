package com.example.youtube.Controller;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class signUpController {
    @FXML
    private TextField emailField;
    @FXML
    private TextField passwordField;
    @FXML
    private TextField secondPasswordField;
    private Stage stage = new Stage();
    private Parent root;
    @FXML
    private Button endSignUp;

    @FXML
    public void emailFieldClick() {
        emailField.setStyle("-fx-border-color : #4de4ff; -fx-border-radius : 6; -fx-background-color:  #2C2829; -fx-text-inner-color : #ffff");
        passwordField.setStyle("-fx-border-radius : 6; -fx-background-color :  #2C2829; -fx-border-color : #2C2829; -fx-text-inner-color : #ffff");
        secondPasswordField.setStyle("-fx-border-radius : 6; -fx-background-color :  #2C2829; -fx-border-color : #2C2829; -fx-text-inner-color : #ffff");
    }
    @FXML
    public void passwordFieldClick() {
        passwordField.setStyle("-fx-border-radius : 6; -fx-background-color :  #2C2829; -fx-border-color :  #4de4ff; -fx-text-inner-color : #ffff");
        emailField.setStyle("-fx-border-color : #2C2829; -fx-border-radius : 6; -fx-background-color:  #2C2829; -fx-text-inner-color : #ffff");
        secondPasswordField.setStyle("-fx-border-radius : 6; -fx-background-color :  #2C2829; -fx-border-color : #2C2829; -fx-text-inner-color : #ffff");
    }
    @FXML
    public void secondPasswordFieldClick() {
        secondPasswordField.setStyle("-fx-border-radius : 6; -fx-background-color :  #2C2829; -fx-border-color :  #4de4ff; -fx-text-inner-color : #ffff");
        emailField.setStyle("-fx-border-color : #2C2829; -fx-border-radius : 6; -fx-background-color:  #2C2829; -fx-text-inner-color : #ffff");
        passwordField.setStyle("-fx-border-radius : 6; -fx-background-color :  #2C2829; -fx-border-color : #2C2829; -fx-text-inner-color : #ffff");
    }
    @FXML
    public void endOfSignUp() {
        // close the current scene
    }
}
