package com.nhnacademy.day1.process.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

// 빈 생성 방식 1 (Component)
@Component
public class OrderProcessorBean {
    private final OrderReceiverBean  orderReceiverBean;
    private final PaymentProcessorBean paymentProcessorBean;

    @Autowired
    public OrderProcessorBean(OrderReceiverBean orderReceiverBean, PaymentProcessorBean paymentProcessorBean) {
        this.orderReceiverBean = orderReceiverBean;
        this.paymentProcessorBean = paymentProcessorBean;
    }

    public void processOrder() {
        orderReceiverBean.receiveOrder();
        orderReceiverBean.completeOrder();
        paymentProcessorBean.processPayment();
        paymentProcessorBean.completePayment();
    }
}
