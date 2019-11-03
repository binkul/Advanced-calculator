package com.math.calculator;

import java.util.*;

class RPNBuilder {

    private static String pushFunction(int start, String equation, Deque<String> stack, List<String> output) {
        String function = RPNVeryfication.getFunctionFromEquation(start, equation);

        if (RPNOperators.isFunction(function)) {
            pushOperator(function, stack, output);
        } else {
            RPNErrors.printFunctionError(function);
            return null;
        }

        return function;
    }

    private static void pushOperator(String operator, Deque<String> stack, List<String> output) {
        popOfHigherPriority(operator, stack, output);
        stack.push(operator);
    }

    private static String pushVariable(int start, String equation, List<String> output) {
        String variableString = RPNVeryfication.getVariableFromEquation(start, equation);

        if (RPNVeryfication.tryParseDouble(variableString)) {
            output.add(variableString);
        } else {
            return null;
        }

        return variableString;
    }

    private static void popOfHigherPriority(String operator, Deque<String> stack, List<String> output) {
        boolean leftSideOfNew = RPNOperators.isLeftSide(operator);
        int priorityOfNew = RPNOperators.getPriority(operator);
        int priorityOfOld;
        int maxSize = stack.size();
        String operatorFromStack;

        for (int i = 0; i < maxSize; i++) {
            operatorFromStack = stack.peek();
            priorityOfOld = RPNOperators.getPriority(operatorFromStack);

            if (leftSideOfNew) {
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

        // pop/add all operators until '('
        for (int i = 0; i < maxSize; i++) {
            operatorFromStack = stack.peek();
            if (operatorFromStack != null && !operatorFromStack.equals("(")) {
                output.add(stack.pop());
            } else {
                break;
            }
        }

        // pop '(' - if there is nothing to pop then error 'not ( bracket'
        if (stack.size() > 0) {
            stack.pop();
        } else {
            RPNErrors.printOpenBracketsError();
            return false;
        }

        // check and pop/add next, only, if it is function
        if (stack.size() > 0) {
            if (RPNOperators.isFunction(stack.peek())) {
                output.add(stack.pop());
            }
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

        if (!RPNVeryfication.checkLength(equation)) {
            return null;
        }

        while (i < equation.length()) {
            tmpChar = equation.charAt(i);
            if (RPNVeryfication.isNumber(tmpChar)) {
                if ((tmpValue = pushVariable(i, equation, output)) == null) {
                    return null;
                }
            } else if (RPNVeryfication.isLetter(tmpChar)) {
                if ((tmpValue = pushFunction(i, equation, stack, output)) == null) {
                    return null;
                }
            } else if (RPNVeryfication.isCloseBracket(tmpChar)) {
                tmpValue = String.valueOf(tmpChar);
                if (!popToOpenBracket(stack, output)) {
                    return null;
                }
            } else if (RPNVeryfication.isOpenBracket(tmpChar)) {
                tmpValue = String.valueOf(tmpChar);
                stack.push(tmpValue);
            } else if (RPNOperators.isOperator(String.valueOf(tmpChar))) {
                tmpValue = String.valueOf(tmpChar);
                pushOperator(tmpValue, stack, output);
            } else if (RPNVeryfication.isWhiteMark(tmpChar)) {
                tmpValue = String.valueOf(tmpChar);
            } else {
                RPNErrors.printOperatorError(tmpChar);
                return null;
            }
            i += tmpValue.length();
        }

        if (!popAll(stack, output)) {
            RPNErrors.printCloseBracketsError();
        }

        return output;
    }

}
