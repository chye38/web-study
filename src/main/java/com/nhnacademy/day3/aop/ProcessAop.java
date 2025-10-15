package com.nhnacademy.day3.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class ProcessAop {

    //    @Pointcut("within(com.nhnacademy.day2.process..*)") // 프록시로 감싸지않아 AOP가 제대로 적용이 안됨
    @Pointcut("execution(* com.nhnacademy.day3.process.cook.ChefBean.cook())")
    public void cookCut(){
    }

    @Pointcut("execution(* com.nhnacademy.day3.process.delivery.DeliveryServiceBean.deliver())")
    public void deliverCut(){}

    @Pointcut("execution(* com.nhnacademy.day3.process.order.OrderProcessorBean.processOrder())")
    public void processCut(){}

    @Pointcut("execution(* com.nhnacademy.day3.process.order.OrderReceiverBean.receiveOrder())")
    public void receiveCut(){}

    @Pointcut("execution(* com.nhnacademy.day3.process.order.PaymentProcessorBean.processPayment())")
    public void paymentCut(){}

    // 이걸 within으로 한번에 할 방법 찾기
//    @Pointcut("within(com.nhnacademy.day2.process..*)")
//    public void cut(){}

    @Around("cookCut() || deliverCut() || processCut() || receiveCut() || paymentCut()")
//    @Around("cut()")
    public void Around(ProceedingJoinPoint pjp) throws Throwable {
        String simpleClassName = pjp.getSignature().getDeclaringType().getSimpleName();
        String methodName = pjp.getSignature().getName();

        log.info("--> {}.{}()", simpleClassName, methodName);
        pjp.proceed();
        log.info("<-- {}.{}()", simpleClassName, methodName);
    }
}
