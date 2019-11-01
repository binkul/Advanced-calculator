package com.math.calculator;

import java.util.*;

class AlgorithmRPN {

    private static String getVariableFromEquation(int start, String equation) {
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

    private static String getFunctionFromEquation(int start, String equation) {
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

    private static String pushFunction(int start, String equation, Deque<String> stack, List<String> output) {
        String function = getFunctionFromEquation(start, equation);

        if (AlgorithmRPNOperators.getOperatorData(function) != null) {
            pushOperator(function, stack, output);
        } else {
            AlgorithmRPNRules.printFunctionError(function);
            return null;
        }

        return function;
    }

    private static void pushOperator(String operator, Deque<String> stack, List<String> output) {
        popOfHigherPriority(operator, stack, output);
        stack.push(operator);
    }

    private static String pushVariable(int start, String equation, List<String> output) {
        String variableString = getVariableFromEquation(start, equation);

        if (AlgorithmRPNRules.tryParseDouble(variableString)) {
            output.add(variableString);
        } else {
            return null;
        }

        return variableString;
    }

    private static void popOfHigherPriority(String operator, Deque<String> stack, List<String> output) {
        int priorityOfNew = AlgorithmRPNOperators.getPriority(operator);
        int priorityOfOld;
        int maxSize = stack.size();
        String operatorFromStack;

        for (int i = 0; i < maxSize; i++) {
            operatorFromStack = stack.peek();
            priorityOfOld = AlgorithmRPNOperators.getPriority(operatorFromStack);

            if (AlgorithmRPNOperators.isLeftSide(operator)) {
                if (priorityOfOld >= priorityOfNew) {
                    output.add(stack.pop());
                }
            } else {
                if (priorityOfOld > priorityOfNew) {
                    output.add(stack.pop());
                }
            }
        }
    }

    private static boolean popToOpenBracket(Deque<String> stack, List<String> output) {
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
        } else {
            AlgorithmRPNRules.printOpenBracketsError();
            return false;
        }

        return true;
    }

    private static boolean popAll(Deque<String> stack, List<String> output) {
        String operatorFromStack;

        while (stack.size() > 0) {
            operatorFromStack = stack.peek();
            if (operatorFromStack != null && !operatorFromStack.equals("(")) {
                output.add(stack.pop());
            } else {
                return false;
            }
        }

        return true;
    }

    static List<String> buildRPN(String equation) {
        List<String> output = new ArrayList<>();
        Deque<String> stack = new ArrayDeque<>();
        char tmpChar;
        String tmpValue;
        int i = 0;

        if (!AlgorithmRPNRules.checkLength(equation)) {
            return null;
        }

        while (i < equation.length()) {
            tmpChar = equation.charAt(i);
            if ((tmpChar >= '0' && tmpChar <= '9') || tmpChar == '.') {
                if ((tmpValue = pushVariable(i, equation, output)) == null) {
                    return null;
                }
                i += tmpValue.length() - 1;
            } else if (tmpChar >= 'a' && tmpChar <= 'z') {
                if ((tmpValue = pushFunction(i, equation, stack, output)) == null) {
                    return null;
                }
                i += tmpValue.length() - 1;
            } else if (tmpChar == ')') {
                if (!popToOpenBracket(stack, output)) {
                    return null;
                }
            } else if (tmpChar == '(') {
                stack.push(String.valueOf(tmpChar));
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
                AlgorithmRPNRules.printOperatorError(tmpChar);
                return null;
            }
            i++;
        }

        if (!popAll(stack, output)) {
            AlgorithmRPNRules.printCloseBracketsError();
        }

        return output;
    }
}
