package com.example.todolist;

import com.example.todolist.domain.dto.Todo;
import com.example.todolist.domain.entity.TodoEntity;
import com.example.todolist.service.TodoService;
import com.example.todolist.service.TodoServiceImpl;
import com.example.todolist.store.TodoRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.mockito.Mockito.when;

class TodoServiceImplTest {

    @Test
    void getTodos() {

        String title = "Hello World";
        String content = "This is first content.";
        TodoRepository todoRepository = Mockito.mock(TodoRepository.class);
        when(todoRepository.findAll())
                .thenReturn(Collections.singletonList(
                                TodoEntity.builder()
                                        .id(1)
                                        .title(title)
                                        .content(content)
                                        .build()
                        )
                );
        TodoService sut = new TodoServiceImpl(todoRepository);


        // system under test
        // when
        List<Todo> result = sut.getTodos();


        // given
        assertThat(result.size(), equalTo(1));
        assertThat(result.get(0).getTitle(), equalTo(title));
        assertThat(result.get(0).getContent(), equalTo(content));
    }
}