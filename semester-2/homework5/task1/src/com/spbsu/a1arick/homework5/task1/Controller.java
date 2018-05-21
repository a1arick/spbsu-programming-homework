package com.spbsu.a1arick.homework5.task1;

        import javafx.fxml.FXML;
        import javafx.scene.control.ProgressBar;
        import javafx.scene.control.Slider;

public class Controller {
    @FXML
    private Slider slider;

    @FXML
    private ProgressBar progressBar;

    public void initialize() {
        slider.valueProperty().addListener((observable, oldValue, newValue) -> progressBar.progressProperty().setValue(newValue));
    }
}
