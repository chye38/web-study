package com.nhnacademy.shoppingmall.common.filter;

import jakarta.servlet.annotation.WebServlet;
import lombok.extern.slf4j.Slf4j;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j

public class WelcomePageFilter extends HttpFilter {

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        //todo#9 /요청이 오면 welcome page인 index.do redirect 합니다.
        if("/".equals(req.getServletPath())){
            log.debug("Redirecting to /index.do");
            res.sendRedirect(req.getServletPath() + "/index.do");
            return;
        }

        chain.doFilter(req, res);
    }
}
