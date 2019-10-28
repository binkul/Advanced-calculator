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
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < this.equation.length(); i++) {
            if (this.equation.charAt(i) > 32) {
                result.append(this.equation.charAt(i));
            }
        }
        return result.toString();
    }

    public String runSolver() {
        String result = "";

        if ((this.equation = removeWhiteMarks()).length() == 0) {
            return "Equation is empty ...";
        }


        return this.equation;
    }
}
