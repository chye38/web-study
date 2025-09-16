package com.nhnacademy.hello.request;

import com.nhnacademy.hello.practice.AdminPageFilter;
import com.nhnacademy.hello.practice.MyPageFilter;
import com.nhnacademy.hello.main.FilterChain;

public class HttpRequest {
    private final FilterChain filterChain = new FilterChain();

    public HttpRequest(){
        initFilter();
    }

    public void doRequest(Request request){
        filterChain.doFilter(request);
    }

    private void initFilter(){
        filterChain.addFilter(new MyPageFilter());
        filterChain.addFilter(new AdminPageFilter());
    }

}