package com.psm.particle_movements_simulation;

public enum Algorithm {
    Euler("Euler"), RK4("RK4");
    private final String name;
    Algorithm(String name) {
        this.name = name;
    }
}
