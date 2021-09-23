package com.codesoom.assignment.application;

import com.codesoom.assignment.domain.Task;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class TaskService {

    private final Map<Long, Task> taskMap = new HashMap<>();
    private Long lastId = 0L;

    public Collection<Task> getTasks() {
        return taskMap.values();
    }

    public Task getTask(Long id) {
        return taskMap.get(id);
    }

    public Task createTask(String title) {
        Long id = getNextId();
        Task task = new Task(id, title);

        taskMap.put(id, task);

        return task;
    }

    public void deleteTask(Long id) {
        taskMap.remove(id);
    }

    public void updateTask(Long id, String title) {
        Task task = new Task(id, title);

        taskMap.replace(id, task);
    }

    private Long getNextId() {
        lastId++;

        return lastId;
    }
}
