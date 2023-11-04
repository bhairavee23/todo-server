package com.todo.app.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.Objects;

@Data
@Entity
public class Todo {

    private @Id @GeneratedValue Long id;
    private String message;
    private String status;

    public Todo() {}

    public Todo(String message) {
        this.message = message;
        this.status = TodoStatus.NEW.toString();
    }

    public Todo(String message, String status) {
        this.message = message;
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o)
            return true;
        if(!(o instanceof Todo todo))
            return false;
        return Objects.equals(this.id, todo.id)&& Objects.equals(this.message, todo.message) && Objects.equals(this.status, todo.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.message, this.status);
    }

    @Override
    public String toString() {
        return "Todo {" + "id = " + this.id + ", message = " + this.message + ", status = " + this.status +"}";
    }
}
