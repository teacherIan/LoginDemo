package com.example.logindemo;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Objects;

public class DBUtils {

        static double stageX, stageY;
        static double preferedX, preferedY;
        static Scene currentScene = null;
        static Stage currentStage = null;

        public static void changeScene(ActionEvent event, String fxmlFile, String title ) {

                Parent root = null;
                FXMLLoader loader = null;

                        try {
                                root = FXMLLoader.load(Objects.requireNonNull(DBUtils.class.getResource(fxmlFile)));
                        } catch (IOException e) {
                                e.printStackTrace();
                        }

                Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                        currentStage = stage;
                assert root != null;
                Scene scene = new Scene(root);
                scene.setFill(Color.TRANSPARENT);
                stage.setScene(scene);
                stage.setTitle(title);
                currentScene = scene;
                stage.show();

        }

        public static void draggable(Node parent) {
                parent.setOnMousePressed(e -> {
                        stageX = e.getX();
                        stageY = e.getY();
                });

                parent.setOnMouseDragged(e -> {
                        currentStage.setX(e.getScreenX() - stageX);
                        currentStage.setY(e.getScreenY() - stageY);
                });
        }



}
