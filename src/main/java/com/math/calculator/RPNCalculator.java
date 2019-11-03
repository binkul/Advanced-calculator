package com.math.calculator;

import java.util.*;
import java.math.*;

public class RPNCalculator {

    private static Double calcAddition(Deque<Double> stack) {
        Double result;
        Double a;
        Double b;

        if (stack.size() >= 2) {
            a = stack.pop();
            b = stack.pop();
            result = b+a;
        } else {
            RPNErrors.printToLessVariablesError(2, stack.size());
            result = null;
        }
        return result;
    }

    private static Double calcSubtraction(Deque<Double> stack) {
        Double result;
        Double a;
        Double b;

        if (stack.size() >= 2) {
            a = stack.pop();
            b = stack.pop();
            result = b-a;
        } else {
            RPNErrors.printToLessVariablesError(2, stack.size());
            result = null;
        }
        return result;
    }

    private static Double calcMultiply(Deque<Double> stack) {
        Double result;
        Double a;
        Double b;

        if (stack.size() >= 2) {
            a = stack.pop();
            b = stack.pop();
            result = a*b;
        } else {
            RPNErrors.printToLessVariablesError(2, stack.size());
            result = null;
        }
        return result;
    }

    private static Double calcDivision(Deque<Double> stack) {
        Double result;
        Double a;
        Double b;

        if (stack.size() >= 2) {
            a = stack.pop();
            b = stack.pop();
            if (a != 0) {
                result = b / a;
            } else {
                RPNErrors.printDivideByZeroError();
                result = null;
            }
        } else {
            RPNErrors.printToLessVariablesError(2, stack.size());
            result = null;
        }
        return result;
    }

    private static Double calcModulo(Deque<Double> stack) {
        Double result;
        Double a;
        Double b;

        if (stack.size() >= 2) {
            a = stack.pop();
            b = stack.pop();
            if (a != 0) {
                result = b % a;
            } else {
                RPNErrors.printModuloByZeroError();
                result = null;
            }
        } else {
            RPNErrors.printToLessVariablesError(2, stack.size());
            result = null;
        }
        return result;
    }

    private static Double calcExponentiation(Deque<Double> stack) {
        Double result;
        Double a;
        Double b;

        if (stack.size() >= 2) {
            a = stack.pop();
            b = stack.pop();
            result = Math.pow(b, a);
        } else {
            RPNErrors.printToLessVariablesError(2, stack.size());
            result = null;
        }
        return result;
    }

    private static Double calcSin(Deque<Double> stack) {
        Double result;
        Double a;

        if (stack.size() >= 1) {
            a = stack.pop();
            result = Math.sin(a);
        } else {
            RPNErrors.printToLessVariablesError(1, 0);
            result = null;
        }
        return result;
    }

    private static Double calcCos(Deque<Double> stack) {
        Double result;
        Double a;

        if (stack.size() >= 1) {
            a = stack.pop();
            result = Math.cos(a);
        } else {
            RPNErrors.printToLessVariablesError(1, 0);
            result = null;
        }
        return result;
    }

    private static Double calcLn(Deque<Double> stack) {
        Double result;
        Double a;

        if (stack.size() >= 1) {
            a = stack.pop();
            if (a > 0) {
                result = Math.log(a);
            } else {
                RPNErrors.printZeroOrMinusError("Ln(x)");
                result = null;
            }
        } else {
            RPNErrors.printToLessVariablesError(1, 0);
            result = null;
        }
        return result;
    }

    private static Double calcLog(Deque<Double> stack) {
        Double result;
        Double a;

        if (stack.size() >= 1) {
            a = stack.pop();
            if (a > 0) {
                result = Math.log10(a);
            } else {
                RPNErrors.printZeroOrMinusError("Log10");
                result = null;
            }
        } else {
            RPNErrors.printToLessVariablesError(1, 0);
            result = null;
        }
        return result;
    }

    private static Double calcSqr(Deque<Double> stack) {
        Double result;
        Double a;

        if (stack.size() >= 1) {
            a = stack.pop();
            if (a >= 0) {
                result = Math.sqrt(a);
            } else {
                RPNErrors.printMinusValueError("Square(x)");
                result = null;
            }
        } else {
            RPNErrors.printToLessVariablesError(1, 0);
            result = null;
        }
        return result;
    }

    private static Double piConst() {
        return 3.1416;
    }

    private static Double eConst() {
        return 2.718281828;
    }

    static Double calculateRPN(List<String> rpnSeries) {
        Deque<Double> stack = new ArrayDeque<>();
        Double result = null;
        String value;

        for (int i = 0; i < rpnSeries.size(); i++) {

            value = rpnSeries.get(i);
            if (RPNVeryfication.isNumber(value.charAt(0))) {
                result = Double.valueOf(value);
            } else {
                if (value.equals("+")) {
                    result = calcAddition(stack);
                } else if (value.equals("-")) {
                    result = calcSubtraction(stack);
                } else if (value.equals("*")) {
                    result = calcMultiply(stack);
                } else if (value.equals("/")) {
                    result = calcDivision(stack);
                } else if (value.equals("%")) {
                    result = calcModulo(stack);
                } else if (value.equals("^")) {
                    result = calcExponentiation(stack);
                } else if (value.equals("sin")) {
                    result = calcSin(stack);
                } else if (value.equals("cos")) {
                    result = calcCos(stack);
                } else if (value.equals("ln")) {
                    result = calcLn(stack);
                } else if (value.equals("log") || value.equals("lg")) {
                    result = calcLog(stack);
                } else if (value.equals("sqr")){
                    result = calcSqr(stack);
                } else if (value.equals("pi")) {
                    result = piConst();
                } else if (value.equals("e")) {
                    result = eConst();
                }
            }

            if (result != null) {
                stack.push(result);
            } else {
                return null;
            }
        }

        return stack.size() == 1 ? stack.pop(): null;
    }
}
