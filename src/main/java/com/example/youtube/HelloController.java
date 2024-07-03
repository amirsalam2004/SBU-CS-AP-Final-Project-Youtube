package com.example.youtube;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloController {
    @FXML
    private Rectangle homeBackGround;
    @FXML
    private Rectangle shortBackGround;
    @FXML
    private Rectangle subscriptionBackGround;
    @FXML
    private Rectangle historyBackGround;
    @FXML
    private Rectangle watchLaterBackGround;
    @FXML
    private Rectangle likedVideosBackGround;
    @FXML
    private Rectangle TrendingBackGround;
    @FXML
    private Rectangle musicBackGround;
    @FXML
    private Rectangle gamingBackGround;
    @FXML
    private Rectangle newsBackGround;
    @FXML
    private Rectangle sportsBackGround;
    @FXML
    private Rectangle productsBackGround;
    @FXML
    private Rectangle playListsBackGround;
    @FXML
    private Rectangle settingBackGround;

    @FXML
    private Label home;
    @FXML
    private Label shortL;
    @FXML
    private Label subscription;
    @FXML
    private Label history;
    @FXML
    private Label playlist;
    @FXML
    private Label watchLater;
    @FXML
    private Label likedVideos;
    @FXML
    private  Label trending;
    @FXML
    private Label music;
    @FXML
    private Label news;
    @FXML
    private Label sports;
    @FXML
    private Label podcast;
    @FXML
    private Label setting;
    @FXML
    private Label gaming;

    @FXML
    private AnchorPane mainField;
    @FXML
    private AnchorPane sideBar;
    @FXML
    private AnchorPane settingSideBar;
    @FXML
    private AnchorPane videos;

    private boolean isSideBarOn = false;
    @FXML
    private AnchorPane firstSideBar;
    private boolean isFirstSideBarOn;
    private Parent root;
    private Stage stage = new Stage();


    @FXML
    protected void onHelloButtonClick() throws IOException {

        Stage currentStage = (Stage) homeBackGround.getScene().getWindow();
        currentStage.close();
        root = FXMLLoader.load(getClass().getResource("darkHomePage.fxml"));
        stage.setScene(new Scene(root));
        stage.setTitle("Login Page");
        stage.show();

    }
    @FXML
    protected void sideBarBtnClick() {
        sideBar.setVisible(!isSideBarOn);
        isSideBarOn = !isSideBarOn;
        firstSideBar.setVisible(isFirstSideBarOn);
        isFirstSideBarOn = !isFirstSideBarOn;
        settingSideBar.setVisible(false);

        // Videos Size
        if (sideBar.isVisible()) {
            videos.setPrefWidth(922);
            videos.setLayoutX(253);
        }
        else {
            videos.setPrefWidth(1080);
            videos.setLayoutX(100);
        }
    }

    private void handleRectangleHover(Rectangle rectangle, Paint hoverColor, Paint normalColor) {
        rectangle.setOnMouseEntered(event -> rectangle.setFill(hoverColor));
        rectangle.setOnMouseExited(event -> rectangle.setFill(normalColor));
    }

    private void handleLabelHover(Label label, Paint hoverColor, Paint normalColor) {
        if (label == home) {
            label.setOnMouseEntered(event -> homeBackGround.setFill(hoverColor));
            label.setOnMouseExited(event -> homeBackGround.setFill(normalColor));
        }
        else if (label == shortL) {
            label.setOnMouseEntered(event -> shortBackGround.setFill(hoverColor));
            label.setOnMouseExited(event -> shortBackGround.setFill(normalColor));
        }
        else if (label == subscription) {
            label.setOnMouseEntered(event -> subscriptionBackGround.setFill(hoverColor));
            label.setOnMouseExited(event -> subscriptionBackGround.setFill(normalColor));
        }
        else if (label == playlist) {
            label.setOnMouseEntered(event -> playListsBackGround.setFill(hoverColor));
            label.setOnMouseExited(event -> playListsBackGround.setFill(normalColor));
        }
        else if (label == history) {
            label.setOnMouseEntered(event -> historyBackGround.setFill(hoverColor));
            label.setOnMouseExited(event -> historyBackGround.setFill(normalColor));
        }
        else if (label == watchLater) {
            label.setOnMouseEntered(event -> watchLaterBackGround.setFill(hoverColor));
            label.setOnMouseExited(event -> watchLaterBackGround.setFill(normalColor));
        }
        else if (label == likedVideos) {
            label.setOnMouseEntered(event -> likedVideosBackGround.setFill(hoverColor));
            label.setOnMouseExited(event -> likedVideosBackGround.setFill(normalColor));
        }
        else if (label == trending) {
            label.setOnMouseEntered(event -> TrendingBackGround.setFill(hoverColor));
            label.setOnMouseExited(event -> TrendingBackGround.setFill(normalColor));
        }
        else if (label == music) {
            label.setOnMouseEntered(event -> musicBackGround.setFill(hoverColor));
            label.setOnMouseExited(event -> musicBackGround.setFill(normalColor));
        }
        else if (label == gaming) {
            label.setOnMouseEntered(event -> gamingBackGround.setFill(hoverColor));
            label.setOnMouseExited(event -> gamingBackGround.setFill(normalColor));
        }
        else if (label == sports) {
            label.setOnMouseEntered(event -> sportsBackGround.setFill(hoverColor));
            label.setOnMouseExited(event -> sportsBackGround.setFill(normalColor));
        }
        else if (label == podcast) {
            label.setOnMouseEntered(event -> productsBackGround.setFill(hoverColor));
            label.setOnMouseExited(event -> productsBackGround.setFill(normalColor));
        }
        else if (label == setting) {
            label.setOnMouseEntered(event -> settingBackGround.setFill(hoverColor));
            label.setOnMouseExited(event -> settingBackGround.setFill(normalColor));
        }
    }

    @FXML
    public void initialize() {
        // SideBar Hovers
        Paint hoverColor, normalColor;
        if (mainField.getId().equals("1")) {
            hoverColor = Paint.valueOf("#CBC6C6");
            normalColor = Paint.valueOf("#fff");
        }
        else {
            hoverColor = Paint.valueOf("#7F7C7C");
            normalColor = Paint.valueOf("#272424");
        }
        Rectangle[] rectangles = {
                homeBackGround, shortBackGround, subscriptionBackGround, historyBackGround,
                watchLaterBackGround, likedVideosBackGround, TrendingBackGround, musicBackGround,
                gamingBackGround, newsBackGround, sportsBackGround, productsBackGround, playListsBackGround,
                settingBackGround
        };
        Label[] labels = {
                home, shortL, playlist, subscription, history, watchLater, likedVideos,
                trending, music, news, sports, podcast, setting, gaming
        };
        for (Rectangle rectangle : rectangles) {
            handleRectangleHover(rectangle, hoverColor, normalColor);
        }
        for (Label label : labels) {
            handleLabelHover(label, hoverColor, normalColor);
        }

    }

    @FXML
    protected void settingClick() {
        sideBar.setVisible(false);
        settingSideBar.setVisible(true);
    }
}