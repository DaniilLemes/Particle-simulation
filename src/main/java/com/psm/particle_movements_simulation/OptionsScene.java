package com.psm.particle_movements_simulation;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class OptionsScene {

    public static Scene createOptionsScene(Stage optionsStage, ParticleSpace particleSpace) {
        VBox layout = new VBox(15);
        layout.setPadding(new Insets(20));

        layout.getStyleClass().add("options-dialog");

        // Retrieve current simulation settings
        String currentAlgorithm = ParticleSpace.getAlgorithm();
        String currentEnvironment = Particle.getEnvironment();
        boolean currentGravityEnabled = Particle.isGravityEnabled();
        double currentWeight = Particle.getDefaultWeight();
        double currentInitialVelocity = Particle.getInitialVelocity();

        // Title label
        Label titleLabel = new Label("Simulation Options");

        // Algorithm selection
        Label algorithmLabel = new Label("Select Algorithm:");
        ToggleGroup algorithmGroup = new ToggleGroup();
        RadioButton rbEuler = new RadioButton("Euler");
        rbEuler.setToggleGroup(algorithmGroup);
        RadioButton rbRK4 = new RadioButton("RK4");
        rbRK4.setToggleGroup(algorithmGroup);
        // Pre-select based on current algorithm
        if (currentAlgorithm.equals("Euler")) {
            rbEuler.setSelected(true);
        } else {
            rbRK4.setSelected(true);
        }

        // Environment selection
        Label environmentLabel = new Label("Select Environment:");
        ComboBox<String> environmentComboBox = new ComboBox<>();
        environmentComboBox.getItems().addAll("Water", "Air", "Vacuum");
        environmentComboBox.setValue(currentEnvironment);

        // Gravity option
        CheckBox gravityCheckBox = new CheckBox("Enable Gravity");
        gravityCheckBox.setSelected(currentGravityEnabled);

        // Particle weight input
        Label weightLabel = new Label("Particle Weight (kg):");
        TextField weightTextField = new TextField(String.valueOf(currentWeight));

        // Initial velocity input
        Label initialVelocityLabel = new Label("Initial Velocity (pixels/s):");
        TextField initialVelocityTextField = new TextField(String.valueOf(currentInitialVelocity));

        // Apply settings button
        Button applyButton = new Button("Apply Settings");
        applyButton.setOnAction(e -> {
            // Get the selected algorithm
            String selectedAlgorithm = ((RadioButton) algorithmGroup.getSelectedToggle()).getText();
            // Get the selected environment
            String environment = environmentComboBox.getValue();
            // Get gravity flag
            boolean gravityEnabled = gravityCheckBox.isSelected();
            // Parse particle weight
            double weight = currentWeight;
            try {
                weight = Double.parseDouble(weightTextField.getText());
            } catch (NumberFormatException ex) {
                // Use current value if parsing fails
            }
            // Parse initial velocity
            double initVelocity = currentInitialVelocity;
            try {
                initVelocity = Double.parseDouble(initialVelocityTextField.getText());
            } catch (NumberFormatException ex) {
                // Use current value if parsing fails
            }

            // Update simulation parameters accordingly.
            ParticleSpace.setAlgorithm(selectedAlgorithm);  // e.g., "Euler" or "RK4"
            Particle.setEnvironment(environment);             // e.g., "Water", "Air", or "Vacuum"
            Particle.setGravityEnabled(gravityEnabled);       // true/false for gravity on/off
            Particle.setDefaultWeight(weight);                // particle weight in kg
            Particle.setInitialVelocity(initVelocity);        // initial velocity of particles

            // Restart the simulation in the existing ParticleSpace instance
            particleSpace.restart();

            // Close the options window
            optionsStage.close();
        });

        // Add all components to the layout
        layout.getChildren().addAll(
                titleLabel,
                algorithmLabel, rbEuler, rbRK4,
                environmentLabel, environmentComboBox,
                gravityCheckBox,
                weightLabel, weightTextField,
                initialVelocityLabel, initialVelocityTextField,
                applyButton
        );

        Scene optionsScene = new Scene(layout, 400, 440);
        return optionsScene;
    }
}
