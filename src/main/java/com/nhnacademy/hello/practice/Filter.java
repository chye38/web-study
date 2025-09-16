package com.nhnacademy.hello.practice;

import com.nhnacademy.hello.request.Request;
import com.nhnacademy.hello.main.FilterChain;

public interface Filter {
    void doFilter(Request request, FilterChain filterChain);
}