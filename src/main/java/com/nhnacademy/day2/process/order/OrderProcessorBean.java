package com.nhnacademy.day2.process.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Component;

// 빈 생성 방식 1 (Component)
@Component
@ConditionalOnBean({OrderProcessorBean.class, PaymentProcessorBean.class})
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
        paymentProcessorBean.processPayment();
    }
}
