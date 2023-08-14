module com.example.events {
    requires javafx.controls;
    requires javafx.fxml;
    requires opencsv;
    requires kernel;
    requires layout;
    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;

    opens com.example.events to javafx.fxml;
    exports com.example.events;
}