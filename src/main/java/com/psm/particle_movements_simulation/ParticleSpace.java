package com.psm.particle_movements_simulation;

import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ParticleSpace extends Canvas {

    private final Circle[] circles = new Circle[]{
            new Circle(new Vector(390, 200), 50),
            new Circle(new Vector(0, 0), 45),
            new Circle(new Vector(700, 0), 40),
            new Circle(new Vector(0, 350), 50),

    };

    public final ExecutorService executorService = Executors.newFixedThreadPool(
            Runtime.getRuntime().availableProcessors()
    );

    ArrayList<Particle> particles;
    private static final int ParticleCount = 5;

    public ParticleSpace() {
        super(800, 500);
        this.getStyleClass().add("particle-space");
        initParticles();

        // Запуск автоматического обновления частиц через AnimationTimer
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                updateParticles();
            }
        };
        timer.start();
    }

    public void initParticles(){
        GraphicsContext gc = this.getGraphicsContext2D();
        gc.setFill(Color.web("424242"));
        gc.fillRect(0, 0, getWidth(), getHeight());

        for (Circle circle : circles) {
            gc.setFill(Color.web("ffffff"));
            gc.fillOval(circle.position.x, circle.position.y, circle.radius * 2, circle.radius * 2);
        }

        particles = new ArrayList<>(ParticleCount);
        for (int i = 0; i < ParticleCount; i++) {
            particles.add(new Particle(
                    new Vector(Math.random() * getWidth(), Math.random() * getHeight()),
                    new Vector(Math.random() * 100, Math.random() * 100),
                    1
            ));
        }

        for (Particle particle : particles) {
            particle.draw(gc);
        }
    }

    public void updateParticles(){
        List<Callable<Void>> tasks = new ArrayList<>();
        for (Particle particle : particles) {
            tasks.add(() -> {
                Circle circle = checkCollisionWithCircles(particle);
                if (circle != null){
                    particle.velocity = particle.calculateReactionToCollisionWithCircle(circle, particle);
                }
                if (checkCollisionWithVerticalWalls(particle)){
                    particle.velocity = Particle.calculateCollisionWithWall(particle.velocity, true);
                }
                if (checkCollisionWithHorizontalWalls(particle)){
                    particle.velocity = Particle.calculateCollisionWithWall(particle.velocity, false);
                }
                particle.move_using_rk4(0, 0.1);
                return null;
            });
        }

        try{
            executorService.invokeAll(tasks);
        }
        catch (InterruptedException e){
            System.err.println("Error: " + e.getMessage());
            Thread.currentThread().interrupt();
        }

        Platform.runLater(() -> {
            GraphicsContext gc = this.getGraphicsContext2D();
            // Clear the canvas
            gc.setFill(Color.web("424242"));
            gc.fillRect(0, 0, getWidth(), getHeight());
            // Redraw static elements
            for (Circle circle : circles) {
                gc.setFill(Color.web("ffffff"));
                gc.fillOval(circle.position.x, circle.position.y, circle.radius * 2, circle.radius * 2);
            }
            // Draw updated particles
            for (Particle particle : particles) {
                particle.draw(gc);
            }
        });

    }

    public Circle checkCollisionWithCircles(Particle particle){
        for (Circle circle : circles) {
            double distance = particle.position.subtract(circle.position).length();
            if (distance < circle.radius){
                return circle;
            }
        }
        return null;
    }

    public boolean checkCollisionWithVerticalWalls(Particle particle){
        return particle.position.x <= 0 || particle.position.x >= getWidth();
    }

    public boolean checkCollisionWithHorizontalWalls(Particle particle){
        return particle.position.y <= 0 || particle.position.y >= getHeight();
    }

    // Shutdown the executor service when appropriate (e.g., on application exit)
    public void shutdown() {
        executorService.shutdown();
    }
}

