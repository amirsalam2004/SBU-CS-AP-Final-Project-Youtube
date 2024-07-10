package com.example.youtube;

import com.example.youtube.Model.Channel;
import com.example.youtube.Model.User;
import com.example.youtube.Server.Client;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.net.URL;
import java.util.ResourceBundle;

public class ProfileViewController implements Initializable {

    // handle subKeys in inisialize Function (sub or no / this is his page or no)

    public TilePane content;

    public Channel channel;

    public User user;
    public boolean check=true;
    public Client client;
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
            //
            describe.setText(channel.getDescription());

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        if (check){
            unSubKey.setVisible(false);
        }else{
            unSubKey.setVisible(true);
        }

        pageName.setText(channel.getName());
        //and get mor information form database
    }
}
