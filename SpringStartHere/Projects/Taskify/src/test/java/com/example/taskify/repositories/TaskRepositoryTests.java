package com.example.taskify.repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.example.taskify.models.Task;

@DataJpaTest
@EnableJpaAuditing
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class TaskRepositoryTests {

    @Autowired
    private TaskRepository taskRepository;

    @Test
    public void testSave_ReturnSavedTask() {
        Task task1 = Task.builder().title("Title 1").description("Description 1").build();

        Task savedTask = taskRepository.save(task1);

        Assertions.assertThat(savedTask).isNotNull();
        Assertions.assertThat(savedTask.getId()).isGreaterThan(0);
        Assertions.assertThat(savedTask.getCreatedDate()).isNotNull();
        Assertions.assertThat(savedTask.getTitle()).isNotBlank();
    }

    @Test
    public void testFindAll_returnMoreThanOne() {
        Task task1 = Task.builder().title("Title 1").description("Description 1").build();
        Task task2 = Task.builder().title("Title 2").description("Description 2").build();

        taskRepository.save(task1);
        taskRepository.save(task2);

        List<Task> tasksList = taskRepository.findAll();

        Assertions.assertThat(tasksList).isNotNull();
        Assertions.assertThat(tasksList.size()).isEqualTo(2);

    }

    @Test
    public void testFindById_returnTask() {
        Task testTask = Task.builder().title("Title 1").description("Description 1").build();

        taskRepository.save(testTask);

        Task task = taskRepository.findById(testTask.getId()).get();

        Assertions.assertThat(task).isNotNull();

    }

    @Test
    public void testDeleteById_returnTaskIsEmpty() {
        Task testTask = Task.builder().title("Title 1").description("Description 1").build();

        taskRepository.save(testTask);

        taskRepository.deleteById(testTask.getId());

        Optional<Task> task = taskRepository.findById(testTask.getId());
        Assertions.assertThat(task).isEmpty();

    }

    @Test
    public void testDeleteByCompleted() {
        List<Task> testTaskList = new ArrayList<>();
        testTaskList.add(Task.builder().title("Title 1").description("Description 1").build());       
        testTaskList.add(Task.builder().title("Title 2").description("Description 1").completed(true).build());       
        taskRepository.saveAll(testTaskList);

        // Call the method under test
        taskRepository.deleteByCompleted(true);

        // Verify that only completed tasks were deleted
        List<Task> remainingTasks = taskRepository.findAll();
        Assertions.assertThat(remainingTasks).hasSize(1);
        Assertions.assertThat(remainingTasks.get(0).getTitle()).isEqualTo("Title 2");
    }
}
