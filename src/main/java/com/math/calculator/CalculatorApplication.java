package com.math.calculator;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class CalculatorApplication {

    public static void main(String[] args) {
        String equation;
        List<String> rpnSeries;

        while (true) {
            equation = ReadEquation.putEquation();
            if (!equation.equals("stop")) {
                equation = AlgorithmRPNRules.removeWhiteMarks(equation);
                if (AlgorithmRPNRules.checkRules(equation)) {
                    rpnSeries = AlgorithmRPN.buildRPN(equation);
                    if (rpnSeries != null) {
                        for (int i = 0; i < rpnSeries.size(); i++) {
                            System.out.print(rpnSeries.get(i) + " ");
                        }
                        System.out.println();
                    }
                } else {
                    System.out.println("Try again ...");
                }
            } else {
                break;
            }
        }
    }

}
