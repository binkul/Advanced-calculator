package com.math.calculator;

public class OperatorsAndFunction {
    private static final String [] OPERATORS_AND_FUNCTION = {"+", "-", "*", "/", "%", "sin", "cos", "ln", "log", "^"};
    private static final int[] OPERATORS_PRIORITY = {0, 0, 1, 1, 1, 2, 2, 2, 2, 3};

    public static int getPriority(String operator) {
        for (int i = 0; i < OPERATORS_AND_FUNCTION.length; i++) {
            if (operator.equals(OPERATORS_AND_FUNCTION[i])) {
                return OPERATORS_PRIORITY[i];
            }
        }

        return -1;
    }
}
