package com.spbsu.a1arick.homework5.task3;

/**
 * Class for controlling calculation process
 */
public class Controller {

    private Model model = new Model();

    private long calculate(long a, long b, String operation) {
        switch (operation) {
            case "+":
                return a + b;
            case "-":
                return a - b;
            case "*":
                return a * b;
            case "/":
                return a / b;
            default:
                throw new IllegalArgumentException("Unknown operation: " + operation);
        }
    }

    public long calculate(long a, String operation) {
        long result = calculate(model.getValue(), a, operation);
        model.setValue(result);
        return result;
    }

    public void setValue(long v) {
        model.setValue(v);
    }
}
