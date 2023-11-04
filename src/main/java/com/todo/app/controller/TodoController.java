package com.todo.app.controller;

import com.todo.app.model.Todo;
import com.todo.app.model.TodoRepository;
import com.todo.app.model.TodoStatus;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TodoController {
    private final TodoRepository repository;

    TodoController(TodoRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/todos")
    List<Todo> all() {
       return repository.findAll();
    }

    @PostMapping("/addtodo")
    Todo newTodo(@RequestBody String message) {
        Todo newTodo = repository.save(new Todo(message));
        return newTodo;
    }

    @GetMapping("/getById")
    Todo getTodo(@Param("id") Long id) {
        Todo fetchedTodo = repository.findById(id).orElse(null);
        return fetchedTodo;
    }

    @PostMapping("/updateTodoInProgress")
    Todo updateTodo(@Param("id") Long id) {
        Todo updateTodo = null;
        try {
            updateTodo = repository.findById(id).orElse(null);
            if(updateTodo != null) {
                updateTodo.setStatus(TodoStatus.IN_PROGRESS.toString());
                repository.save(updateTodo);
            }
        } catch(Exception e) {
            System.out.println("Todo with id "+id+" is not found");
        }
        return updateTodo;

    }

    @PostMapping("/completeTodo")
    Todo completeTodo(@Param("id") Long id) {
        Todo completeTodo = null;
        try {
            completeTodo = repository.findById(id).orElse(null);
            if(completeTodo != null) {
                if(completeTodo.getStatus().equals(TodoStatus.IN_PROGRESS.toString())) {
                    completeTodo.setStatus(TodoStatus.DONE.toString());
                    repository.save(completeTodo);
                } else {
                    System.out.println("Todo with id " + id + " is not started yet");
                    return null;
                }
            }
        } catch(Exception e) {
            System.out.println("Todo with id "+id+" is not found");
        }
        return completeTodo;
    }
}
