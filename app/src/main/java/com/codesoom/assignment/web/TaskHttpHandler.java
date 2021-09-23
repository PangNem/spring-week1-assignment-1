package com.codesoom.assignment.web;

import com.codesoom.assignment.application.TaskService;
import com.codesoom.assignment.domain.Task;
import com.codesoom.assignment.errors.TaskNotFoundException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Collection;
import java.util.regex.Pattern;

public class TaskHttpHandler implements HttpHandler {

    private final TaskService taskService = new TaskService();

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        String method = httpExchange.getRequestMethod();
        String path = httpExchange.getRequestURI().getPath();

        if (isTasksPathWithId(path)) {
            checkIdNotExist(httpExchange, path);
        }

        if ("GET".equals(method) && "/tasks".equals(path)) {
            Collection<Task> tasks = taskService.getTasks();

            sendHttpResponse(httpExchange, 200, toJson(tasks));
        }

        if ("GET".equals(method) && isTasksPathWithId(path)) {
            long id = getId(path);

            Task task = taskService.getTask(id);

            sendHttpResponse(httpExchange, 200, toJson(task));
        }

        if ("POST".equals(method) && "/tasks".equals(path)) {
            Task task = toTask(getResponseBody(httpExchange));

            String title = task.getTitle();
            Task createdTask = taskService.createTask(title);

            sendHttpResponse(httpExchange, 201, toJson(createdTask));
        }

        if (("PATCH".equals(method) || "PUT".equals(method)) && isTasksPathWithId(path)) {
            long id = getId(path);
            Task task = toTask(getResponseBody(httpExchange));

            Task updatedTask = taskService.updateTask(id, task.getTitle());

            sendHttpResponse(httpExchange, 200, toJson(updatedTask));
        }

        if ("DELETE".equals(method) && isTasksPathWithId(path)) {
            long id = getId(path);

            taskService.deleteTask(id);

            sendHttpResponse(httpExchange, 204);
        }

        sendHttpResponse(httpExchange, 404);
    }

    private void checkIdNotExist(HttpExchange httpExchange, String path) throws IOException {
        try {
            taskService.checkIdExist(getId(path));
        } catch (TaskNotFoundException e) {
            sendHttpResponse(httpExchange, 404);
        }
    }

    private long getId(String path) {
        String replace = path.replace("/tasks/", "");
        return Long.parseLong(replace);
    }

    private String toJson(Object value) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(value);
    }

    private Task toTask(String body) throws com.fasterxml.jackson.core.JsonProcessingException {
        return new ObjectMapper().readValue(body, Task.class);
    }

    private String getResponseBody(HttpExchange httpExchange) throws IOException {
        InputStream requestBody = httpExchange.getRequestBody();
        InputStreamReader inputStreamReader = new InputStreamReader(requestBody);

        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        return bufferedReader.readLine();
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
