package com.nhnacademy.httpServer.service;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;

public interface HttpService extends HttpHandler {
    String getPath();

    @Override
    void handle(HttpExchange exchange) throws IOException;
}
