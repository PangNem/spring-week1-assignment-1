package com.codesoom.assignment.errors;

public class TaskNotFoundException extends Throwable {

    public TaskNotFoundException(Long id) {
        super(String.format("id %s에 대한 할일을 찾을 수 없습니다.", id));
    }
}
