package com.codesoom.assignment;

import com.codesoom.assignment.models.Task;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public class TaskHttpHandler implements HttpHandler {

    private List<Task> tasks = new ArrayList<>();

    public TaskHttpHandler() {
        addTemporaryTasks();
    }

    private void addTemporaryTasks() {
        Task task1 = new Task(1L, "task one");
        Task task2 = new Task(2L, "task two");
        Task task3 = new Task(3L, "task three");

        tasks.add(task1);
        tasks.add(task2);
        tasks.add(task3);
    }

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        URI requestURI = httpExchange.getRequestURI();
        String path = requestURI.getPath();
        String method = httpExchange.getRequestMethod();

        System.out.println(method + " " + path);

        String content = "Hello, World!";
        int httpStatusCode = 200;

        if (method.equals("GET") && path.equals("/tasks")) {
            content = tasksToJson();
        }

        httpExchange.sendResponseHeaders(httpStatusCode, content.getBytes().length);

        OutputStream responseBody = httpExchange.getResponseBody();
        responseBody.write(content.getBytes());
        responseBody.flush();
        responseBody.close();
    }

    private String tasksToJson() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        OutputStream outputStream = new ByteArrayOutputStream();

        objectMapper.writeValue(outputStream, tasks);

        return outputStream.toString();
    }

}