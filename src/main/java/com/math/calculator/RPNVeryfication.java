package com.math.calculator;

class RPNVeryfication {
    static String getVariableFromEquation(int start, String equation) {
        char tmpChar;
        StringBuilder result = new StringBuilder();

        for (int i = start; i < equation.length(); i++) {
            tmpChar = equation.charAt(i);
            if (isNumber(tmpChar)) {
                result.append(tmpChar);
            } else {
                break;
            }
        }

        return result.toString();
    }

    static String getFunctionFromEquation(int start, String equation) {
        char tmpChar;
        StringBuilder result = new StringBuilder();

        for (int i = start; i < equation.length(); i++) {
            tmpChar = equation.charAt(i);
            if (isLetter(tmpChar)) {
                result.append(tmpChar);
            } else {
                break;
            }
        }

        return result.toString().toLowerCase();
    }

    static boolean isNumber(char value) {
        return (value >= '0' && value <= '9') || value == '.';
    }

    static boolean isLetter(char value) {
        return (value >= 'a' && value <= 'z') || (value >= 'A' && value <= 'Z');
    }

    static boolean isOpenBracket(char value) {
        return value == '(' || value == '{' || value == '[';
    }

    static boolean isCloseBracket(char value) {
        return value == ')' || value == '}' || value == ']';
    }

    static boolean isWhiteMark(char value) {
        return value <= ' ';
    }

    static boolean tryParseDouble(String value) {
        try {
            Double.parseDouble(value);
            return true;
        } catch(Exception e) {
            RPNErrors.printVariableError(value);
            return false;
        }
    }

    static boolean checkLength(String equation) {
        int length = equation.length();

        if (length == 0) {
            RPNErrors.printLengthError();
        }

        return equation.length() > 0;
    }
}
