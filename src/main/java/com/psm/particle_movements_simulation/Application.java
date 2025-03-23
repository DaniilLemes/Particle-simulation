package com.psm.particle_movements_simulation;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Application extends javafx.application.Application {
    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("view.fxml"));
        primaryStage.setTitle("Particles movements simulation");
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, 800, 600);
        scene.getStylesheets().add(Objects.requireNonNull(Application.class.getResource("/style.css")).toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch();
    }
}