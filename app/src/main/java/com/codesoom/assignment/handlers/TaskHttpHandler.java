package com.codesoom.assignment.handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.IOException;
import java.io.OutputStream;

public class TaskHttpHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {

        OutputStream outputStream = httpExchange.getResponseBody();

        int httpStatusCode = 200;
        httpExchange.sendResponseHeaders(httpStatusCode, 0);

        String message = "abc";

        outputStream.write(message.getBytes());
        outputStream.close();
    }
}
