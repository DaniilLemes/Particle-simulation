package com.psm.particle_movements_simulation;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Application extends javafx.application.Application {
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Particles movements simulation");
        Group root = new Group();
        ParticleSpace particleSpace = new ParticleSpace();
        root.getChildren().add(particleSpace);
        primaryStage.setWidth(800);
        primaryStage.setHeight(600);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch();
    }
}