package com.example.todolist.domain.dto;

import com.example.todolist.domain.entity.TodoEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Todo {

    // thread-safe immutable object
    // setter
    private String title;
    private String content;

    // static method factory pattern
    public static Todo of(TodoEntity entity) {
        return Todo.builder()
                .title(entity.getTitle())
                .content(entity.getContent())
                .build();
    }
}
