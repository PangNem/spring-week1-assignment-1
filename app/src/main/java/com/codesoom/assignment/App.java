package com.codesoom.assignment;

import com.codesoom.assignment.web.TaskHttpHandler;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.net.InetSocketAddress;

public class App {

    private static final int PORT = 8000;
    private static final int BACKLOG = 0;

    public static void main(String[] args) throws IOException {
        HttpServer httpServer = HttpServer.create(new InetSocketAddress(PORT), BACKLOG);

        httpServer.createContext("/tasks", new TaskHttpHandler());

        httpServer.start();

        System.out.printf("Server Started. Port: %s", PORT);
    }

    public String getGreeting() {
        return "Hello World!";
    }
}
