package com.nhnacademy.day1.process.delivery;

import org.springframework.stereotype.Component;

@Component
public class DeliveryServiceBean {
    public void deliver() {
        System.out.println("DeliveryServiceBean : 배달중");
    }
}
