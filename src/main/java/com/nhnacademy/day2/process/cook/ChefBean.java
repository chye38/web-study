package com.nhnacademy.day2.process.cook;

import com.nhnacademy.day2.properties.MessageProperties;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ChefBean {

    private final MessageProperties messageProperties;

    public void cook() {
        System.out.println(messageProperties.getCooking());
    }
}
