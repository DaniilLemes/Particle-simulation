module com.psm.particle_movements_simulation {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens com.psm.particle_movements_simulation to javafx.fxml;
    exports com.psm.particle_movements_simulation;
}