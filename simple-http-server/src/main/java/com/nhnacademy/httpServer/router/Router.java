package com.nhnacademy.httpServer.router;

import com.nhnacademy.httpServer.service.HttpService;
import com.nhnacademy.httpServer.service.StaticResourceService;
import com.sun.net.httpserver.HttpServer;
import org.reflections.Reflections;

import java.util.Set;

public class Router {
    HttpServer httpServer;

    public Router(HttpServer httpServer){
        if(httpServer == null){
            throw new NullPointerException("httpServer is null");
        }
        this.httpServer = httpServer;
        addRouter(httpServer);
    }

    private void addRouter(HttpServer httpServer){
        // 리스코프 원칙
        Reflections reflections = new Reflections("com.nhnacademy.httpServer");
        Set<Class<? extends HttpService>> clazz = reflections.getSubTypesOf(HttpService.class);

        for (Class<? extends HttpService> aClass : clazz) {
            try {
                if (StaticResourceService.class.isAssignableFrom(aClass)) {
                    continue; // 자동 등록에서 제외
                }

                HttpService httpService = aClass.getDeclaredConstructor().newInstance();
                httpServer.createContext(httpService.getPath(), httpService);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        // css, js는 따로 등록
        httpServer.createContext("/css", new StaticResourceService("css"));
        httpServer.createContext("/js", new StaticResourceService("js"));


    }
}
