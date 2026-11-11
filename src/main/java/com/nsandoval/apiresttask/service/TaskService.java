
package com.nsandoval.apiresttask.service;

import org.springframework.stereotype.Service;
import com.nsandoval.apiresttask.model.Task;
import com.nsandoval.apiresttask.repository.TaskRepository;
import com.nsandoval.apiresttask.validation.TareaNoEncontradaException;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    // obtener las tareas
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    // obtener tarea por id
    public Task getTaskById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new TareaNoEncontradaException(id));
    }

    // crear una tarea nueva
    public Task createTask(Task task) {
        if (task.getIsCompleted() == null) {
            task.setIsCompleted(false);
        }
        return taskRepository.save(task);
    }

    // actualizar una tarea
    public Task updateTask(Long id, Task updatedTask) {
        Task existingTask = getTaskById(id);

        existingTask.setTitle(updatedTask.getTitle());
        existingTask.setDescription(updatedTask.getDescription());
        existingTask.setIsCompleted(Boolean.TRUE.equals(updatedTask.getIsCompleted()));

        return taskRepository.save(existingTask);
    }

    // eliminar una tarea
    public void deleteTask(Long id) {
        Task task = getTaskById(id);
        taskRepository.delete(task);
    }
}
