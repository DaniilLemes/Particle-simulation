package com.psm.particle_movements_simulation;

import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class OptionsScene {

    public static Scene createOptionsScene(Stage stage) {
        VBox layout = new VBox(15);
        layout.setPadding(new Insets(20));

        // Title label
        Label titleLabel = new Label("Simulation Options");

        // Algorithm selection
        Label algorithmLabel = new Label("Select Algorithm:");
        ToggleGroup algorithmGroup = new ToggleGroup();
        RadioButton rbEuler = new RadioButton("Euler");
        rbEuler.setToggleGroup(algorithmGroup);
        rbEuler.setSelected(true);
        RadioButton rbRK4 = new RadioButton("RK4");
        rbRK4.setToggleGroup(algorithmGroup);

        // Environment selection
        Label environmentLabel = new Label("Select Environment:");
        ComboBox<String> environmentComboBox = new ComboBox<>();
        environmentComboBox.getItems().addAll("Water", "Air", "Vacuum");
        environmentComboBox.setValue("Air");

        // Gravity option
        CheckBox gravityCheckBox = new CheckBox("Enable Gravity");
        gravityCheckBox.setSelected(true);

        // Particle weight input
        Label weightLabel = new Label("Particle Weight (kg):");
        TextField weightTextField = new TextField("1.0");

        // Optional: initial velocity input
        Label initialVelocityLabel = new Label("Initial Velocity (pixels/s):");
        TextField initialVelocityTextField = new TextField("100");

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
            double weight = 1.0;
            try {
                weight = Double.parseDouble(weightTextField.getText());
            } catch (NumberFormatException ex) {
                // Use default value if parsing fails
            }
            // Parse initial velocity
            double initVelocity = 100.0;
            try {
                initVelocity = Double.parseDouble(initialVelocityTextField.getText());
            } catch (NumberFormatException ex) {
                // Use default value if parsing fails
            }

            // Update simulation parameters accordingly.
            // These methods should be implemented in your Particle class.
            ParticleSpace.setAlgorithm(selectedAlgorithm);      // e.g., "Euler" or "RK4"
            Particle.setEnvironment(environment);            // e.g., "Water", "Air", or "Vacuum"
            Particle.setGravityEnabled(gravityEnabled);      // true/false for gravity on/off
            Particle.setDefaultWeight(weight);               // particle weight in kg
            Particle.setInitialVelocity(initVelocity);       // initial velocity of particles


            // Create a new ParticleSpace instance with the updated parameters
            ParticleSpace particleSpace = new ParticleSpace();
            // Wrap ParticleSpace in a Parent (using Group in this case)
            Group root = new Group(particleSpace);
            Scene simulationScene = new Scene(root, 800, 500);
            stage.setScene(simulationScene);
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

        Scene optionsScene = new Scene(layout, 800, 500);
        return optionsScene;
    }
}
