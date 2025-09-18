package com.nhnacademy.student.domain;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Student {
    private String id;
    private String name;
    private Gender gender;
    private int age;
    private LocalDateTime createdAt = LocalDateTime.now();
    private String dateTimeFormatted = getDateTimeFormatted();

    public Student(){}
    public Student(String id, String name, Gender gender, int age){
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.age = age;
    }
    public String getDateTimeFormatted(){
        if(Objects.isNull(createdAt)) return "";

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        dateTimeFormatted = formatter.format(createdAt);
        return dateTimeFormatted;
    }
}
