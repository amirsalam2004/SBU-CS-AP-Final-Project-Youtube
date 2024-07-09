package com.example.youtube;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class ProfileViewController {

    // handle subKeys in inisialize Function (sub or no / this is his page or no)

    public TilePane content;
    @FXML
    private ImageView profilePic;
    @FXML
    private ImageView longPic;
    @FXML
    private Label pageName;
    private Label numVideos;
    private Label numSubs;
    @FXML
    private AnchorPane moreInfo;
    @FXML
    private Button moreBtn;
    @FXML
    private Label describe;
    @FXML
    private Button unSubKey;
    @FXML
    private Button subKey;

    public void createVideoBox() {
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

        content.getChildren().add(vbox);
    }

    @FXML
    public void moreBtnClick() {
        if (moreInfo.isVisible()) {
            moreBtn.setText("More...");
            moreInfo.setVisible(false);
        }
        else {
            moreBtn.setText("Less");
            moreInfo.setVisible(true);
        }

    }

    @FXML
    public void subFunc() {
        subKey.setVisible(false);
        unSubKey.setVisible(true);
    }
    @FXML
    public void unSubFunc() {
        subKey.setVisible(true);
        unSubKey.setVisible(false);
    }
}
