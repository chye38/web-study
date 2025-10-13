package com.nhnacademy.day1.runner;

import com.nhnacademy.day1.process.cook.ChefBean;
import com.nhnacademy.day1.process.delivery.DeliveryServiceBean;
import com.nhnacademy.day1.process.order.OrderProcessorBean;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AppStartupRunner implements ApplicationRunner {

    private final OrderProcessorBean orderProcessorBean;
    @Autowired
    private ChefBean chefBean;
    private DeliveryServiceBean deliveryServiceBean;




    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("[JBGW12-028]");
        System.out.println("ApplicationRunner run!");

        orderProcessorBean.processOrder();
        chefBean.cook();

    }
}
