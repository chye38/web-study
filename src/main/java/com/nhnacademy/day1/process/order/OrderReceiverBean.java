package com.nhnacademy.day1.process.order;

import org.springframework.stereotype.Component;

// 빈 생성 방식 1 (Component)
@Component
public class OrderReceiverBean {
    public void receiveOrder() {
        System.out.println("OrderReceiverBean : 주문 받는중");
    }
    public void completeOrder() {
        System.out.println("OrderReceiverBean : 주문 완료");
    }
}
