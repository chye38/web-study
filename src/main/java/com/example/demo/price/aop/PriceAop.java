package com.example.demo.price.aop;


import com.example.demo.account.dto.Account;
import com.example.demo.account.service.AuthenticationService;

import java.util.Arrays;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
@RequiredArgsConstructor
public class PriceAop {

    private final AuthenticationService authenticationService;

    @Pointcut("execution(* com.example.demo.shell.MyCommands.*(..)) &&" +
            "!execution(* com.example.demo.shell.MyCommands.login(..)) &&" +
            "!execution(* com.example.demo.shell.MyCommands.logout()) &&" +
            "!execution(* com.example.demo.shell.MyCommands.currentUser())"
    )
    public void priceMethodCut() {}

//    @Before("priceMethodCut()")
//    public void loginRequiredMethod() {
//        Account account = authenticationService.getCurrentAccount();
//        if(Objects.isNull(account)){
//            throw new IllegalArgumentException("Login required");
//        }
//    }

    @Around("priceMethodCut()")
    public Object aroundPriceMethod(ProceedingJoinPoint pjp) throws Throwable {
        Account account = authenticationService.getCurrentAccount();

        if(Objects.isNull(account)){
            throw new IllegalArgumentException("Login required");
        }

        String fullMethodName = "%s.%s".formatted(
                pjp.getSignature().getDeclaringTypeName(),
                pjp.getSignature().getName()
        );
        // 사용자 입력 Args
        String args = Arrays.toString(pjp.getArgs());
        log.info("----- %s class %s(%s) ----->"
                .formatted(
                        account.getName(),
                        fullMethodName,
                        args
                )
        );

        Object object = pjp.proceed();

        // 서버 반환 값
        log.info("<----- %s class %s(%s) -----"
                .formatted(
                        account.getName(),
                        fullMethodName,
                        object
                )
        );

        return object;
    }
}