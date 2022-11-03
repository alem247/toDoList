package com.alem.todolist;


import com.alem.todolist.controller.ToDoListController;
import com.alem.todolist.model.Task;
import com.alem.todolist.repository.ToDoListRepository;
import com.alem.todolist.service.ToDoListService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
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

@AutoConfigureMockMvc
@WebMvcTest(ToDoListController.class)
public class ToDoListControllerTest {

    @MockBean
    private ToDoListRepository toDoListRepository;

    @MockBean
    private ToDoListService toDoListService;

    @Autowired
    WebApplicationContext wac;

    @Autowired
    private MockMvc mockmvc;

    @BeforeEach
    void setup(WebApplicationContext wac) {
        this.mockmvc = MockMvcBuilders.webAppContextSetup(wac).build();
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
        List<Task> tasks = new ArrayList<Task>();
        Task one = new Task();
        Task two = new Task();
        Task three = new Task();
        one.setId(1L);
        one.setDate("3/11/2022");
        one.setDesc("test one");
        one.setGroup("work");
        one.setLocation("office");
        two.setId(2L);
        two.setDate("3/11/2022");
        two.setDesc("test two");
        two.setGroup("work");
        two.setLocation("office");
        three.setId(3L);
        three.setDate("3/11/2022");
        three.setDesc("test three");
        three.setGroup("work");
        three.setLocation("office");
        tasks.add(one);
        tasks.add(two);
        tasks.add(three);
        when(toDoListRepository.findAll()).thenReturn(tasks);
        List<Task> taskList = toDoListRepository.findAll();
        assertEquals(3, taskList.size());
        verify(toDoListRepository, times(1)).findAll();
    }


}
