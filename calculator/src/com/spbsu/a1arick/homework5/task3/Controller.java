package com.spbsu.a1arick.homework5.task3;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

public class Controller {

    @FXML
    private Text output;
    private long num1 = 0;

    private boolean start = true;
    private String operator = "";
    private Model model = new Model();


    @FXML
    public void processNum(javafx.event.ActionEvent event) {
        if (start) {
            output.setText("");
            start = false;
        }
        String value = ((Button) event.getSource()).getText();
        output.setText(output.getText() + value);
    }

    @FXML
    public void processOperator(javafx.event.ActionEvent event) {
        String value = ((Button) event.getSource()).getText();
        if (!value.equals("=")) {
            if (!operator.isEmpty()) return;
            operator = value;
            num1 = Long.parseLong(output.getText());
            output.setText("");
        } else {
            if (operator.isEmpty()) return;
            output.setText(String.valueOf(model.calculation(num1, Long.parseLong(output.getText()), operator)));
            operator = "";
            start = true;

        }
    }
}
