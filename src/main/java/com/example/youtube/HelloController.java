package com.example.youtube;


import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;

public class HelloController {
    public TilePane container;
    public TilePane playListsContainer;
    public TilePane subscriptionContainer;
    public TilePane historyContainer;
    public ScrollPane HistoryPane;
    public TilePane watchLaterContainer;
    public ScrollPane watchLaterPane;
    public ScrollPane likedVideoPane;
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
    private Label exploreLabel;

    @FXML
    private AnchorPane mainField;
    @FXML
    private AnchorPane sideBar;
    @FXML
    private AnchorPane settingSideBar;
    @FXML
    private ScrollPane videos;
    @FXML
    private AnchorPane videoView;
    @FXML
    private ScrollPane playListsPane;
    @FXML
    private ScrollPane explore;
    @FXML
    private ScrollPane subscriptionPane;
    @FXML
    private AnchorPane shortsPane;
    @FXML
    private ScrollPane notificationPane;

    private boolean isSideBarOn = false;
    @FXML
    private AnchorPane firstSideBar;
    private boolean isFirstSideBarOn;
    private Parent root;
    private Stage stage = new Stage();


    MediaPlayer mediaPlayer;

    @FXML
    private MediaView mediaView;
    @FXML
    private Button playBtn;
    @FXML
    private ImageView platImg;
    @FXML
    private ImageView pauseImg;
    @FXML
    private Slider timeSlider;
    @FXML
    private ImageView notLikedImg;
    @FXML
    private ImageView likedImg;
    @FXML
    private ImageView notDisLikedImg;
    @FXML
    private ImageView disLikedImg;
    @FXML
    private ChoiceBox searchFilter;


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


