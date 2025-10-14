package com.nhnacademy.day2.process.delivery;

import com.nhnacademy.day2.properties.MessageProperties;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DeliveryServiceBean {

    private final MessageProperties messageProperties;

    public void deliver() {
        System.out.println(messageProperties.getDelivery());
    }
}
