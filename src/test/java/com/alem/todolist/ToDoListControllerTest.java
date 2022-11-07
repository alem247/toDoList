package com.alem.todolist;


import com.alem.todolist.controller.ToDoListController;
import com.alem.todolist.model.Task;
import com.alem.todolist.repository.ToDoListRepository;
import com.alem.todolist.service.ToDoListService;
import com.alem.todolist.service.ToDoListServiceImpl;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ToDoListController.class)
@AutoConfigureMockMvc
public class ToDoListControllerTest {

    @MockBean
    private ToDoListRepository toDoListRepository;
    @InjectMocks
    private ToDoListController toDoListController = new ToDoListController(toDoListRepository);

    @Autowired
    private MockMvc mockmvc;

    //  // task: id, desc, date, location, group
    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        List<Task> tasks = toDoListController.getTasks();
        when(toDoListRepository.findAll()).thenReturn(tasks);
    }
    @Test
    void shouldCreateMockMvc(){
        assertNotNull(mockmvc);
    }

    @Test
    void shouldReturnFirstTask() throws Exception {
        ResultActions result = this.mockmvc.perform(get("/tasks")
                        .param("id", "1")
                        .accept("application/json"))
                        .andExpect(status().isOk());
        String content = result.andReturn().getResponse().getContentAsString();
        System.out.println(content);
    }

    @Test
    void shouldReturnAllTasks() throws Exception {
        ResultActions result = this.mockmvc.perform(MockMvcRequestBuilders.get("/tasks")
                        .accept("application/json"))
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(7)))
                .andExpect(MockMvcResultMatchers.status().isOk());
        String content = result.andReturn().getResponse().getContentAsString();
        System.out.print(content);

    }

    @Test
    void shouldAddTask() throws Exception {
        this.mockmvc.perform(post("/tasks")
                .content(String.valueOf(MediaType.APPLICATION_JSON))
                .content("{\"id\": \"100\", \"desc\":\"testadd\", \"date\":\"7/11/2022\"," +
                        " \"location\":\"home\", \"group\":\"work\"}"))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    void shouldRemoveTask() throws Exception {
        this.mockmvc.perform(MockMvcRequestBuilders.delete("/tasks/2")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

}
