package sample;

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class Controller {
    @FXML
    private TextField result;

    @FXML
    private ChoiceBox<String> operation;

    @FXML
    private Spinner<Integer> firstOperand;

    @FXML
    private Spinner<Integer> secondOperand;

    private void calculate() {
        double result = 0;
        int firstOperand = this.firstOperand.getValue();
        int secondOperand = this.secondOperand.getValue();
        switch (operation.getValue()) {
            case "+":
                result = firstOperand + secondOperand;
                break;
            case "-":
                result = firstOperand - secondOperand;
                break;
            case "*":
                result = firstOperand * secondOperand;
                break;
            case "/":
                result = (double) firstOperand / secondOperand;
        }
        this.result.textProperty().setValue(Double.toString(result));
    }

    public void initialize() {
        operation.getItems().addAll("+", "-", "*", "/");
        operation.valueProperty().setValue("+");

        operation.valueProperty().addListener((observable, oldValue, newValue) -> calculate());

        firstOperand.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(-100, 100, 0, 1));
        secondOperand.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(-100, 100, 0, 1));

        firstOperand.valueProperty().addListener((observable, oldValue, newValue) -> calculate());
        secondOperand.valueProperty().addListener((observable, oldValue, newValue) -> calculate());
    }
}
