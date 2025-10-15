package com.nhnacademy.day3.process.order;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

// 빈 생성 방식 1 (Component)
@Component
public class OrderReceiverBean {

    @Value("${message.receive}")
    private String receive;

    public void receiveOrder() {
        System.out.println(receive);
    }
}
