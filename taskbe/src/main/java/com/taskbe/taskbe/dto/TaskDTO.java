package com.taskbe.taskbe.dto;

import com.taskbe.taskbe.entity.TaskPriority;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Data
public class TaskDTO implements Serializable {
    private int id;

    private String description;

    private boolean completed;

    private TaskPriority priority;

    private LocalDate creationDate;

    private List<SubTaskDTO> subtasks;
}
