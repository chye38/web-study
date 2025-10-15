package com.nhnacademy.day3.process.cook;

import com.nhnacademy.day3.properties.MessageProperties;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ChefBean {

    private final MessageProperties messageProperties;

    public void cook() {
        System.out.println(messageProperties.getCooking());
    }
}
