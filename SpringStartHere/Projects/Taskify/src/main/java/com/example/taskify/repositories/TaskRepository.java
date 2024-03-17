package com.example.taskify.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.taskify.models.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {

    /**
     * Retrieves tasks based on their completion status.
     * 
     * @param completed the completion status of tasks to retrieve
     * @return a list of tasks with the specified completion status
     */
    List<Task> findAllByCompleted(boolean completed);

    /**
     * Counts tasks based on their completion status.
     * 
     * @param completed the completion status of tasks to count
     * @return the number of tasks with the specified completion status
     */
    long countAllByCompleted(boolean completed);

    /**
     * Deletes tasks by their completion status.
     * 
     * @param completed the completion status of the tasks to be deleted
     */
    void deleteByCompleted(boolean completed);
}
