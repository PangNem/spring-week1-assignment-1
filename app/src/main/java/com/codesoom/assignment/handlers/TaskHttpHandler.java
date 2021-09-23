package com.codesoom.assignment.handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.IOException;
import java.io.OutputStream;

public class TaskHttpHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        int httpStatusCode = 200;

        sendHttpResponse(httpExchange, httpStatusCode);
    }

    private void sendHttpResponse(HttpExchange httpExchange, int httpStatusCode)
        throws IOException {
        OutputStream outputStream = httpExchange.getResponseBody();

        httpExchange.sendResponseHeaders(httpStatusCode, 0);

        String message = "abc";

        outputStream.write(message.getBytes());
        outputStream.close();
    }
}
