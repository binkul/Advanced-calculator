package com.math.calculator;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.*;

@SpringBootApplication
public class CalculatorApplication {

    private static void calculate(String equation) {
        List<String> rpnSeries;
        String equationRPN = AlgorithmRPNRules.removeWhiteMarks(equation);

        rpnSeries = AlgorithmRPN.buildRPN(equationRPN);
        if (rpnSeries != null) {
            for (String value : rpnSeries) {
                System.out.print(value + " ");
            }
            System.out.println();
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
