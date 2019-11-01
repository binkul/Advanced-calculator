package com.math.calculator;

class AlgorithmRPNRules {
    static String removeWhiteMarks(String equation) {
        String result = equation.replaceAll(",", ".");
        return result.replaceAll("\\s", "");
    }

    static boolean tryParseDouble(String value) {
        try {
            Double.parseDouble(value);
            return true;
        } catch(Exception e) {
            printVariableError(value);
            return false;
        }
    }

    static boolean checkLength(String equation) {
        int length = equation.length();

        if (length == 0) {
            printLengthError();
        }

        return equation.length() > 0;
    }

    static void printOperatorError(char operator) {
        System.out.println("Error - unknown operator '" + operator + "'");
    }

    private static void printVariableError(String value) {
        System.out.println("Error - value: '" + value + "' is not a number.");
    }

    private static void printLengthError() {
        System.out.println("Error - equation is empty ...");
    }

    static void printFunctionError(String function) {
        System.out.println("Error - function: '" + function + "' does not exists.");
    }

    static void printCloseBracketsError() {
        System.out.println("Error - missing one or more closing brackets ')' ");
    }

    static void printOpenBracketsError() {
        System.out.println("Error - missing one or more opening brackets '(' ");
    }
}
