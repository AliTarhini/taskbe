package com.taskbe.taskbe.service;

import com.taskbe.taskbe.entity.TaskEntity;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
class TaskServiceTest {

    @Autowired
    private TaskService taskService;

    @Test
    void getTask() {
        TaskEntity task = taskService.getTask(1);
        assertEquals("testing1", task.getDescription());
    }
}