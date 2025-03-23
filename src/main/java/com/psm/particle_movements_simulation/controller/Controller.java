package com.psm.particle_movements_simulation.controller;

import com.psm.particle_movements_simulation.ControlPane;
import com.psm.particle_movements_simulation.ParticleSpace;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;


public class Controller {

    @FXML
    private Pane particlePane;

    @FXML
    public void initialize(){
        ParticleSpace particleSpace = new ParticleSpace();
        ControlPane controlPane = new ControlPane();
        particlePane.getChildren().addAll(particleSpace,controlPane);
    }
}