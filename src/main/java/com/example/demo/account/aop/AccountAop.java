package com.example.demo.account.aop;

import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class AccountAop {
    @Pointcut("execution(* com.example.demo.account.service.AuthenticationService.login(..))")
    public void loginCut() {}

    @Before(value = "loginCut() && args(id, password)", argNames = "id,password")
    public void beforeLogin(long id, String password) throws Throwable {
        // 원하는 로직
        log.info("login([{}, {}])", id, password);
    }

    @Pointcut("execution(* com.example.demo.account.service.AuthenticationService.logout())")
    public void logoutCut() {}

    @After("logoutCut()")
    public void afterLogout() {
        log.info("logout([])");
    }
}