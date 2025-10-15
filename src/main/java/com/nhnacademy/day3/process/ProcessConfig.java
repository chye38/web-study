package com.nhnacademy.day3.process;

import com.nhnacademy.day3.process.cook.ChefBean;
import com.nhnacademy.day3.process.delivery.DeliveryServiceBean;
import com.nhnacademy.day3.properties.MessageProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// 빈 생성 방식 2 (Configuration + Bean)
@Configuration
@RequiredArgsConstructor
public class ProcessConfig {

    private final MessageProperties messageProperties;

    @Bean
    ChefBean chefBean() {
        return new ChefBean(messageProperties);
    }
    @Bean
    DeliveryServiceBean deliveryServiceBean() {
        return new DeliveryServiceBean(messageProperties);
    }
}
