package com.codesoom.assignment.handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.IOException;
import java.io.OutputStream;
import java.util.regex.Pattern;

public class TaskHttpHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        String method = httpExchange.getRequestMethod();
        String path = httpExchange.getRequestURI().getPath();

        if ("GET".equals(method) && "/tasks".equals(path)) {
            sendHttpResponse(httpExchange, 200);
        }

        if ("GET".equals(method) && isTasksPathWithId(path)) {
            sendHttpResponse(httpExchange, 200);
        }

        if ("POST".equals(method) && "/tasks".equals(path)) {
            sendHttpResponse(httpExchange, 201);
        }

        if (("PATCH".equals(method) || "PUT".equals(method)) && isTasksPathWithId(path)) {
            sendHttpResponse(httpExchange, 200);
        }

        if ("DELETE".equals(method) && isTasksPathWithId(path)) {
            sendHttpResponse(httpExchange, 204);
        }

        sendHttpResponse(httpExchange, 404);
    }

    private boolean isTasksPathWithId(String path) {
        return Pattern.matches("/tasks/[0-9]*$", path);
    }

    private void sendHttpResponse(HttpExchange httpExchange, int httpStatusCode)
        throws IOException {
        sendHttpResponse(httpExchange, httpStatusCode, "{}");
    }

    private void sendHttpResponse(HttpExchange httpExchange, int httpStatusCode, String message)
        throws IOException {
        OutputStream outputStream = httpExchange.getResponseBody();

        httpExchange.sendResponseHeaders(httpStatusCode, 0);

        outputStream.write(message.getBytes());
        outputStream.close();
    }
}
