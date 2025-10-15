package com.nhnacademy.day3.process.delivery;

import com.nhnacademy.day3.properties.MessageProperties;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DeliveryServiceBean {

    private final MessageProperties messageProperties;

    public void deliver() {
        System.out.println(messageProperties.getDelivery());
    }
}
