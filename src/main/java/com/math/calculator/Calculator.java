package com.math.calculator;

import java.util.*;

class Calculator {
    private String equation;
    private List<String> equationRPN;

    Calculator() {
        this.equation = "";
        this.equationRPN = new ArrayList<String>();
    }

    void putEquation(String equation) {
        this.equation = equation;
    }

    private String removeWhiteMarks(String equation) {
        String result = this.equation.replaceAll(",", ".");
        return result.replaceAll("\\s", "");
    }

    private int countBrackets() {
        int result = 0;
        char tmpChar;

            for (int i = 0; i < equation.length(); i++) {
                tmpChar = equation.charAt(i);
                if (tmpChar == '(') {
                    result++;
                } else if (tmpChar == ')') {
                    result--;
                }
            }
        return result;
    }

    private boolean checkBrackets() {
        int result = countBrackets();

        if (result < 0) {
            System.out.println("Error - missing open bracket '(' ");
        } else if (result > 0) {
            System.out.println( "Error - missing close bracket ')");
        }

        return result == 0;
    }

    private boolean checkLength() {
        int length = this.equation.length();

        if (length == 0) {
            System.out.println("Error - equation is empty ...");
        }

        return this.equation.length() > 0;
    }

    private boolean checkFunction() {
        char tmpChar;
        String function;

        for (int i = 0; i < this.equation.length(); i++) {
            tmpChar = equation.charAt(i);
            if (tmpChar >= 'a' && tmpChar <= 'z') {
                function = AlgorithmRPN.getFunction(i, this.equation);
                if (OperatorsAndFunction.getPriority(function) == -1) {
                    System.out.println("Function: '" + function + "' does not exists.");
                    return false;
                }
                i += function.length() - 1;
            }
        }

        return true;
    }

    String runSolver() {
        String result = "Try again ...";

        this.equation = removeWhiteMarks(this.equation);

        if (checkLength() && checkBrackets() && checkFunction()) {
            result = this.equation;
        }

        return result;
   }
}
