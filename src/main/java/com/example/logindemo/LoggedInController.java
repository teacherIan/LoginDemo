package com.example.logindemo;

import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class LoggedInController implements Initializable {

    @FXML
    private AnchorPane pane;

    @FXML
    private ImageView icon;

    @FXML
    private Line bottomLine;

    private Stage stage;

    private double xScene;
    private double yScene;

    double gravity = .00001;

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

        RotateTransition rt = new RotateTransition(Duration.seconds(2500),icon);
        rt.setByAngle(500000);

        rt.setCycleCount(Animation.INDEFINITE);
        rt.play();

        pane.setOpacity(0);
        fadeIn();

        DBUtils.draggable(pane);

        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

    }

    public void fadeIn() {
        FadeTransition ft = new FadeTransition();
        ft.setDuration(Duration.millis(1000));
        ft.setNode(pane);
        ft.setFromValue(0);
        ft.setToValue(1);
        ft.play();
    }

    public void collision() {

        if(bottomLine.getBoundsInParent().intersects(icon.getBoundsInParent()))  {
            gravity = -.4;
        }
    }

}
