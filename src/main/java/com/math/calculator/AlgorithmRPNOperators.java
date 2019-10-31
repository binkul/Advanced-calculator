package com.math.calculator;

class AlgorithmRPNOperators {
    private static final String [][] OPERATORS_AND_PRIORITY = {
            {"(", "0"},
            {"+", "1"},
            {"-", "1"},
            {"*", "2"},
            {"/", "2"},
            {"%", "2"},
            {"sin", "3"},
            {"cos", "3"},
            {"ln", "3"},
            {"log", "3"},
            {"^", "4"}
    };

    static int getPriority(String operator) {
        for (String[] priority : OPERATORS_AND_PRIORITY) { // int i = 0; i < OPERATORS_AND_PRIORITY.length; i++) {
            if (operator.equals(priority[0])) {
                return Integer.parseInt(priority[1]);
            }
        }

        return -1;
    }

    static boolean isFunction(String operator) {
        return operator.length() > 1;
    }
}
