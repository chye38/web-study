package com.nhnacademy.shoppingmall.common.filter;

import com.nhnacademy.shoppingmall.user.domain.User;
import lombok.extern.slf4j.Slf4j;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class AdminCheckFilter extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        //todo#11 /admin/ 하위 요청은 관리자 권한의 사용자만 접근할 수 있습니다. ROLE_USER가 접근하면 403 Forbidden 에러처리
        if(req.getServletPath().contains("/admin")){
            Object loginSession = req.getSession().getAttribute("loginSession");

            if(loginSession==null){
                res.sendRedirect("/login.do");
                return;
            }

            if(loginSession instanceof User user){
                if (user.getUserAuth() == User.Auth.ROLE_USER) {
                    log.debug("권한 없음");
                    res.sendError(HttpServletResponse.SC_FORBIDDEN);
                    return;
                }
            }
        }

        chain.doFilter(req,res);
    }
}
