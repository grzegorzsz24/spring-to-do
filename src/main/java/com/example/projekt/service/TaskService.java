package com.example.projekt.service;

import com.example.projekt.model.Task;
import com.example.projekt.model.User;
import com.example.projekt.repository.TaskRepository;
import com.example.projekt.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TaskService {
    TaskRepository taskRepository;
    UserRepository userRepository;

    public TaskService(TaskRepository taskRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    public Optional<Task> getTaskById(Long id) {
        return taskRepository.findById(id);
    }

    public Task saveTask(Task task, Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            task.setUser(user);
            taskRepository.save(task);
            return task;
        } else {
            throw new IllegalArgumentException("Użytkownik o podanym id nie istnieje");
        }
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    public Optional<Task> replaceTask(Long taskId, Task task) {
        if (!taskRepository.existsById(taskId)) {
            return Optional.empty();
        }
        task.setId(taskId);
        taskRepository.save(task);
        return Optional.of(task);
    }
}
