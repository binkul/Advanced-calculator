package com.math.calculator;

class AlgorithmRPNRules {
    static String removeWhiteMarks(String equation) {
        String result = equation.replaceAll(",", ".");
        return result.replaceAll("\\s", "");
    }

    private static int countBrackets(String equation) {
        int result;
        char tmpChar;

        result = 0;
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

    private static boolean checkBrackets(String equation) {
        int result = countBrackets(equation);

        if (result < 0) {
            System.out.println("Error - missing open bracket '(' ");
        } else if (result > 0) {
            System.out.println( "Error - missing close bracket ')");
        }

        return result == 0;
    }

    private static boolean checkLength(String equation) {
        int length = equation.length();

        if (length == 0) {
            System.out.println("Error - equation is empty ...");
        }

        return equation.length() > 0;
    }

    private static boolean checkFunction(String equation) {
        char tmpChar;
        String function;
        int i = 0;

        while (i < equation.length()) {
            tmpChar = equation.charAt(i);
            if (tmpChar >= 'a' && tmpChar <= 'z') {
                function = AlgorithmRPN.getFunction(i, equation);
                if (AlgorithmRPNOperators.getPriority(function) == -1) {
                    System.out.println("Function: '" + function + "' does not exists.");
                    return false;
                }
                i += function.length() - 1;
            }
            i++;
        }

        return true;
    }

    static boolean checkRules(String equation) {
        return checkLength(equation) && checkBrackets(equation) && checkFunction(equation);
    }
}
