package com.psm.particle_movements_simulation;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;


public class Particle {


    Vector position;
    Vector velocity;
    public double mass;

    // Static simulation parameters with default values
    private static String environment = "Air"; // "Water", "Air", "Vacuum"
    private static boolean gravityEnabled = true;
    private static double defaultWeight = 1.0;     // in kg
    private static double initialVelocity = 100.0; // default initial velocity (pixels/s or chosen units)


    public Particle(Vector position, Vector velocity, double mass) {
        this.position = position;
        this.velocity = velocity;
        this.mass = mass;
    }

    // Optional: Constructor using default weight and initial velocity
    public Particle(Vector position) {
        this.position = position;
        this.velocity = new Vector(initialVelocity, initialVelocity);
        this.mass = defaultWeight;
    }

    // Acceleration is based on gravity and a drag force that depends on the environment.
    public Vector acceleration(double t, Vector pos, Vector vel) {
        Vector acc = new Vector(0, 0);

        // Apply gravity if enabled (acceleration due to gravity is independent of mass)
        if (gravityEnabled) {
            acc = acc.add(new Vector(0, 9.8));
        }

        // Determine drag coefficient based on environment
        double dragCoefficient;
        switch (environment) {
            case "Water":
                dragCoefficient = 0.8;
                break;
            case "Air":
                dragCoefficient = 0.2;
                break;
            case "Vacuum":
                dragCoefficient = 0.0;
                break;
            default:
                dragCoefficient = 0.2;
                break;
        }
        // Drag force: a simple linear drag opposing the current velocity
        Vector drag = vel.multiply(-dragCoefficient);
        acc = acc.add(drag);

        return acc;
    }

    public void move_using_rk4(double t, double dt){
        //4th order Runge-Kutta
        Vector k1 = acceleration(t, position, velocity).multiply(dt);
        Vector k2 = acceleration(t + dt/2, position.add(velocity.multiply(dt/2)), velocity.add(k1.divide(2))).multiply(dt);
        Vector k3 = acceleration(t + dt/2, position.add(velocity.multiply(dt/2)), velocity.add(k2.divide(2))).multiply(dt);
        Vector k4 = acceleration(t + dt, position.add(velocity.multiply(dt)), velocity.add(k3)).multiply(dt);

        // Speed update
        Vector newVelocity = velocity.add(
                k1.add(k2.multiply(2)).add(k3.multiply(2)).add(k4).multiply(1.0 / 6)
        );


        Vector l1 = velocity.multiply(dt);
        Vector l2 = (velocity.add(k1.multiply(0.5))).multiply(dt);
        Vector l3 = (velocity.add(k2.multiply(0.5))).multiply(dt);
        Vector l4 = (velocity.add(k3)).multiply(dt);

        Vector newPosition = position.add(
                l1.add(l2.multiply(2)).add(l3.multiply(2)).add(l4).multiply(1.0 / 6)
        );

        velocity = newVelocity;
        position = newPosition;

    }

    public void move_using_euler(double t, double dt){
        Vector acceleration = acceleration(t, position, velocity);
        Vector newVelocity = velocity.add(acceleration.multiply(dt));
        Vector newPosition = position.add(newVelocity.multiply(dt));
        velocity = newVelocity;
        position = newPosition;
    }

    public void draw(GraphicsContext gc){
        gc.setFill(Color.web("ffffff"));
        gc.fillOval(position.x, position.y, 5, 5);
    }

    public Vector calculateReactionToCollisionWithCircle(Circle circle, Particle particle){
        Vector collisionNormal = particle.position.subtract(circle.position).normalize();

        Vector v_normal = collisionNormal.multiply(velocity.dot(collisionNormal));
        Vector v_tangent = velocity.subtract(v_normal);

        //for e = 0.9 (coefficient of restitution)
        return v_tangent.subtract(v_normal.multiply(0.9));
    }

    public static Vector calculateCollisionWithWall(Vector velocity, boolean vertical ) {
        double restitution = 0.9;

        if (vertical) {
            // Для вертикальной стены отражаем x-компоненту
            return new Vector(-velocity.x * restitution, velocity.y);
        } else {
            // Для горизонтальной стены отражаем y-компоненту
            return new Vector(velocity.x, -velocity.y * restitution);
        }
    }


    // Static setter for simulation environment ("Water", "Air", "Vacuum")
    public static void setEnvironment(String env) {
        environment = env;
    }

    // Static setter to enable or disable gravity
    public static void setGravityEnabled(boolean enabled) {
        gravityEnabled = enabled;
    }

    // Static setter to set the default weight of particles (in kg)
    public static void setDefaultWeight(double weight) {
        defaultWeight = weight;
    }

    // Static setter to set the initial velocity for newly created particles
    public static void setInitialVelocity(double initVel) {
        initialVelocity = initVel;
    }

}
