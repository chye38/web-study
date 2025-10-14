package com.nhnacademy.day2.process.order;

import com.nhnacademy.day2.properties.MessageProperties;
import lombok.RequiredArgsConstructor;
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
