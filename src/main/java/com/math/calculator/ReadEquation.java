package com.math.calculator;

import java.util.Scanner;

class ReadEquation {

    static String putEquation() {
        Scanner scanner = new Scanner(System.in);
        String result;

        System.out.println("Enter the equation ('stop' finish program):");
        result = scanner.nextLine().trim().toLowerCase();
        return result;
    }
}

