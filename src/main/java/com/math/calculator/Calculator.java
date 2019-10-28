package com.math.calculator;

public class Calculator {
    private String equation;

    public Calculator() {
        equation = "";
    }

    public void putEquation(String equation) {
        this.equation = equation;
    }

    private String removeWhiteMarks() {
        return equation.replaceAll("\\s", "");
    }

    private int checkBrackets() {

    }

    public String runSolver() {
        String result = "";

        if ((this.equation = removeWhiteMarks()).length() == 0) {
            return "Equation is empty ...";
        }


        return this.equation;
    }
}
