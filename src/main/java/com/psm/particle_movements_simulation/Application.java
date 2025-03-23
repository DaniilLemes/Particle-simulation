package com.psm.particle_movements_simulation;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

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

        // Set close event handler
        primaryStage.setOnCloseRequest((WindowEvent event) -> {
            // Perform cleanup tasks here
            System.out.println("Application is closing");
            // For example, shutdown the executor service
            ParticleSpace particleSpace = (ParticleSpace) root.lookup("#particleSpace");
            if (particleSpace != null) {
                particleSpace.shutdown();
            }
            Platform.exit();
        });
    }


    public static void main(String[] args) {
        launch();
    }
}