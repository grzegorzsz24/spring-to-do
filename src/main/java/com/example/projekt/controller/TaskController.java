package com.example.projekt.controller;

import com.example.projekt.model.Task;
import com.example.projekt.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping()
public class TaskController {
    TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }
    @GetMapping("tasks/{id}")
    ResponseEntity<Task> getTaskById(@PathVariable Long id) {
        return taskService.getTaskById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @PostMapping("/users/{id}/tasks")
    ResponseEntity<Task> saveTask(@PathVariable Long id, @RequestBody Task task) {
        Task savedTask = taskService.saveTask(task, id);
        URI savedTaskUri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("{id}")
                .buildAndExpand(savedTask.getId())
                .toUri();
        return ResponseEntity.created(savedTaskUri).body(savedTask);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteTaskById(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/tasks/{id}")
    ResponseEntity<?> replaceTask(@PathVariable Long id, @RequestBody Task task) {
        return taskService.replaceTask(id, task)
                .map(t -> ResponseEntity.noContent().build())
                .orElse(ResponseEntity.notFound().build());
    }
}
