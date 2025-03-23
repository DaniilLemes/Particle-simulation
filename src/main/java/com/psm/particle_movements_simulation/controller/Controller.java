package com.psm.particle_movements_simulation.controller;

import com.psm.particle_movements_simulation.ParticleSpace;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;


public class Controller {

    @FXML
    private Pane particlePane;


    @FXML
    public void initialize(){
        ParticleSpace particleSpace = new ParticleSpace();
        particlePane.getChildren().add(particleSpace);
    }
}