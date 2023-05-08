package com.example.todolist.service;

import com.example.todolist.domain.dto.Todo;

import java.util.List;

public interface TodoService {
    List<Todo> getTodos();
}
