//package com.example.youtube;
//
//import javafx.fxml.FXML;
//import javafx.fxml.FXMLLoader;
//import javafx.scene.Parent;
//import javafx.scene.Scene;
//import javafx.scene.layout.AnchorPane;
//import javafx.scene.paint.Paint;
//import javafx.scene.shape.Rectangle;
//import javafx.stage.Stage;
//
//import java.io.IOException;
//
//public class DarkMainController {
//    @FXML
//    private Rectangle homeBackGround;
//    @FXML
//    private Rectangle shortBackGround;
//    @FXML
//    private Rectangle subscriptionBackGround;
//    @FXML
//    private Rectangle historyBackGround;
//    @FXML
//    private Rectangle watchLaterBackGround;
//    @FXML
//    private Rectangle likedVideosBackGround;
//    @FXML
//    private Rectangle TrendingBackGround;
//    @FXML
//    private Rectangle musicBackGround;
//    @FXML
//    private Rectangle gamingBackGround;
//    @FXML
//    private Rectangle newsBackGround;
//    @FXML
//    private Rectangle sportsBackGround;
//    @FXML
//    private Rectangle productsBackGround;
//    @FXML
//    private Rectangle playListsBackGround;
//    @FXML
//    private Rectangle settingBackGround;
//    @FXML
//    private AnchorPane sideBar;
//    private boolean isSideBarOn = false;
//    @FXML
//    private AnchorPane firstSideBar;
//    private boolean isFirstSideBarOn;
//    private Parent root;
//    private Stage stage = new Stage();
//
//
//    @FXML
//    protected void onHelloButtonClick() throws IOException {
////        welcomeText.setText("Welcome to JavaFX Application!");
//
//        Stage currentStage = (Stage) homeBackGround.getScene().getWindow();
//        currentStage.close();
//        root = FXMLLoader.load(getClass().getResource("login-view.fxml"));
//        stage.setScene(new Scene(root));
//        stage.setTitle("Login Page");
//        stage.show();
//
//    }
//
//    @FXML
//    protected void sideBarBtnClick() {
//        sideBar.setVisible(!isSideBarOn);
//        isSideBarOn = !isSideBarOn;
//        firstSideBar.setVisible(isFirstSideBarOn);
//        isFirstSideBarOn = !isFirstSideBarOn;
//    }
//    private void handleRectangleHover(Rectangle rectangle, Paint hoverColor, Paint normalColor) {
//        rectangle.setOnMouseEntered(event -> rectangle.setFill(hoverColor));
//        rectangle.setOnMouseExited(event -> rectangle.setFill(normalColor));
//    }
//
//    @FXML
//    public void initialize() {
//        Paint hoverColor = Paint.valueOf("#7F7C7C");
//        Paint normalColor = Paint.valueOf("#272424");
//        Rectangle[] rectangles = {
//                homeBackGround, shortBackGround, subscriptionBackGround, historyBackGround,
//                watchLaterBackGround, likedVideosBackGround, TrendingBackGround, musicBackGround,
//                gamingBackGround, newsBackGround, sportsBackGround, productsBackGround, playListsBackGround,
//                settingBackGround
//        };
//        for (Rectangle rectangle : rectangles) {
//            handleRectangleHover(rectangle, hoverColor, normalColor);
//        }
//    }
//}
