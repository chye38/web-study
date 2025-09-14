package com.nhnacademy.httpServer.service;

import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class StaticResourceService implements HttpService {

    private final String resourceRoot; // 예: "css" 또는 "js"

    public StaticResourceService(String resourceRoot) {
        this.resourceRoot = resourceRoot;
    }

    @Override
    public String getPath() {
        return "/" + resourceRoot; // /css, /js
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        // GET만 처리
        if (!"GET".equals(exchange.getRequestMethod())) {
            exchange.sendResponseHeaders(405, 0);
            exchange.close();
            return;
        }

        String uriPath = exchange.getRequestURI().getPath(); // 예: /css/login.css
        String filePath = uriPath.substring(1);               // css/login.css

        try (InputStream is = getClass().getClassLoader().getResourceAsStream(filePath);
             OutputStream os = exchange.getResponseBody()) {

            if (is == null) {
                exchange.sendResponseHeaders(404, 0);
                exchange.close();
                return;
            }

            // MIME 타입 지정
            String contentType = "text/plain";
            if (filePath.endsWith(".css")) contentType = "text/css";
            if (filePath.endsWith(".js")) contentType = "application/javascript";

            byte[] bytes = is.readAllBytes();
            exchange.getResponseHeaders().add("Content-Type", contentType + "; charset=UTF-8");
            exchange.sendResponseHeaders(200, bytes.length);
            os.write(bytes);
            os.flush();
        }
    }
}
