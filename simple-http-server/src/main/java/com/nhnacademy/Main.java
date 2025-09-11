package com.nhnacademy;

import com.nhnacademy.httpServer.util.SimpleHttpServer;

public class Main {
    public static void main(String[] args) {
        SimpleHttpServer simpleHttpServer = new SimpleHttpServer();
        simpleHttpServer.start();
    }
}