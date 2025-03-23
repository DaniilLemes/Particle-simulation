package com.psm.particle_movements_simulation;

public class Vector {
    public double x, y;

    public Vector(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Vector add(Vector other) {
        return new Vector(this.x + other.x, this.y + other.y);
    }

    public Vector subtract(Vector other) {
        return new Vector(this.x - other.x, this.y - other.y);
    }

    public Vector multiply(double scalar) {
        return new Vector(this.x * scalar, this.y * scalar);
    }

    public Vector divide(double scalar) {
        return new Vector(this.x / scalar, this.y / scalar);
    }

    public double dot(Vector other) {
        return this.x * other.x + this.y * other.y;
    }

    public double length() {
        return Math.sqrt(x * x + y * y);
    }

    public Vector normalize() {
        double len = length();
        return len == 0 ? new Vector(0, 0) : divide(len);
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y +")";
    }
}