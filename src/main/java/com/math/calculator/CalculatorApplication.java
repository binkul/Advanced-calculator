package com.math.calculator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CalculatorApplication {

    public static void main(String[] args) {
        String equation;
        Calculator calculator = new Calculator();

        while (true) {
            equation = ReadEquation.putEquation();
            if (equation.equals("stop")) {
                break;
            }
            calculator.putEquation(equation);
            System.out.println(calculator.runSolver());
        }
    }

}
