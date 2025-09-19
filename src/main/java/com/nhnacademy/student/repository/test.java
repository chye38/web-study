package com.nhnacademy.student.repository;

public class test {
     public static void main(String[] args){
         String path = test.class.getClassLoader().getResource("").getPath();
         System.out.printf(path);
     }
}
