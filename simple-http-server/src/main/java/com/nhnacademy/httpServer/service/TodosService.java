package com.nhnacademy.httpServer.service;

import com.nhnacademy.httpServer.server.SessionManager;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;

public class TodosService implements HttpService{
    SessionManager sessionM = SessionManager.getInstance();

    @Override
    public String getPath() {
        return "/todos";
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String method = exchange.getRequestMethod();

        if("GET".equals(method)){
            doGet(exchange);
        }else if("POST".equals(method)){
            doGet(exchange);
        }else {
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
        // 로그인 안된 상태면 로그인 페이지로 이동
        if(!sessionM.isValidSession(sessionId)){
            exchange.getResponseHeaders().add("Location", "/login");
            exchange.sendResponseHeaders(302,-1);
            exchange.close();
            return;
        }

        try(InputStream is = getClass().getClassLoader().getResourceAsStream("html/todos.html");
            OutputStream os = exchange.getResponseBody();)
        {
            if(is==null){
                exchange.sendResponseHeaders(404,-1);
                return;
            }

            byte[] fileBytes = is.readAllBytes();

            exchange.getResponseHeaders().add("Content-Type", "text/html; charset=UTF-8");
            exchange.sendResponseHeaders(200,fileBytes.length);
            os.write(fileBytes);
            os.flush();
        }
    }
}
