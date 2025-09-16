package com.nhnacademy.hello.initializer.WebAppInitializer;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import java.util.Set;

public interface ServletContainerInitializer {
    public void onStartup(Set<Class<?>> c, ServletContext ctx) throws ServletException;
}