package com.math.calculator;

class RPNErrors {
    static void printOperatorError(char operator) {
        System.out.println("Error - unknown operator '" + operator + "'");
    }

    static void printVariableError(String value) {
        System.out.println("Error - value: '" + value + "' is not a number.");
    }

    static void printLengthError() {
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

    static void printToLessVariablesError(int expected, int real) {
        System.out.println("Error - insufficient number of parameters. Expected: " + expected + ", found: " + real);
    }

    static void printDivideByZeroError() {
        System.out.println("Error - division by zero.");
    }

    static void printModuloByZeroError() {
        System.out.println("Error - Modulo by 0 is undefined.");
    }

    static void printZeroOrMinusError(String function) {
        System.out.println("Error - value for function '" + function + "' must be > 0.");
    }

    static void printMinusValueError(String function) {
        System.out.println("Error - value for function '" + function + "' must be >= 0.");
    }
}
