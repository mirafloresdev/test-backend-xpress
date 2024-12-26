package com.test.xpress.controller;

import com.test.xpress.config.ApiResponse;
import com.test.xpress.model.Task;
import com.test.xpress.model.User;
import com.test.xpress.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/task")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    // crear una task
    @PostMapping
    public ResponseEntity<ApiResponse<Task>> createTask(@RequestBody Task task) {
        Optional<Task> createdTask = taskService.createTask(task);
        if (createdTask.isPresent()) {
            ApiResponse<Task> apiResponse = new ApiResponse<>(
                    Collections.emptyList(),
                    "SUCCESS",
                    createdTask.get()
            );
            return ResponseEntity.ok(apiResponse);
        } else {
            ApiResponse<Task> apiResponse = new ApiResponse<>(
                    Collections.singletonList("Creación de tarea fallida, verifique los datos"),
                    "FAILURE",
                    null
            );
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
        }
    }



    // obtener tareas por usuario
    @GetMapping("/{user}")
    public ResponseEntity<ApiResponse<List<Task>>> getTasks(@PathVariable Integer user) {
        List<Task> tasks = this.taskService.getAllTasks(user);
        if (tasks.isEmpty()) {
            ApiResponse<List<Task>> apiResponse = new ApiResponse<>(
                    Collections.singletonList("No existen tareas para este usuario aun creadas..."),
                    "SUCCESS",
                    tasks
            );
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
        }else{
            ApiResponse<List<Task>> apiResponse = new ApiResponse<>(
                    Collections.emptyList(),
                    "SUCCESS",
                    tasks
            );
            return ResponseEntity.ok(apiResponse);
        }
    }


    // actualizar task
    @PutMapping("/{user}")
    public ResponseEntity<ApiResponse<Task>> updateTask(@PathVariable Integer user, @RequestBody Task task) {
        Optional<Task> updatedTask = this.taskService.updateTask(task);
        if (updatedTask.isPresent()) {
            ApiResponse<Task> apiResponse = new ApiResponse<>(
                    Collections.emptyList(),
                    "SUCCESS",
                    updatedTask.get()
            );
            return ResponseEntity.ok(apiResponse);
        }else{
            ApiResponse<Task> apiResponse = new ApiResponse<>(
                    Collections.singletonList("Error al actualizar tarea..."),
                    "FAIL",
                    null
            );
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteTask(@PathVariable Integer id) {
        boolean isDeleted = taskService.deleteTask(id);
        if (!isDeleted) {
            ApiResponse<Void> apiResponse = new ApiResponse<>(
                    Collections.emptyList(),
                    "SUCCESS",
                    null
            );
            return ResponseEntity.ok(apiResponse);
        } else {
            ApiResponse<Void> apiResponse = new ApiResponse<>(
                    Collections.singletonList("Eliminación de tarea fallida o tarea no encontrada"),
                    "FAIL",
                    null
            );
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponse);
        }
    }

}
