package com.psm.particle_movements_simulation;

import javafx.scene.canvas.Canvas;
import javafx.scene.shape.Rectangle;

public class ParticleSpace extends Canvas {

    public ParticleSpace() {
        super(800, 500);
        Rectangle rectangle = new Rectangle(0, 0, 800, 500);
        rectangle.setStyle("-fx-fill: white; -fx-stroke: black; -fx-stroke-width: 1;");
        getGraphicsContext2D().fillRect(0, 0, 800, 500);
    }
}

