package com.example.taskify.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.example.taskify.dto.TaskDTO;
import com.example.taskify.services.TaskService;

import jakarta.validation.Valid;

@Controller
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    public enum ListFilter {
        ALL,
        ACTIVE,
        COMPLETED
    }

    @GetMapping
    public String getAllTasks(Model model) {
        addAttributesForIndex(model, ListFilter.ALL, taskService.getAllTasks());
        return "index.html";
    }

    @GetMapping("/active")
    public String getActiveTasks(Model model) {
        addAttributesForIndex(model, ListFilter.ACTIVE, taskService.getActiveTasks());
        return "index.html";
    }

    @GetMapping("/completed")
    public String getCompletedTasks(Model model) {
        addAttributesForIndex(model, ListFilter.COMPLETED, taskService.getCompletedTasks());
        return "index.html";
    }


    private void addAttributesForIndex(Model model, ListFilter listFilter, List<TaskDTO> tasksDTO) {
        model.addAttribute("newTask", new TaskDTO());
        model.addAttribute("filter", listFilter);
        model.addAttribute("tasks", tasksDTO);
        model.addAttribute("countAllTasks", taskService.countAllTasks());
        model.addAttribute("countActiveTasks", taskService.countActiveTasks());
        model.addAttribute("countCompletedTasks", taskService.countCompletedTasks());
    }

    @PostMapping
    public String createTask(@Valid @ModelAttribute TaskDTO taskDTO) {
        taskService.createTask(taskDTO);
        return "redirect:/";
    }

    @PutMapping("/toggle/{id}")
    public String toggleTaskStatus(@PathVariable Long id) {
        taskService.toggleTaskStatus(id);
        return "redirect:/";
    }

    @PutMapping("/toggle-all")
    public String toggleAll() {
        taskService.toggleAllTasksStatus();
        return "redirect:/";
    }

    @DeleteMapping("/{id}")
    public String deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return "redirect:/";
    }

    @DeleteMapping("/completed")
    public String deleteCompletedTasks() {
        taskService.deleteCompletedTasks();
        return "redirect:/";
    }
}