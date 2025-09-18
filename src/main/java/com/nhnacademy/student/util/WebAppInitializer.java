package com.nhnacademy.student.util;

import com.nhnacademy.student.controller.ControllerFactory;
import jakarta.servlet.ServletContainerInitializer;
import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import java.util.Set;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@HandlesTypes(
        value = {
                com.nhnacademy.student.controller.Command.class
        }
)
public class WebAppInitializer implements ServletContainerInitializer {
    @Override
    public void onStartup(Set<Class<?>> c, ServletContext ctx) throws ServletException {

        ControllerFactory controllerFactory = new ControllerFactory();
        controllerFactory.init(c);
        ctx.setAttribute("controllerFactory", controllerFactory);

    }

}