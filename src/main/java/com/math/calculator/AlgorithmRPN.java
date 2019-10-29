package com.math.calculator;

import java.util.*;

public class AlgorithmRPN {

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

    public static String getFunction(int start, String equation) {
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

    private static void popFromStackRPN(String operator, Deque<String> stack, List<String> output) {
        int priorityOperator = OperatorsAndFunction.getPriority(operator);

        if (stack.size() == 0) {
            return;
        }

        for (int i = 0; i < stack.size(); i++) {
            if (OperatorsAndFunction.getPriority(stack.peek()) <= priorityOperator) {
                output.add(stack.pop());
            }
        }
    }

    private static List<String> changeToRPN(String equation) {
        List<String> output = new ArrayList<String>();
        Deque<String> stack = new ArrayDeque<String>();
        char tmpChar;
        String tmpValue;

        for (int i = 0; i < equation.length(); i++) {
            tmpChar = equation.charAt(i);
            if ((tmpChar >= '0' && tmpChar <= '9') || tmpChar == '.') {
                tmpValue = getVariable(i, equation);
                output.add(tmpValue);
                i += tmpValue.length() - 1;
            } else if (tmpChar >= 'a' && tmpChar <= 'z') {
                tmpValue = getFunction(i, equation);
                popFromStackRPN(tmpValue, stack, output);
                stack.push(tmpValue);
                i += tmpValue.length() - 1;
            } else if (tmpChar == '(') {

            } else if (tmpChar == ')') {

            } else if (tmpChar == '+') {

            } else if (tmpChar == '-') {

            } else if (tmpChar == '*') {

            } else if (tmpChar == '/') {

            } else if (tmpChar == '%') {

            } else if (tmpChar == '^') {

            }
        }

        return output;
    }
}
