package com.nhnacademy.day1.process.order;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrderProcessorBean {
    @Bean
    OrderReceiverBean orderReceiverBean() {
        return new OrderReceiverBean();
    }

    @Bean
    PaymentProcessorBean paymentProcessorBean() {
        return new PaymentProcessorBean();
    }

    public void processOrder(){
        orderReceiverBean().receiveOrder();
        paymentProcessorBean().processPayment();
    }
}
