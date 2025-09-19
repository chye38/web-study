package com.nhnacademy.student.util;

import com.nhnacademy.student.domain.Gender;
import java.util.Objects;

public class Invalid {
    public static void invalid(String id, String name, Gender gender, Integer age){
        if(Objects.isNull(id) || Objects.isNull(name) || Objects.isNull(gender) || Objects.isNull(age)){
            throw new RuntimeException("id, name, gender, age 다시 확인");
        }

        if(age < 1){
            throw new IllegalArgumentException("나이는 1살 이상이어야 합니다");
        }
    }
}
