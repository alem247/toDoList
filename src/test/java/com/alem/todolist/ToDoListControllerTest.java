package com.alem.todolist;


import com.alem.todolist.controller.ToDoListController;
import com.alem.todolist.exceptions.MissingArgumentException;
import com.alem.todolist.model.Task;
import com.alem.todolist.repository.ToDoListRepository;
import com.alem.todolist.service.ToDoListService;
import com.alem.todolist.service.ToDoListServiceImpl;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
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
    void setup() throws MissingArgumentException {
        long now = System.currentTimeMillis();
        Date currentDate = new Date(now);
        MockitoAnnotations.openMocks(this);
        Task test1 = new Task();
        Task test2 = new Task();
        Task test3 = new Task();
        test1.setId(1L);
        test1.setDesc("test11");
        test1.setDate(currentDate.toInstant());
        test1.setLocation("home");
        test1.setGroup("work");
        test2.setId(2L);
        test2.setDesc("test22");
        test2.setDate(currentDate.toInstant());
        test2.setLocation("home");
        test2.setGroup("work");
        test3.setId(3L);
        test3.setDesc("test33");
        test3.setDate(currentDate.toInstant());
        test3.setLocation("home");
        test3.setGroup("work");
        List<Task> tasks = new ArrayList<>();
        tasks.add(test1);
        tasks.add(test2);
        tasks.add(test3);
        when(toDoListRepository.findAll()).thenReturn(tasks);
        toDoListController.addTask(test1);
        toDoListController.addTask(test2);
        toDoListController.addTask(test3);
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
        this.mockmvc.perform(MockMvcRequestBuilders.get("/tasks")
                        .accept("application/json"))
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(3)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void shouldAddTask() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        mapper.registerModule(javaTimeModule);
        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"));
        long now = System.currentTimeMillis();
        Date currentDate = new Date(now);
        Task task = new Task();
        task.setId(55L);
        task.setDesc("testadding");
        task.setDate(currentDate.toInstant());
        task.setGroup("work");
        task.setLocation("home");
        when(toDoListRepository.save(any(Task.class))).thenReturn(task);
        this.mockmvc.perform(MockMvcRequestBuilders.post("/tasks")
                .content(mapper.writeValueAsString(task)).contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept("application/json"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"));
    }

    @Test
    void shouldRemoveTask() throws Exception {

        ResultActions result =  this.mockmvc.perform(MockMvcRequestBuilders.delete("/tasks/2")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
        String content = result.andReturn().getResponse().getContentAsString();
        System.out.print(content);
    }

}
