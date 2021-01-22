package com.codesoom.assignment.util;

import com.codesoom.assignment.models.Task;
import com.codesoom.assignment.models.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static String toJson(Object object) throws JsonProcessingException {
        return objectMapper.writeValueAsString(object);
    }

    public static Task toTask(String content) throws JsonProcessingException {
        return objectMapper.readValue(content, Task.class);
    }

    public static User toUser(String content) throws JsonProcessingException {
        return objectMapper.readValue(content, User.class);
    }
}
