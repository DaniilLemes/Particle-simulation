package com.psm.particle_movements_simulation.controller;

import com.psm.particle_movements_simulation.ControlPane;
import com.psm.particle_movements_simulation.OptionsScene;
import com.psm.particle_movements_simulation.ParticleSpace;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Controller {
    @FXML
    private Pane particlePane;

    // Keep a reference to the ParticleSpace instance
    private ParticleSpace particleSpace;

    @FXML
    public void initialize(){
        particleSpace = new ParticleSpace();
        ControlPane controlPane = new ControlPane();
        particlePane.getChildren().addAll(particleSpace, controlPane);
    }

    @FXML
    public void showOptions(){
        Stage optionsStage = new Stage();
        optionsStage.initModality(Modality.APPLICATION_MODAL); // Makes it modal relative to the main window
        optionsStage.setTitle("Simulation Options");

        // Pass the current ParticleSpace instance to OptionsScene
        Scene optionsScene = OptionsScene.createOptionsScene(optionsStage, particleSpace);
        optionsStage.setScene(optionsScene);
        optionsStage.showAndWait();
    }
}
