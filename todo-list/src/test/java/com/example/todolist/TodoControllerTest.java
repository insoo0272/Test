package com.example.todolist;

import com.example.todolist.controller.TodoController;
import com.example.todolist.domain.dto.Todo;
import com.example.todolist.service.TodoService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

class TodoControllerTest {

    @Test
    void getAllTodos() throws Exception {

        String title = "Hello World";
        String content = "This is second todo.";

        // given
        TodoService mockService = Mockito.mock(TodoService.class);
        when(mockService.getTodos()).thenReturn(
                Collections.singletonList(
                        Todo.builder()
                                .title(title)
                                .content(content)
                                .build()
                )
        );


        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(new TodoController(mockService))
                .build();


        // when
        mockMvc.perform(get("/todos"))
                // then
                .andExpect(jsonPath("$[0].title").value(title))
                .andExpect(jsonPath("$[0].content").value(content));
    }
}
