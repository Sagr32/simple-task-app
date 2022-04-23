package com.sagr.task.models;

public class Task {
    public Task(int id, String taskTitle) {
        this.id = id;
        this.taskTitle = taskTitle;
        isDone = false;
    }

    private int id;
    private boolean isDone;
    private String taskTitle;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public String getTaskTitle() {
        return taskTitle;
    }

    public void setTaskTitle(String taskTitle) {
        this.taskTitle = taskTitle;
    }
}
