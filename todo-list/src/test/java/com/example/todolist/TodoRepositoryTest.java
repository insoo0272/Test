package com.example.todolist;

import com.example.todolist.store.TodoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
// @TestConfiguration // initSql
class TodoRepositoryTest {

    // persistence context
    // flush
    // clear

    @Autowired
    TodoRepository sut;

    @Test
    void getAllTodos() {
    }

    @Test
    void createTodo() {
    }
}