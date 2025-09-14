package com.nhnacademy.httpServer.service;

import com.nhnacademy.httpServer.server.SessionManager;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;

public class LogoutService implements HttpService{
    SessionManager sessionM = SessionManager.getInstance();

    @Override
    public String getPath() {
        return "/logout";
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String method = exchange.getRequestMethod();
        if("GET".equals(method)){
            doGet(exchange);
        }else if("POST".equals(method)){
            doPost(exchange);
        }else{
            exchange.sendResponseHeaders(405,0);
        }
    }

    private void doGet(HttpExchange exchange) throws IOException {
        String cookie = exchange.getRequestHeaders().getFirst("Cookie");
        String sessionId = "";
        for (String s : cookie.split("\\s+")) {
            if(s.contains("session")){
                sessionId = s.split("=")[1];
            }
        }

        if(sessionM.isValidSession(sessionId)){
            sessionM.removeSession(sessionId);
            // 즉시 만료
            String expireCookie = "session=; Max-Age=0; HttpOnly; SameSite=Lax";
            exchange.getResponseHeaders().add("Set-Cookie",expireCookie);
        }
        exchange.getResponseHeaders().add("Location", "/login");
        exchange.sendResponseHeaders(302,-1);
    }

    private void doPost(HttpExchange exchange) throws IOException {
        doGet(exchange);
    }
}
