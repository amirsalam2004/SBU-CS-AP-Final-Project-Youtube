package com.example.youtube;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class addInformationVideoController {
    @FXML
    private TextField title;
    @FXML
    private ChoiceBox categories;
    @FXML
    private TextArea description;

    public void initialize() {
        categories.getItems().add("sport");
        categories.getItems().add("travel");
        categories.getItems().add("education");
        categories.getItems().add("news");
        categories.getItems().add("game");
        categories.getItems().add("comedy");
        categories.getItems().add("car");
        categories.getItems().add("science");
        categories.getItems().add("lifeStyle");
        categories.getItems().add("other");
        categories.getSelectionModel().select("sport");
    }
}
