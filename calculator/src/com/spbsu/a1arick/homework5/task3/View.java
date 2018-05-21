package com.spbsu.a1arick.homework5.task3;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

import java.util.Objects;

/**
 * JavaFX view for calculator
 */
public class View {
    @FXML
    private Text output;

    private boolean start = true;
    private String operator = "";
    private Controller controller = new Controller();

    @FXML
    public void processNum(ActionEvent event) {
        if (start) {
            output.setText("");
            start = false;
        }
        String value = ((Button) event.getSource()).getText();
        output.setText(output.getText() + value);
    }

    @FXML
    public void processOperator(ActionEvent event) {
        String value = ((Button) event.getSource()).getText();
        if (!Objects.equals(value, "=")) {
            if (!operator.isEmpty()) return;
            operator = value;
            controller.setValue(Long.parseLong(output.getText()));
            output.setText("");
        } else {
            if (operator.isEmpty()) return;
            output.setText(Long.toString(controller.calculate(Long.parseLong(output.getText()), operator)));
            operator = "";
            start = true;
        }
    }
}
