package com.psm.particle_movements_simulation;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Particle {
    Vector position;
    Vector velocity;
    public double mass;


    public Particle(Vector position, Vector velocity, double mass) {
        this.position = position;
        this.velocity = velocity;
        this.mass = mass;
    }

    public Vector acceleration(double t, Vector pos, Vector vel){
        //just gravity for now
        return new Vector(0, 9.81 * mass);
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

}
