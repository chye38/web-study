package com.nhnacademy.util;

import com.nhnacademy.PaymentMethod;
import com.nhnacademy.Result;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;

public class InputValidator {
    public static int getMenu(Scanner sc){
        try{
            int input = sc.nextInt(); sc.nextLine();
            return input;
        } catch (Exception e) {
            throw new InputMismatchException("숫자를 입력하세요 : ");
        }
    }

    public static int getIntInput(Scanner sc, String prompt){
        System.out.print(prompt + "(을)를 입력하세요 : ");
        int input = sc.nextInt(); sc.nextLine(); // 버퍼 무시

        if(input < 1) {
            throw new IllegalArgumentException(prompt + "는 0보다 커야합니다");
        }

        return input;
    }

    public static LocalDateTime getDateTimeInput(Scanner sc, String prompt){
        System.out.print(prompt + " 날짜 및 시간을 입력하세요 (양식 : yyyy-MM-dd HH:mm:ss) : ");
        String input = sc.nextLine();
        try{
            return LocalDateTime.parse(input, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        }catch (DateTimeParseException e){
            throw new IllegalArgumentException("잘못된 날짜/시간 형식입니다");
        }
    }

    public static void comparisonDateTime(LocalDateTime startedAt, LocalDateTime endedAt){
        // 시작 시간이 종료 시간보다 이후이면 오류 던지기
        if(startedAt.isAfter(endedAt)){
            throw new IllegalArgumentException("시작시간보다 종료시간이 빠릅니다");
        }
    }

    public static <T extends Enum<T>> T getEnumInput(Scanner sc, Class<T> enumClass){
        T[] enumValues = enumClass.getEnumConstants();
        System.out.print("결과를 선택해주세요");

        for (int i = 1; i <= enumValues.length; i++) {
            System.out.printf("%d. %s\n", i, enumValues[i - 1]);
        }

        System.out.print("입력 : ");
        Integer input = sc.nextInt(); sc.nextLine(); // 버퍼 무시
        if(Objects.isNull(input) || (input - 1 < 0) || (enumValues.length < input)){
            throw new IllegalArgumentException("잘못된 값 입력");
        }

        return enumValues[input - 1];
    }

    public static String getStringInput(Scanner sc, String prompt){
        System.out.print(prompt + "(을)를 입력하세요 : ");
        String str = sc.nextLine();

        return str;
    }

}
