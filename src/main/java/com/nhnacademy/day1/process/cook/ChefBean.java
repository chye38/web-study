package com.nhnacademy.day1.process.cook;

import org.springframework.stereotype.Component;

@Component
public class ChefBean {
    public void cook() {
        System.out.println("ChefBean : 요리중");
    }
}
