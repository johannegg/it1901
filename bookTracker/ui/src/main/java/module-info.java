module bookTracker.ui {
    requires calc.core;
    requires javafx.controls;
    requires javafx.fxml;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.core;

    opens ui to javafx.graphics, javafx.fxml;
}
