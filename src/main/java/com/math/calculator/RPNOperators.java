package com.math.calculator;

enum RPNOperators {
    BRACKET("(", 0, "B", "L"),
    ADD("+", 1, "O", "L"),
    SUB("-", 1, "O", "L"),
    MUL("*", 2, "O", "L"),
    DIV("/", 2, "O", "L"),
    MOD("%", 2, "O", "L"),
    SIN("sin", 3, "F", "L"),
    COS("cos", 3, "F", "L"),
    LN("ln", 3, "F", "L"),
    LOG("log", 3, "F", "L"),
    LG("lg", 3, "F", "L"),
    SQR("sqr", 3, "F", "L"),
    SUP("^", 4, "O", "R"),
    CONSTE("e", 5, "F", "L"),
    CONSTPI("pi", 5, "F", "L");



    final String symbol;
    final Integer priority;
    final String funOrOperator;
    final String leftOrRight;

    RPNOperators(String symbol, Integer priority, String funOrOperator, String leftOrRight) {
        this.symbol = symbol;
        this.priority = priority;
        this.funOrOperator = funOrOperator;
        this.leftOrRight = leftOrRight;
    }

    static RPNOperators isExist(String symbol) {
        for (RPNOperators operator : RPNOperators.values()) {
            if (symbol.equals(operator.symbol)) {
                return operator;
            }
        }
        return null;
    }

    static Integer getPriority(String symbol) {
        for (RPNOperators operator : RPNOperators.values()) {
            if (symbol.equals(operator.symbol)) {
                return operator.priority;
            }
        }
        return -1;
    }

    static boolean isFunction(String symbol) {
        for (RPNOperators operator : RPNOperators.values()) {
            if (symbol.equals(operator.symbol)) {
                return operator.funOrOperator.equals("F");
            }
        }
        return false;
    }

    static boolean isOperator(String symbol) {
        for (RPNOperators operator : RPNOperators.values()) {
            if (symbol.equals(operator.symbol)) {
                return operator.funOrOperator.equals("O");
            }
        }
        return false;
    }

    static boolean isLeftSide(String symbol) {
        for (RPNOperators operator : RPNOperators.values()) {
            if (symbol.equals(operator.symbol)) {
                return operator.leftOrRight.equals("L");
            }
        }
        return false;
    }
}

//class RPNOperators {
    /*
    Data for Operators:
    [0] - Symbol of operator/function
    [1] - Priority/Function or Operator/Left-sided or Right-sided
    O - operator; F - function; B - bracket
    '(' must have the lowest priority, constant e.g. pi, e must have highest priority
     */
//    private static final String [][] OPERATORS_AND_PRIORITY = {
//            {"(", "0/B/L"},
//            {"+", "1/O/L"},
//            {"-", "1/O/L"},
//            {"*", "2/O/L"},
//            {"/", "2/O/L"},
//            {"%", "2/O/L"},
//            {"sin", "3/F/L"},
//            {"cos", "3/F/L"},
//            {"ln", "3/F/L"},
//            {"log", "3/F/L"},
//            {"lg", "3/F/L"},
//            {"sqr", "3/F/L"},
//            {"^", "4/O/R"},
//            {"e", "5/F/L"},
//            {"pi", "5/F/L"}
//    };

    /*
    Return data for variable operator, or null if not found
     */
//    private static String getOperatorData(String operator) {
//        for (String[] data : OPERATORS_AND_PRIORITY) {
//            if (operator.equals((data[0]))) {
//                return data[1];
//            }
//        }
//        return null;
//    }

//    static int getPriority(String operator) {
//        String operatorData = getOperatorData(operator);

//        if (operatorData != null) {
//            String[] data = operatorData.split("/", 0);
//            return  Integer.parseInt(data[0]);
//        } else {
//            return -1;
//        }
//    }

//    static boolean isFunction(String operator) {
//        String operatorData = getOperatorData(operator);

//        if (operatorData != null) {
//            String[] data = operatorData.split("/", 0);
//            return data[1].equals("F");
//        } else {
//            return false;
//        }
//    }

//    static boolean isOperator(String operator) {
//        String operatorData = getOperatorData(operator);

//        if (operatorData != null) {
//            String[] data = operatorData.split("/", 0);
//            return data[1].equals("O");
//        } else {
//            return false;
//        }
//    }

//    static boolean isLeftSide(String operator) {
//        String operatorData = getOperatorData(operator);

//        if (operatorData != null) {
//            String[] data = operatorData.split("/", 0);
//            return data[2].equals("L");
//        } else {
//            return false;
//        }
//    }
//}
