package com.spbsu.a1arick.homework2.task3.calculator;

import com.spbsu.a1arick.homework2.task3.exception.DataStructureIsEmptyException;
import com.spbsu.a1arick.homework2.task3.stack.Stack;

/**
 * Calculator class
 */
public class Calculator {
    /**
     * Computes expression result
     *
     * @param s     string with expression
     * @param stack stack to use
     * @return result of computation
     */
    public static int solve(String s, Stack stack) {
        String[] strs = s.split(" ");
        Node[] nodes = new Node[strs.length];
        for (int i = 0; i < nodes.length; i++) {
            String str = strs[i];
            if (str.length() == 1 && Node.isOperation(str.charAt(0))) {
                nodes[i] = new Node(str.charAt(0));
            } else {
                nodes[i] = new Node(Integer.valueOf(str));
            }
        }
        try {
            return solve(nodes, stack);
        } catch (DataStructureIsEmptyException e) {
            throw new IllegalStateException("Something is wrong", e);
        }
    }


    private static int solve(Node[] nodes, Stack stack) throws DataStructureIsEmptyException {
        for (int i = 0; i < nodes.length; i++) {
            Node node = nodes[i];
            if (node.isOperation()) {
                int number1 = stack.pop();
                int number2 = stack.pop();

                switch (node.getOperation()) {
                    case '*':
                        stack.push(number1 * number2);
                        break;
                    case '+':
                        stack.push(number1 + number2);
                        break;
                    case '-':
                        stack.push(number2 - number1);
                        break;
                    case '/':
                        stack.push(number2 / number1);
                        break;
                }
            } else {
                stack.push(node.getNumber());
            }
        }
        return stack.pop();
    }

}
