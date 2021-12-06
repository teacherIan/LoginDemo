package com.example.logindemo;

import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.BoundingBox;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Transform;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    @FXML
    private Button newAccountButton;

    @FXML
    private Button signInButton;

    @FXML
    Button exitButton;

    @FXML
    HBox hBox;

    @FXML
    private ImageView icon;

    @FXML
    private Label welcomeText;

    @FXML
    Line bottomLine;

    double x = 100,y = 50;
    double gravity = .00001;
    private double xScene, yScene;
    boolean game = true;

    Timeline timeline = new Timeline(new KeyFrame(Duration.millis(1), new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent actionEvent) {

            icon.setY(icon.getY() + gravity);
            collision();
            gravity+= .0001;

        }
    }));


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        controlButtonScale(signInButton);
        controlButtonScale(newAccountButton);
        controlButtonScale(exitButton);

        DBUtils.draggable(hBox);
        DBUtils.draggable(exitButton);
        exitButton.setOnMouseClicked(mouseEvent -> {
            DBUtils.currentStage.close();
        });

        RotateTransition rt = new RotateTransition(Duration.seconds(2000),icon);
        rt.setByAngle(500000);

        rt.setCycleCount(Animation.INDEFINITE);
        rt.play();

        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

        signInButton.setOnAction(actionEvent -> {
            gravity += .1;
            game = false;
            DBUtils.preferedX = hBox.getPrefWidth();
            DBUtils.preferedY = hBox.getPrefHeight();

            FadeTransition ft = new FadeTransition();
            ft.setDuration(Duration.millis(3000));
            ft.setNode(hBox);
            ft.setFromValue(1);
            ft.setToValue(0);
            ft.play();

            ft.setOnFinished(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {
                    DBUtils.changeScene(actionEvent,"LoggedInController.fxml","Logged In");
                    System.out.println("Action");
                }
            });
        });
    }

    private void controlButtonScale(Button button) {
        button.setOnMouseEntered(e -> {
            ScaleTransition sc = new ScaleTransition(Duration.millis(2000), button);
            sc.setToX(1.2);
            sc.setToY(1.2);
            sc.play();
        });
        button.setOnMouseExited(e -> {
            ScaleTransition sc = new ScaleTransition(Duration.millis(2000), button);
            sc.setToX(1);
            sc.setToY(1);
            sc.play();
        });
    }

    public void collision() {

            if(bottomLine.getBoundsInParent().intersects(icon.getBoundsInParent()) && game)  {
                gravity = -.3;
        }
    }
}