package com.alem.todolist;


import com.alem.todolist.controller.ToDoListController;
import com.alem.todolist.exceptions.InvalidGroupException;
import com.alem.todolist.model.GroupType;
import com.alem.todolist.model.Task;
import com.alem.todolist.repository.ToDoListRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.hasValue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(ToDoListController.class)
@AutoConfigureMockMvc
public class ToDoListControllerTest {

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private ToDoListRepository toDoListRepository;
    @InjectMocks
    private ToDoListController toDoListController = new ToDoListController(toDoListRepository);

    @Autowired
    private MockMvc mockmvc;

    //  // task: id, desc, date, location, group
    @BeforeEach
    void setup() throws InvalidGroupException {
        long now = System.currentTimeMillis();
        Date currentDate = new Date(now);
        MockitoAnnotations.openMocks(this);
        Task test1 = new Task();
        Task test2 = new Task();
        Task test3 = new Task();
        test1.setId(1L);
        test1.setDesc("test11");
        LocalDate ld = LocalDate.parse("2022-10-26");
        test1.setDate(ld.atStartOfDay(ZoneId.of("Europe/Ljubljana")).toInstant());
        test1.setLocation("home");
        test1.setGroup(GroupType.valueOf("PERSONAL"));
        test2.setId(2L);
        test2.setDesc("test22");
        test2.setDate(ld.atStartOfDay(ZoneId.of("Europe/Ljubljana")).toInstant());
        test2.setLocation("home");
        test2.setGroup(GroupType.valueOf("WORK"));
        test3.setId(3L);
        test3.setDesc("test33");
        test3.setDate(ld.atStartOfDay(ZoneId.of("Europe/Ljubljana")).toInstant());
        test3.setLocation("home");
        test3.setGroup(GroupType.valueOf("STUDY"));
        List<Task> tasks = Arrays.asList(test1, test2, test3);
        when(toDoListRepository.findAll()).thenReturn(tasks);
        toDoListController.addTask(test1);
        toDoListController.addTask(test2);
        toDoListController.addTask(test3);
    }

    @Test
    void shouldCreateMockMvc() {
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

        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"));
        long now = System.currentTimeMillis();
        Task task = new Task();
        task.setId(55L);
        task.setDesc("testadding");
        LocalDate ld = LocalDate.parse("2022-10-26");
        task.setDate(ld.atStartOfDay(ZoneId.of("Europe/Ljubljana")).toInstant());
        task.setGroup(GroupType.valueOf("WORK"));
        task.setLocation("home");
        when(toDoListRepository.save(any(Task.class))).thenReturn(task);
        this.mockmvc.perform(
                        MockMvcRequestBuilders.post("/tasks").content(mapper.writeValueAsString(task)).
                                contentType(MediaType.APPLICATION_JSON_VALUE).accept("application/json"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"));
    }

    @Test
    void shouldRemoveTask() throws Exception {

        ResultActions result = this.mockmvc.perform(MockMvcRequestBuilders.delete("/tasks/2")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
        String content = result.andReturn().getResponse().getContentAsString();
        System.out.print(content);
    }

    @Test
    void shouldPrintTasksForGivenGroup() throws Exception {
        Task testgroup1 = new Task();
        testgroup1.setId(1L);
        testgroup1.setDesc("test11");
        LocalDate ld = LocalDate.parse("2022-10-26");
        testgroup1.setDate(ld.atStartOfDay(ZoneId.of("Europe/Ljubljana")).toInstant());
        testgroup1.setLocation("home");
        testgroup1.setGroup(GroupType.valueOf("PERSONAL"));
        List<Task> tasksgrp = List.of(testgroup1);
        when(toDoListRepository.getTasksForGivenGroup(anyString())).thenReturn(tasksgrp);
        this.mockmvc.perform(MockMvcRequestBuilders.get("/tasks/forGroup/PERSONAL").contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    void shouldPrintTasksForGivenDate() throws Exception {
        Task testdate = new Task();
        testdate.setId(1L);
        testdate.setDesc("test11");
        LocalDate ld = LocalDate.parse("2022-10-26");
        testdate.setDate(ld.atStartOfDay(ZoneId.of("Europe/Ljubljana")).toInstant());
        testdate.setLocation("home");
        testdate.setGroup(GroupType.valueOf("PERSONAL"));
        List<Task> testdates = List.of(testdate);
        when(toDoListRepository.getTasksForGivenDate(any(Instant.class))).thenReturn(testdates);
        this.mockmvc.perform(MockMvcRequestBuilders.get("/tasks/forDay/2022_10_26").contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$", hasSize(1)));

    }

}
