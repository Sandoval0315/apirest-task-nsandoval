
package com.nsandoval.apiresttask.service;

import org.springframework.stereotype.Service;
import com.nsandoval.apiresttask.model.Task;
import com.nsandoval.apiresttask.repository.TaskRepository;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    // Obtener todas las tareas
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    // Obtener una tarea por ID
    public Task getTaskById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tarea no encontrada: " + id));
    }

    // Crear una tarea
    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    // Actualizar una tarea
    public Task updateTask(Long id, Task updatedTask) {
        Task existingTask = getTaskById(id);

        existingTask.setTitle(updatedTask.getTitle());
        existingTask.setDescription(updatedTask.getDescription());
        existingTask.setIsCompleted(updatedTask.getIsCompleted());

        return taskRepository.save(existingTask);
    }

    // Eliminar una tarea
    public void deleteTask(Long id) {
        Task task = getTaskById(id);
        taskRepository.delete(task);
    }
}
