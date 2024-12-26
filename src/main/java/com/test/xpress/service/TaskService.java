package com.test.xpress.service;

import com.test.xpress.model.Task;

import java.util.List;
import java.util.Optional;

public interface TaskService {

    Optional<Task> createTask(Task task);
    Task getTaskById(int id);
    List<Task> getAllTasks(Integer userId);
    Optional<Task> updateTask(Task task);
    boolean deleteTask(int id);
}
