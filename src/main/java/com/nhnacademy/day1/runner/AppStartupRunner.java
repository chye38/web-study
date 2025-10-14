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
@RequiredArgsConstructor // 의존성 주입 1 (생성자 주입)
public class AppStartupRunner implements ApplicationRunner {

    private final OrderProcessorBean orderProcessorBean;

    private ChefBean chefBean;

    @Autowired  // 의존성 주입 2 (세터 주입)
    public void setChefBean(ChefBean chefBean) {
        this.chefBean = chefBean;
    }

    @Autowired  // 의존성 주입 3 (필드 주입)
    private DeliveryServiceBean deliveryServiceBean;


    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("[JBGW12-028]");
        System.out.println("ApplicationRunner run!");

        orderProcessorBean.processOrder();
        chefBean.cook();
        chefBean.completeCook();
        deliveryServiceBean.deliver();
        deliveryServiceBean.completeDeliver();
    }
}
