package com.example.todolist.service;

import com.example.todolist.domain.dto.Todo;
import com.example.todolist.store.TodoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TodoServiceImpl implements TodoService {

    private final TodoRepository todoRepository;

    public TodoServiceImpl(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    // refactoring -> 영향도를 모름
    @Override
    public List<Todo> getTodos() {
        // lambda expression
        // Java 8 > 11 > 17 | stream | > 데이터
        return todoRepository.findAll()
                .stream()
                .map(Todo::of)
                .collect(Collectors.toList());
    }
}
