package com.math.calculator;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.*;

@SpringBootApplication
public class CalculatorApplication {

    private static void calculate(String equation) {
        List<String> rpnSeries;
        Double result;

        if ((rpnSeries = RPNBuilder.buildRPN(equation)) == null) {
            System.out.println("Try again ...");
            return;
        }

        if ((result = RPNCalculator.calculateRPN(rpnSeries)) != null) {
            System.out.println(equation + " = " + result);
        } else {
            System.out.println("Try again ...");
        }
    }

    public static void main(String[] args) {
        String equation;

        while (true) {
            equation = ReadEquation.putEquation();
            if (!equation.equals("stop")) {
                calculate(equation);
            } else {
                break;
            }
        }
    }

}
