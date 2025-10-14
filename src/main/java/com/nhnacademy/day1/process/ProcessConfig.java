package com.nhnacademy.day1.process;

import com.nhnacademy.day1.process.cook.ChefBean;
import com.nhnacademy.day1.process.delivery.DeliveryServiceBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// 빈 생성 방식 2 (Configuration + Bean)
@Configuration
public class ProcessConfig {
    @Bean
    ChefBean chefBean() {
        return new ChefBean();
    }
    @Bean
    DeliveryServiceBean deliveryServiceBean() {
        return new DeliveryServiceBean();
    }
}
