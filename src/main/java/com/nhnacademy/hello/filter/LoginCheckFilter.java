package com.nhnacademy.hello.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

//@WebFilter(
//        filterName = "LoginCheckFilter",
//        urlPatterns = "/*",
//        initParams = {
//                @WebInitParam(name = "exclude-urls", value = "/login\n/logout\n/login.html")
//        }
//)
public class LoginCheckFilter implements Filter {
    private final Set<String> excludeUrls = new HashSet<>();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String urls = filterConfig.getInitParameter("exclude-urls");
        Arrays.stream(urls.split("\n"))
                .map(String::trim)
                .forEach(excludeUrls::add);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws
            IOException, ServletException {
        // 로그인 체크 필터 적용할 URL인 경우
        String requestUri = ((HttpServletRequest) servletRequest).getRequestURI();
        if (!excludeUrls.contains(requestUri)) {
            // 세션 찾기
            HttpSession session = ((HttpServletRequest) servletRequest).getSession(false);
            if (Objects.isNull(session)) {
                ((HttpServletResponse) servletResponse).sendRedirect("/login.html");
                return;
            }
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
