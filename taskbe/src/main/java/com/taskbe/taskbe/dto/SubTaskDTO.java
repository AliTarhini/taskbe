package com.taskbe.taskbe.dto;

import com.taskbe.taskbe.entity.TaskPriority;
import lombok.Data;

import java.io.Serializable;

@Data
public class SubTaskDTO implements Serializable {
    private int id;

    private String description;

    private boolean completed;

    private TaskPriority priority;
}
