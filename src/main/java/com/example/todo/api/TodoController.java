package com.example.todo.api;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/api/todos")
public class TodoController {

    private final Map<Long, TodoItem> store = new ConcurrentHashMap<>();
    private final AtomicLong seq = new AtomicLong(1);

    @GetMapping
    public Collection<TodoItem> list() {
        return store.values();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TodoItem create(@Valid @RequestBody TodoItem payload) {
        long id = seq.getAndIncrement();
        TodoItem saved = new TodoItem(id, payload.title(), payload.notes(), payload.done());
        store.put(id, saved);
        return saved;
    }
}
