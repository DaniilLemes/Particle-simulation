# Particle Movements Simulation

Particle Movements Simulation is an advanced JavaFX project designed to demonstrate complex physics interactions and numerical integration methods through a visually engaging, real-time simulation. This project is not only an educational tool for understanding particle dynamics, but also a foundation for more complex systems in computer graphics, game development, and engineering simulations.

---

## Why This Project is Important

- **Educational Value**:  
  The simulation provides a hands-on approach to learning fundamental physics concepts such as gravity, drag forces, and collision responses. It visually demonstrates the differences between numerical integration methods (Euler vs. Runge-Kutta 4) and how these impact simulation accuracy.

- **Real-Time Visualization**:  
  Real-time animation using JavaFX gives an immediate visual feedback loop for learning, making it easier to grasp complex mathematical models and physical phenomena.

- **Scalability & Performance**:  
  Implementing multi-threading with an `ExecutorService` highlights performance optimization techniques, which are crucial in simulations that require high computational efficiency.

- **Practical Application**:  
  The architecture and techniques used here lay the groundwork for more sophisticated simulations in fields such as computer graphics, robotics, virtual reality, and even aerospace engineering.

---

## What Was Implemented

### Numerical Integration Methods

- **Euler Method**:  
  A simple, first-order integration technique used for basic simulations. Although less accurate, it is computationally inexpensive and demonstrates the basics of motion update.

- **Runge-Kutta 4 (RK4) Method**:  
  A fourth-order method that offers higher accuracy in solving differential equations. This method is crucial when precision in simulation is required, showcasing the trade-off between computational complexity and accuracy.

### Collision Detection and Response

- **Static Obstacles**:  
  The simulation includes circular obstacles that particles interact with. The collision detection system computes reaction forces and repositions particles to prevent overlap, ensuring realistic motion.

- **Boundary Collisions**:  
  Particles detect collisions with canvas boundaries (vertical and horizontal walls). Upon collision, they rebound with a coefficient of restitution, simulating energy loss and realistic bouncing behavior.

### Simulation Environment & Parameters

- **Dynamic Environment Settings**:  
  Users can select between different environmental conditions (Water, Air, Vacuum). Each environment affects the drag coefficient, altering particle motion to simulate real-world resistance.

- **Gravity & Particle Properties**:  
  The simulation allows toggling gravity on/off and adjusting particle weight and initial velocity. These parameters dynamically affect the simulation, providing an interactive learning experience.

### Advanced JavaFX Features

- **FXML Integration & Controller**:  
  The user interface is built using FXML, separating the view from the logic. The Controller class manages interactions between the UI and the simulation, ensuring a clean and modular architecture.

- **Custom UI Components**:  
  The project features a custom `ControlPane` for additional UI controls and an `OptionsScene` that lets users fine-tune simulation parameters in a modal window.

- **Multi-threading**:  
  Particle updates are performed using Java’s `ExecutorService`, distributing the computation across available processor cores. This ensures smooth animations even with complex calculations and many particles.

---

## Project Structure

```
com.psm.particle_movements_simulation
├── Application.java         // Main entry point launching the JavaFX application.
├── Algorithm.java           // Enum defining available simulation algorithms (Euler, RK4).
├── Circle.java              // Represents circular obstacles with position and radius.
├── ControlPane.java         // Custom UI component for simulation controls.
├── OptionsScene.java        // Constructs the options/settings scene for runtime parameter changes.
├── Particle.java            // Contains particle physics, including movement and collision logic.
├── ParticleSpace.java       // Canvas that handles particle rendering, updating, and collision detection.
├── Vector.java              // Utility class for vector arithmetic and operations.
└── controller
└── Controller.java      // FXML controller that connects the UI to the simulation backend.
```

---

## Setup and Build Instructions

### Prerequisites

- **Java Development Kit (JDK) 11 or higher**
- **JavaFX SDK**: Download from [Gluon](https://gluonhq.com/products/javafx/) or include as Maven/Gradle dependencies.

### Steps

1. **Clone the Repository**:
   ```bash
   git clone <repository-url>
   cd <repository-directory>
   ```

2. **Configure JavaFX in Your IDE**:
    - **IntelliJ IDEA / Eclipse**: Add the JavaFX SDK libraries to your project.
    - **Maven**: Update your `pom.xml`:
      ```xml
      <dependencies>
        <dependency>
          <groupId>org.openjfx</groupId>
          <artifactId>javafx-controls</artifactId>
          <version>YOUR_JAVAFX_VERSION</version>
        </dependency>
        <dependency>
          <groupId>org.openjfx</groupId>
          <artifactId>javafx-fxml</artifactId>
          <version>YOUR_JAVAFX_VERSION</version>
        </dependency>
      </dependencies>
      ```
    - **Gradle**: Follow the [JavaFX Gradle Plugin](https://openjfx.io/openjfx-docs/#gradle) setup guide.

3. **Build the Project**:
    - In your IDE or via command line with Maven:
      ```bash
      mvn clean install
      ```

---

## Running the Application

Launch the application by running the main class `Application.java`. For command line execution:
```bash
java --module-path /path/to/javafx-sdk/lib --add-modules javafx.controls,javafx.fxml -cp target/your-jar-file.jar com.psm.particle_movements_simulation.Application
```
Alternatively, run the project directly from your IDE.

---

## Future Enhancements

- **Extended Physics Models**: Incorporate additional forces (e.g., wind, friction) and more advanced collision algorithms.
- **3D Simulation**: Extend the simulation into three dimensions using JavaFX 3D features.
- **User Interactivity**: Add real-time controls to interact with particles (e.g., adding/removing particles dynamically).
- **Performance Optimization**: Explore GPU-accelerated computations for handling larger particle systems.

---

## Contributing

Contributions to improve simulation accuracy, performance, and features are welcome. Please fork the repository and submit pull requests or open issues to discuss improvements.

---

## License

This project is licensed under the [MIT License](LICENSE).

