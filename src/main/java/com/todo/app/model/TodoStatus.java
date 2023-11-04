package com.todo.app.model;

public enum TodoStatus {
    NEW("New"),
    IN_PROGRESS("InProgress"),
    DONE("Done");

    private final String value;

    TodoStatus(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }

}
