package com.nhnacademy.day2.process.order;

import com.nhnacademy.day2.properties.MessageProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

// 빈 생성 방식 1 (Component)
@Component
public class PaymentProcessorBean {

    @Value("${message.payment}")
    private String payment;

    public void processPayment() {
        System.out.println(payment);
    }
}
