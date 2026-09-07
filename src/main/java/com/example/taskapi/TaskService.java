package com.example.taskapi;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class TaskService {

    private final List<Task> tasks = new ArrayList<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    public TaskService() {
        tasks.add(new Task(idGenerator.getAndIncrement(), "Learn Gradle", false));
        tasks.add(new Task(idGenerator.getAndIncrement(), "Build CI pipeline", false));
    }

    public List<Task> getAllTasks() {
        return tasks;
    }

    public Task getTaskById(Long id) {
        return tasks.stream()
                .filter(task -> task.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public Task createTask(Task task) {
        task.setId(idGenerator.getAndIncrement());
        tasks.add(task);
        return task;
    }

    public Task updateTask(Long id, Task updatedTask) {
        Task existingTask = getTaskById(id);

        if (existingTask == null) {
            return null;
        }

        existingTask.setTitle(updatedTask.getTitle());
        existingTask.setCompleted(updatedTask.isCompleted());

        return existingTask;
    }

    public boolean deleteTask(Long id) {
        return tasks.removeIf(task -> task.getId().equals(id));
    }
}