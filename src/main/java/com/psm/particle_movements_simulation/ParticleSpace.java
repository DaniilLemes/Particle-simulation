package com.psm.particle_movements_simulation;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class ParticleSpace extends Canvas {

    public ParticleSpace() {
        super(800, 500);
        this.getStyleClass().add("particle-space");
        GraphicsContext gc = this.getGraphicsContext2D();
        gc.setFill(Color.web("424242"));
        getGraphicsContext2D().fillRect(0, 0, 800, 500);
    }
}

