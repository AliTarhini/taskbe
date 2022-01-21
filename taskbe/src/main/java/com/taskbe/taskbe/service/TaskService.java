package com.taskbe.taskbe.service;

import com.taskbe.taskbe.entity.TaskEntity;
import com.taskbe.taskbe.entity.TaskPriority;
import com.taskbe.taskbe.exception.TaskNotFoundException;
import com.taskbe.taskbe.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class TaskService {
    @Autowired
    TaskRepository taskRepository;

    public TaskEntity getTask(int taskId) throws TaskNotFoundException {
        return taskRepository.findById(taskId).orElseThrow(() ->
                new TaskNotFoundException(String.format("Task with id %d not found", taskId)));
    }

    public List<TaskEntity> getAllTasks(Pageable pageable, Boolean completed, TaskPriority priority) {
        if (!Objects.isNull(completed) && !Objects.isNull(priority)) {
            return taskRepository.findAllByCompletedAndPriority(completed, priority, pageable.getSort());
        }

        if (!Objects.isNull(completed)) {
            return taskRepository.findAllByCompleted(completed, pageable.getSort());
        }

        if (!Objects.isNull(priority)) {
            return taskRepository.findAllByPriority(priority, pageable.getSort());
        }

        return taskRepository.findAll(pageable.getSort());
    }

    public TaskEntity addTask(TaskEntity task) {
        return taskRepository.save(task);
    }

    public void deleteTask(int taskId) throws TaskNotFoundException {
        if (taskRepository.existsById(taskId)) {
            taskRepository.deleteById(taskId);
        } else {
            throw new TaskNotFoundException(String.format("Task with id %d not found", taskId));
        }
    }

    public TaskEntity editTask(TaskEntity task, int taskId) throws TaskNotFoundException {
        if (taskRepository.existsById(taskId) && task.getId() == taskId) {
            return taskRepository.save(task);
        } else {
            throw new TaskNotFoundException(String.format("Task with id %d not found", taskId));
        }
    }
}
