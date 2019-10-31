package com.math.calculator;

import java.util.*;

class AlgorithmRPN {

    private static String getVariable(int start, String equation) {
        char tmpChar;
        StringBuilder result = new StringBuilder();

        for (int i = start; i < equation.length(); i++) {
            tmpChar = equation.charAt(i);
            if ((tmpChar >= '0' && tmpChar <= '9') || tmpChar == '.') {
                result.append(tmpChar);
            } else {
                break;
            }
        }

        return result.toString();
    }

    static String getFunction(int start, String equation) {
        char tmpChar;
        StringBuilder result = new StringBuilder();

        for (int i = start; i < equation.length(); i++) {
            tmpChar = equation.charAt(i);
            if (tmpChar >= 'a' && tmpChar <= 'z') {
                result.append(tmpChar);
            } else {
                break;
            }
        }

        return result.toString();
    }

    private static void popHigherPriority(String operator, Deque<String> stack, List<String> output) {
        int priorityOfNew = AlgorithmRPNOperators.getPriority(operator);
        int priorityOfOld;
        int maxSize = stack.size();
        String operatorFromStack;

        for (int i = 0; i < maxSize; i++) {
            operatorFromStack = stack.peek();
            priorityOfOld = AlgorithmRPNOperators.getPriority(operatorFromStack);
            if (priorityOfOld >= priorityOfNew) {
                if (operatorFromStack != null && !operatorFromStack.equals("(")) {
                    output.add(stack.pop());
                } else {
                    return;
                }
            }
        }
    }

    private static void popToOpenBracket(Deque<String> stack, List<String> output) {
        int maxSize = stack.size();
        String operatorFromStack;

        for (int i = 0; i < maxSize; i++) {
            operatorFromStack = stack.peek();
            if (operatorFromStack != null && !operatorFromStack.equals("(")) {
                output.add(stack.pop());
            } else {
                stack.pop();
                break;
            }
        }

        if (stack.size() > 0) {
            if (AlgorithmRPNOperators.isFunction(stack.peek())) {
                output.add(stack.pop());
            }
        }
    }

    private static boolean popAll(Deque<String> stack, List<String> output) {
        String operatorFromStack;

        while (stack.size() > 0) {
            operatorFromStack = stack.peek();
            if (operatorFromStack != null && !operatorFromStack.equals("(")) {
                output.add(stack.pop());
            } else {
                stack.pop();
                return false;
            }
        }

        return true;
    }

    private static void pushOperator(String operator, Deque<String> stack, List<String> output) {
        popHigherPriority(operator, stack, output);
        stack.push(operator);
    }

    private static void printUnknownError(char operator) {
        System.out.println("Unknown symbol '" + operator + "'");
    }

    private static void printBracketsError() {
        System.out.println("Error - Not equal amount of brackets.");
    }

    static List<String> buildRPN(String equation) {
        List<String> output = new ArrayList<>();
        Deque<String> stack = new ArrayDeque<>();
        char tmpChar;
        String tmpValue;
        int i = 0;

        while (i < equation.length()) {
            tmpChar = equation.charAt(i);
            if ((tmpChar >= '0' && tmpChar <= '9') || tmpChar == '.') {
                tmpValue = getVariable(i, equation);
                output.add(tmpValue);
                i += tmpValue.length() - 1;
            } else if (tmpChar >= 'a' && tmpChar <= 'z') {
                tmpValue = getFunction(i, equation);
                pushOperator(tmpValue, stack, output);
                i += tmpValue.length() - 1;
            } else if (tmpChar == '(') {
                stack.push(String.valueOf(tmpChar));
            } else if (tmpChar == ')') {
                popToOpenBracket(stack, output);
            } else if (tmpChar == '+') {
                pushOperator(String.valueOf(tmpChar), stack, output);
            } else if (tmpChar == '-') {
                pushOperator(String.valueOf(tmpChar), stack, output);
            } else if (tmpChar == '*') {
                pushOperator(String.valueOf(tmpChar), stack, output);
            } else if (tmpChar == '/') {
                pushOperator(String.valueOf(tmpChar), stack, output);
            } else if (tmpChar == '%') {
                pushOperator(String.valueOf(tmpChar), stack, output);
            } else if (tmpChar == '^') {
                pushOperator(String.valueOf(tmpChar), stack, output);
           } else {
                printUnknownError(tmpChar);
                return null;
            }
            i++;
        }

        if (!popAll(stack, output)) {
            printBracketsError();
        }

        return output;
    }
}
