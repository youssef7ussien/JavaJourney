package com.example.taskify.services;

import java.util.List;

import com.example.taskify.dto.TaskDTO;

public interface TaskService {

    /**
     * Retrieves all tasks.
     * 
     * @return a list of tasks
     */
    List<TaskDTO> getAllTasks();

    /**
     * Retrieves only active (not completed) tasks.
     * 
     * @return a list of tasks
     */
    List<TaskDTO> getActiveTasks();

    /**
     * Retrieves completed tasks.
     * 
     * @return a list of tasks
     */
    List<TaskDTO> getCompletedTasks();

    /**
     * Retrieves a task by its ID.
     * 
     * @param id the ID of the task to retrieve.
     * @return the task with given Id.
     * @throws TaskNotFoundException if no task found with the given ID.
     */
    TaskDTO getTaskById(Long id);

    /**
     * Creates a new task.
     * 
     * @param taskDTO the task DTO to create
     * @return the created task DTO
     */
    TaskDTO createTask(TaskDTO taskDTO);

    /**
     * Updates an existing task.
     * 
     * @param id             the ID of the task to update
     * @param updatedTaskDTO the updated task DTO
     * @return the updated task DTO
     */
    TaskDTO updateTask(Long id, TaskDTO taskDTO);

    /**
     * Deletes a task by its ID.
     *
     * @param id the ID of the task to delete
     * @throws EmptyResultDataAccessException if the task with the specified ID is
     *                                        not found
     */
    void deleteTask(Long id);

    /**
     * Deletes all completed tasks from the database.
     * This operation is transactional and will be rolled back in case of any errors.
     */
    void deleteCompletedTasks();

    /**
     * Counts all tasks.
     * 
     * @return the total number of tasks.
     */
    public long countAllTasks();

    /**
     * Counts active tasks.
     * 
     * @return the number of active tasks.
     */
    public long countActiveTasks();

    /**
     * Counts completed tasks.
     * 
     * @return the number of completed tasks.
     */
    public long countCompletedTasks();

    /**
     * Toggles a task's status from active to completed and vice versa.
     * 
     * @param taskId the ID of the task to toggle
     * @return the updated task
     * @throws TaskNotFoundException if the task with the specified ID is not found
     */
    public TaskDTO toggleTaskStatus(Long id);

    /**
     * Toggles the status of all tasks. If there are no active tasks, it toggles all
     * tasks to active.
     * 
     * @return the list of updated tasks
     */
    public List<TaskDTO> toggleAllTasksStatus();
}