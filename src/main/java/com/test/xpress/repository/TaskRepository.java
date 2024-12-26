package com.test.xpress.repository;

import com.test.xpress.model.Task;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends CrudRepository<Task, Long> {

    // obtener todas las task por medio del userId
    public List<Task> findAllByUserId(Integer userId);
    boolean findByTaskId(Integer taskId);
    boolean deleteByTaskId(Integer taskId);
}
