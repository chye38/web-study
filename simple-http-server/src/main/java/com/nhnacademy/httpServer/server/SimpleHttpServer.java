package com.nhnacademy.httpServer.server;

import com.nhnacademy.httpServer.router.Router;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.net.InetSocketAddress;

public class SimpleHttpServer {
    private final int port;
    private static final int DEFAULT_PORT = 8080;

    private final HttpServer httpServer;

    public SimpleHttpServer(){
        this(DEFAULT_PORT);
    }

    public SimpleHttpServer(int port) {
        if (port < 1) throw new IllegalArgumentException("유효하지않은 포트번호 : %d".formatted(port));
        this.port = port;

        try {
            httpServer = HttpServer.create(new InetSocketAddress(port), 0);
            Router router = new Router(httpServer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void start(){
        httpServer.start();
    }
}
