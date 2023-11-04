package com.todo.app;

import com.todo.app.model.Todo;
import com.todo.app.model.TodoRepository;
import com.todo.app.model.TodoStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {
    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(TodoRepository repository) {
        return args -> {
            log.info("Preloading" + repository.save(new Todo("First Todo", TodoStatus.NEW.toString())));
        };
    }
}
