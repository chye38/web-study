package com.nhnacademy.calculator;

import org.jsoup.internal.StringUtil;

public class Calculator {
    public static double Calculator(String num1, String num2, String op){
        if(StringUtil.isBlank(op)){
            throw new IllegalArgumentException("oper is null");
        }

        double n1 = 0, n2 = 0;

        // 제곱근 또는 역수는 숫자 하나만 필요
        if("sqrt".equals(op) || "reciprocal".equals(op)) {
            if(StringUtil.isBlank(num1)){
                throw new IllegalArgumentException("num1 is null");
            }
            n1 = Double.parseDouble(num1);
        } else {
            // 일반 사칙 연산, pow, mod
            if(StringUtil.isBlank(num1) || StringUtil.isBlank(num2)){
                throw new IllegalArgumentException("num1 or num2 is null");
            }
            n1 = Double.parseDouble(num1);
            n2 = Double.parseDouble(num2);
        }

        return switch (op){
            case "add" -> n1 + n2;
            case "sub" -> n1 - n2;
            case "mul" -> n1 * n2;
            case "div" -> {
                if(n2 == 0){
                    throw new ArithmeticException("0으로 나눌 수 없습니다");
                }
                yield  n1 / n2;
            }
            case "sqrt" -> Math.sqrt(n1);
            case "pow" -> Math.pow(n1, n2);
            case "mod" -> {
                if(n2 == 0){
                    throw new ArithmeticException("0으로 나눌 수 없습니다");
                }
                yield  n1 % n2;
            }
            case "reciprocal" -> {
                if(n1 == 0){
                    throw new ArithmeticException("0으로 나눌 수 없습니다");
                }
                yield  1 / n1;
            }
            default -> throw new IllegalStateException("Unexpected value: " + op);
        };

    }
}