        // search filter
        searchFilter.getSelectionModel().select("Video");
        searchFilter.getItems().add("Video");
        searchFilter.getItems().add("Channel");
        searchFilter.getItems().add("Playlist");
    }

    private void closeAllPanes() {
        playListsPane.setVisible(false);
        videoView.setVisible(false);
        subscriptionPane.setVisible(false);
        notificationPane.setVisible(false);
        HistoryPane.setVisible(false);
        watchLaterPane.setVisible(false);
        likedVideoPane.setVisible(false);
        if (mediaPlayer != null) {
            mediaPlayer.pause();
        }
    }

    @FXML
    protected void homeClick() {
        videos.setVisible(true);
        explore.setVisible(false);
        closeAllPanes();

    }

    @FXML
    protected void settingClick() {
        sideBar.setVisible(false);
        closeAllPanes();
        settingSideBar.setVisible(true);
        videos.setVisible(false);
        explore.setVisible(false);
        firstSideBar.setVisible(false);
    }
    @FXML
    protected void playListsClick() {
        closeAllPanes();
        videos.setVisible(false);
        explore.setVisible(false);
        subscriptionPane.setVisible(false);
        playListsPane.setVisible(true);
    }
    @FXML
    protected void trendingClick() {
        explore.setVisible(true);
        exploreLabel.setText("Trending");
        videos.setVisible(false);
        closeAllPanes();
    }
    @FXML
    protected void musicClick() {
        explore.setVisible(true);
        exploreLabel.setText("Music");
        videos.setVisible(false);
        closeAllPanes();
    }
    @FXML
    protected void gamingClick() {
        explore.setVisible(true);
        exploreLabel.setText("Gaming");
        videos.setVisible(false);
        closeAllPanes();
    }
    @FXML
    protected void newsClick() {
        exploreLabel.setText("News");
        videos.setVisible(false);
        closeAllPanes();
        explore.setVisible(true);
    }
    @FXML
    protected void sportsClick() {
        exploreLabel.setText("Sports");
        videos.setVisible(false);
        closeAllPanes();
        explore.setVisible(true);
    }
    @FXML
    protected void podcastsClick() {
        exploreLabel.setText("Podcasts");
        videos.setVisible(false);
        closeAllPanes();
        videoView.setVisible(false);
        if (mediaPlayer != null) {
            mediaPlayer.pause();
        }
        explore.setVisible(true);
    }

    @FXML
    protected void openVideoPage() {
        videos.setVisible(false);
        explore.setVisible(false);
        closeAllPanes();
        videoView.setVisible(true);
        if (mediaPlayer != null) {
            mediaPlayer.pause();
        }
    }

    @FXML
    protected void subscriptionClick() {
        videos.setVisible(false);
        explore.setVisible(false);
        closeAllPanes();

        if (mediaPlayer != null) {
            mediaPlayer.pause();
        }
        subscriptionPane.setVisible(true);
    }

    @FXML
    protected void shortsClick() {
        subscriptionPane.setVisible(false);
        videos.setVisible(false);
        explore.setVisible(false);
        closeAllPanes();

        if (mediaPlayer != null) {
            mediaPlayer.pause();
        }
        shortsPane.setVisible(true);
    }

    @FXML
    public void notificationsClick() {
        sideBar.setVisible(false);
        videoView.setVisible(false);
        explore.setVisible(false);
        closeAllPanes();
        notificationPane.setVisible(true);
    }

    @FXML
    public void historyClick() {
        explore.setVisible(false);
        closeAllPanes();
        HistoryPane.setVisible(true);
    }

    @FXML
    public void watchLaterClick() {
        explore.setVisible(false);
        closeAllPanes();
        watchLaterPane.setVisible(true);
    }

    @FXML
    public void likedVideoClick() {
        closeAllPanes();
        explore.setVisible(false);
        likedVideoPane.setVisible(true);
    }

    @FXML
    protected void addVideoClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("addVideo.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 870, 520);
        stage.setTitle("addVideo");
        stage.setScene(scene);
        stage.show();
    }

    // pass video here
    // pass playList here
    public void createVideoBox(TilePane tilePane) {
        VBox vbox = new VBox();
        vbox.prefWidth(309.0);
        vbox.prefHeight(680.0);

        ImageView imageView = new ImageView();
//        imageView.setImage(new Image(// path));
        imageView.setFitHeight(191.0);
        imageView.setFitWidth(261.0);

        Label title = new Label();
        title.setAlignment(Pos.CENTER);
        title.setPrefHeight(45.0);
        title.setPrefWidth(264.0);
        title.setFont(Font.font(37.0));
        // text from server

        Label channelName = new Label();
        channelName.setAlignment(Pos.CENTER);
        channelName.setPrefHeight(45.0);
        channelName.setPrefWidth(266.0);
        channelName.setFont(Font.font(37.0));
        // text from server

        vbox.getChildren().addAll(imageView, title, channelName);

        tilePane.getChildren().add(vbox);
    }


    @FXML
    public void openSongMedia(ActionEvent event) {
        System.out.println("Open Song");

        try {
            FileChooser chooser = new FileChooser();
            File file = chooser.showOpenDialog(null);

            Media media = new Media(file.toURI().toURL().toString());
            mediaPlayer = new MediaPlayer(media);

            mediaView.setMediaPlayer(mediaPlayer);

            // slider

            mediaPlayer.setOnReady(()->{
                timeSlider.setMin(0);
                timeSlider.setMax(mediaPlayer.getMedia().getDuration().toSeconds());
                System.out.println(mediaPlayer.getMedia().getDuration().toSeconds());
                timeSlider.setValue(0);
            });

            mediaPlayer.currentTimeProperty().addListener(new ChangeListener<Duration>() {
                @Override
                public void changed(ObservableValue<? extends Duration> observableValue, Duration duration, Duration t1) {
                    Duration d = mediaPlayer.getCurrentTime();
                    timeSlider.setValue(d.toSeconds());
                }
            });

            timeSlider.valueProperty().addListener(new ChangeListener<Number>() {
                @Override
                public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                    if (timeSlider.isPressed()) {
                        double value = timeSlider.getValue();
                        mediaPlayer.seek(new Duration(value * 1000));
                    }
                }
            });
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }

    @FXML
    public void play() {
        MediaPlayer.Status status = mediaPlayer.getStatus();

        try {
            if (status == MediaPlayer.Status.PLAYING) {
                mediaPlayer.pause();
                pauseImg.setVisible(false);
                platImg.setVisible(true);
            }
            else {
                mediaPlayer.play();
                pauseImg.setVisible(true);
                platImg.setVisible(false);
            }
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }

    @FXML
    public void nextBtnClick() {
        double d = mediaPlayer.getCurrentTime().toSeconds();

        d += 15;

        mediaPlayer.seek(new Duration(d * 1000));
    }

    @FXML
    public void preBtnClick() {
        double d = mediaPlayer.getCurrentTime().toSeconds();

        d -= 10;

        mediaPlayer.seek(new Duration(d * 1000));
    }

    @FXML
    public void mediaViewClickToHidePlayBtn() {
        MediaPlayer.Status status = mediaPlayer.getStatus();

        if (platImg.isVisible() || pauseImg.isVisible()) {
            platImg.setVisible(false);
            pauseImg.setVisible(false);
        }
        else {
            if (status == MediaPlayer.Status.PLAYING) {
                pauseImg.setVisible(true);
            }
            else {
                platImg.setVisible(true);
            }
        }
    }

    @FXML
    public void likeUnlikeClick() {
        if (likedImg.isVisible()) {
            likedImg.setVisible(false);
            notLikedImg.setVisible(true);
        }
        else {
            likedImg.setVisible(true);
            notLikedImg.setVisible(false);
            disLikedImg.setVisible(false);
            notDisLikedImg.setVisible(true);
        }
    }

    @FXML
    public void disLikeUnDisLikeClick() {
        if (disLikedImg.isVisible()) {
            disLikedImg.setVisible(false);
            notDisLikedImg.setVisible(true);
        }
        else {
            disLikedImg.setVisible(true);
            notDisLikedImg.setVisible(false);
            likedImg.setVisible(false);
            notLikedImg.setVisible(true);
        }
    }
}