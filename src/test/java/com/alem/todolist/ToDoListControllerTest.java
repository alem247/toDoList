package com.alem.todolist;


import com.alem.todolist.controller.ToDoListController;
import com.alem.todolist.model.Task;
import com.alem.todolist.repository.ToDoListRepository;
import com.alem.todolist.service.ToDoListService;
import com.alem.todolist.service.ToDoListServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ToDoListController.class)
public class ToDoListControllerTest {

    @MockBean
    ToDoListRepository toDoListRepository;

    @InjectMocks
    ToDoListService toDoListService = new ToDoListServiceImpl(toDoListRepository);

    @Autowired
    private MockMvc mockmvc;
    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
    }
    @Test
    void shouldCreateMockMvc(){
        assertNotNull(mockmvc);
    }


    @Test
    void shouldReturnFirstTask() throws Exception {
        this.mockmvc.perform(get("/tasks")
                        .param("id", "1")
                        .accept("application/json"))
                        .andExpect(status().isOk());
    }
    @Test
    void shouldReturnAllTasks() throws Exception {

        List<Task> tasks = toDoListService.fetchAllTasks();
        when(toDoListRepository.findAll()).thenReturn(tasks);
        this.mockmvc.perform(MockMvcRequestBuilders.get("/tasks"))
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(tasks.size())))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }


}
