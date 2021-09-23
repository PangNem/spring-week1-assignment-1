package com.codesoom.assignment.domain;

public class Task {

    private Long id;
    private String title;

    protected Task() {
    }

    public Task(Long id, String title) {
        this.id = id;
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getId() {
        return id;
    }

}
