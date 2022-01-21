package com.taskbe.taskbe.controller;


import com.taskbe.taskbe.dto.TaskDTO;
import com.taskbe.taskbe.entity.TaskEntity;
import com.taskbe.taskbe.entity.TaskPriority;
import com.taskbe.taskbe.exception.TaskNotFoundException;
import com.taskbe.taskbe.service.TaskService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("tasks")
public class TaskController {
    @Autowired
    private TaskService taskService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/{taskId}")
    public ResponseEntity<TaskDTO> getTaskById(@PathVariable int taskId) {
        return ResponseEntity.ok(entityToDto(taskService.getTask(taskId)));
    }

    @GetMapping("")
    public ResponseEntity<List<TaskDTO>> getAllTasks(Pageable pageable, @RequestParam(required = false) Boolean completed,
                                                     @RequestParam(required = false) TaskPriority priority) {
        return ResponseEntity.ok(taskService.getAllTasks(pageable, completed, priority).stream().map(this::entityToDto).collect(Collectors.toList()));
    }

    @PostMapping("")
    public ResponseEntity<TaskDTO> addTask(@RequestBody TaskEntity task) {
        return ResponseEntity.status(HttpStatus.CREATED).body((entityToDto(taskService.addTask(task))));
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<Void> deleteTask(@PathVariable int taskId) {
        taskService.deleteTask(taskId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{taskId}")
    public ResponseEntity<TaskDTO> editTask(@PathVariable int taskId, @RequestBody TaskDTO taskDTO) {
        TaskEntity task = dtoToEntity(taskDTO);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(entityToDto(taskService.editTask(task, taskId)));
    }

    @ExceptionHandler(TaskNotFoundException.class)
    public void handleAllExceptions(TaskNotFoundException ex) {
        throw new RuntimeException(ex);
    }

    private TaskDTO entityToDto(TaskEntity taskEntity) {
        return modelMapper.map(taskEntity, TaskDTO.class);
    }

    private TaskEntity dtoToEntity(TaskDTO taskDTO) {
        return modelMapper.map(taskDTO, TaskEntity.class);
    }
}
