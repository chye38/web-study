package com.nhnacademy.shoppingmall.common.filter;

import jakarta.servlet.http.HttpSession;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@WebFilter(urlPatterns = "/mypage.do")
public class LoginCheckFilter extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        //todo#10 /mypage/ 하위경로의 접근은 로그인한 사용자만 접근할 수 있습니다.
        HttpSession session = req.getSession(false);
        if(Objects.isNull(session) || Objects.isNull(session.getAttribute("loginUser"))){
            log.info("비로그인 사용자 접근 차단 : {}", req.getRequestURI());
            res.sendRedirect("/login.do");
            return;
        }

        chain.doFilter(req,res);
    }
}