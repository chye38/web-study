package com.nhnacademy.day1.process.order;

import org.springframework.stereotype.Component;

// 빈 생성 방식 1 (Component)
@Component
public class PaymentProcessorBean {
    public void processPayment() {
        System.out.println("PaymentProcessorBean : 결제 처리중");
    }
    public void completePayment() {
        System.out.println("PaymentProcessorBean : 결제 처리 완료!");
    }
}
