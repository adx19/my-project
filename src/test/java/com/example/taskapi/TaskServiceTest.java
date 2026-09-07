package com.example.taskapi;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskServiceTest {

    @Test
    void shouldGetAllTasks() {
        TaskService service = new TaskService();

        assertEquals(2, service.getAllTasks().size());
    }

    @Test
    void shouldCreateTask() {
        TaskService service = new TaskService();

        Task task = new Task(null, "Learn Docker", false);
        Task createdTask = service.createTask(task);

        assertNotNull(createdTask.getId());
        assertEquals("Learn Docker", createdTask.getTitle());
        assertEquals(3, service.getAllTasks().size());
    }

    @Test
    void shouldFindTaskById() {
        TaskService service = new TaskService();

        Task task = service.getTaskById(1L);

        assertNotNull(task);
        assertEquals("Learn Gradle", task.getTitle());
    }

    @Test
    void shouldUpdateTask() {
        TaskService service = new TaskService();

        Task updated = service.updateTask(
                1L,
                new Task(null, "Learn CI/CD", true)
        );

        assertNotNull(updated);
        assertEquals("Learn CI/CD", updated.getTitle());
        assertTrue(updated.isCompleted());
    }

    @Test
    void shouldDeleteTask() {
        TaskService service = new TaskService();

        boolean deleted = service.deleteTask(1L);

        assertTrue(deleted);
        assertNull(service.getTaskById(1L));
    }
}