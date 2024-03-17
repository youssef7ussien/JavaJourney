package com.example.taskify.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.taskify.dto.TaskDTO;
import com.example.taskify.exceptions.TaskNotFoundException;
import com.example.taskify.models.Task;
import com.example.taskify.repositories.TaskRepository;

@Service
@Transactional
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final ModelMapper modelMapper;

    public TaskServiceImpl(TaskRepository taskRepository, ModelMapper modelMapper) {
        this.taskRepository = taskRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<TaskDTO> getAllTasks() {
        List<Task> tasks = taskRepository.findAll();
        return tasks.stream()
                .map(task -> modelMapper.map(task, TaskDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<TaskDTO> getActiveTasks() {
        List<Task> tasks = taskRepository.findAllByCompleted(false);
        return tasks.stream()
                .map(task -> modelMapper.map(task, TaskDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<TaskDTO> getCompletedTasks() {
        List<Task> tasks = taskRepository.findAllByCompleted(true);
        return tasks.stream()
                .map(task -> modelMapper.map(task, TaskDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public TaskDTO getTaskById(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException("Task not found with id '" + id + "'"));

        return modelMapper.map(task, TaskDTO.class);
    }

    @Override
    public TaskDTO createTask(TaskDTO taskDTO) {
        Task task = modelMapper.map(taskDTO, Task.class);
        Task savedTask = taskRepository.save(task);
        return modelMapper.map(savedTask, TaskDTO.class);
    }

    @Override
    public TaskDTO updateTask(Long id, TaskDTO updatedTaskDTO) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(
                        "Task not found with id '" + updatedTaskDTO.getId() + "'"));

        modelMapper.map(updatedTaskDTO, task);

        Task updatedTask = taskRepository.save(task);

        return modelMapper.map(updatedTask, TaskDTO.class);
    }

    @Override
    public void deleteTask(Long id) {
        try {
            taskRepository.deleteById(id);
        } catch (EmptyResultDataAccessException ex) {
            throw new EmptyResultDataAccessException("Task not found with id: " + id, 1);
        }
    }

    @Override
    public void deleteCompletedTasks() {
        taskRepository.deleteByCompleted(true);
    }

    @Override
    public long countAllTasks() {
        return taskRepository.count();
    }

    @Override
    public long countActiveTasks() {
        return taskRepository.countAllByCompleted(false);
    }

    @Override
    public long countCompletedTasks() {
        return taskRepository.countAllByCompleted(true);
    }

    @Override
    public TaskDTO toggleTaskStatus(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(
                        "Task not found with id '" + id + "'"));

        task.setCompleted(!task.isCompleted());
        task.setCompletedDate(LocalDateTime.now());

        task = taskRepository.save(task);

        return modelMapper.map(task, TaskDTO.class);
    }

    @Override
    public List<TaskDTO> toggleAllTasksStatus() {
        List<Task> tasks = taskRepository.findAll();

        if (!tasks.isEmpty()) {
            boolean hasActiveTasks = tasks.stream().anyMatch(task -> !task.isCompleted());

            tasks.forEach(task -> task.setCompleted(hasActiveTasks));

            tasks = taskRepository.saveAll(tasks);
        }

        return tasks.stream()
                .map(task -> modelMapper.map(task, TaskDTO.class))
                .collect(Collectors.toList());
    }

}
