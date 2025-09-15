package com.nhnacademy.calculator;

public class Operator {
    public static String opToString(String op){
        return switch (op){
            case "add" -> "+";
            case "sub" -> "-";
            case "mul" -> "×";
            case "div" -> "÷";
            case "sqrt" -> "√";
            case "pow" -> "^";
            case "mod" -> "%";
            case "reciprocal" -> "/";
            default -> throw new IllegalStateException("Unexpected value: " + op);
        };
    }
}
