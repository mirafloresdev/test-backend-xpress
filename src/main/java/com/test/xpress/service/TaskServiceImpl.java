package com.test.xpress.service;

import com.test.xpress.model.Task;
import com.test.xpress.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }


    @Override
    @Transactional
    public Optional<Task> createTask(Task task) {
        Optional<Task> newTask = Optional.of(taskRepository.save(task));
        if (newTask.isPresent()) {
            return Optional.of(newTask.get());
        }else{
            return Optional.empty();
        }
    }

    @Override
    public Task getTaskById(int id) {
        return null;
    }

    @Override
    public List<Task> getAllTasks(Integer userId) {
        List<Task> tasks =  this.taskRepository.findAllByUserId(userId);
        if (tasks.isEmpty()) {
            return Collections.emptyList();
        }else{
            return tasks;
        }
    }

    @Override
    public Optional<Task> updateTask(Task task) {
        Optional<Task> updatedTask = Optional.of(this.taskRepository.save(task));
        if(updatedTask.isPresent()) {
            return Optional.of(updatedTask.get());
        }else{
            return Optional.empty();
        }
    }

    @Override
    @Transactional
    public boolean deleteTask(int id) {

        Optional<Task> task = this.taskRepository.findById((long) id);
        if(!task.isPresent()) {
            return false;
        }

        try{
            this.taskRepository.deleteByTaskId(id);
            return true;
        }catch(Exception e){
            return false;
        }
    }
}
