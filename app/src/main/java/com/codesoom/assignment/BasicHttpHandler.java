package com.codesoom.assignment;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;

import static com.codesoom.assignment.Status.NOT_FOUND;

public class BasicHttpHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange httpExchange) throws IOException {

        httpExchange.sendResponseHeaders(NOT_FOUND.getStatus(), 0);

        OutputStream outputStream = httpExchange.getResponseBody();
        outputStream.flush();
        outputStream.close();
    }
}
