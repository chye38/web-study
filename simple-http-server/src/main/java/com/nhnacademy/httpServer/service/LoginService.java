package com.nhnacademy.httpServer.service;

import com.nhnacademy.httpServer.domain.Session;
import com.nhnacademy.httpServer.server.SessionManager;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

public class LoginService implements HttpService{
    private SessionManager sessionM = SessionManager.getInstance();

    @Override
    public String getPath() {
        return "/login";
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {

        String method = exchange.getRequestMethod();
        System.out.println("Method: " + method);
        if(method.equals("GET")){
            doGet(exchange);
        } else if(method.equals("POST")){
            doPost(exchange);
        }else {
            exchange.sendResponseHeaders(405,0);
        }
    }

    private void doGet(HttpExchange exchange){
        try(InputStream is = getClass().getClassLoader().getResourceAsStream("html/login.html");
                OutputStream os = exchange.getResponseBody();)
        {
            // 이미 로그인한 세션이면 todos로 리다이렉트
            String cookie = exchange.getRequestHeaders().getFirst("Cookie");
            String sessionId = "";
            for (String s : cookie.split("\\s+")) {
                if(s.contains("session")){
                    sessionId = s.split("=")[1];
                }
            }

            if(sessionM.isValidSession(sessionId)){
                exchange.getResponseHeaders().add("Location", "/todos");
                exchange.sendResponseHeaders(302,-1);
            }

            if(is==null){
                exchange.sendResponseHeaders(404,0);
                return;
            }

            byte[] fileBytes = is.readAllBytes();

            exchange.getResponseHeaders().add("Content-Type", "text/html; charset=UTF-8");
            exchange.sendResponseHeaders(200, fileBytes.length);
            os.write(fileBytes);
            os.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void doPost(HttpExchange exchange){
        String id;
        String pw;
        try (InputStream is = exchange.getRequestBody();){
            String body = new String(is.readAllBytes(), StandardCharsets.UTF_8);
            String[] paris = body.split("&");
            id = paris[0].split("=")[1];
            pw = paris[1].split("=")[1];
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        boolean loginFlag = "test".equals(id) && "1234".equals(pw);
        // 로그인 성공
        if(loginFlag){
            String sessionID = UUID.randomUUID().toString();
            sessionM.putSession(sessionID, new Session(sessionID, id));

            String cookie = "session=%s; HttpOnly; SameSite=Lax Path=/".formatted(sessionID);
            exchange.getResponseHeaders().add("Set-Cookie", cookie);
            exchange.getResponseHeaders().add("Location", "/todos");


            try {
                exchange.sendResponseHeaders(302, -1);
                exchange.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }else{
            doGet(exchange);
        }
    }
}
