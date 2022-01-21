package com.taskbe.taskbe.repository;

import com.taskbe.taskbe.entity.TaskEntity;
import com.taskbe.taskbe.entity.TaskPriority;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface TaskRepository extends JpaRepository<TaskEntity, Integer> {
    List<TaskEntity> findAllByCompleted(boolean completed, Sort sort);

    List<TaskEntity> findAllByPriority(TaskPriority priority, Sort sort);

    List<TaskEntity> findAllByCompletedAndPriority(boolean completed, TaskPriority priority, Sort sort);
}
