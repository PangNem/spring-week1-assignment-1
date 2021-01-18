package com.codesoom.assignment;

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class App {
    public static void main(String[] args) throws IOException {
        int port = 8000;

        InetSocketAddress address = new InetSocketAddress(port);

        HttpServer httpServer = HttpServer.create(address, 0);
        HttpHandler handler = new DemoHttpHandler();

        httpServer.createContext("/", handler);
        System.out.printf("Server on http://localhost:%d\n", port);
        httpServer.start();
    }
}
