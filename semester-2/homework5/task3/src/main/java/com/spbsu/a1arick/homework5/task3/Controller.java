package com.spbsu.a1arick.homework5.task3;

import com.spbsu.a1arick.homework5.task3.exception.UnknownOperationException;

/**
 * Class for controlling calculation process
 */
public class Controller {

    private long value = 0;

    private long calculate(long a, long b, String operation) throws UnknownOperationException {
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
                throw new UnknownOperationException(operation);
        }
    }

    /**
     * Calculates result between argument and value stored in controller
     *
     * @param a         value
     * @param operation operation
     * @return result between argument and value stored in controller
     * @throws UnknownOperationException if operation in unknown
     */
    public long calculate(long a, String operation) throws UnknownOperationException {
        long result = calculate(value, a, operation);
        setValue(result);
        return result;
    }

    public void setValue(long v) {
        value = v;
    }
}
