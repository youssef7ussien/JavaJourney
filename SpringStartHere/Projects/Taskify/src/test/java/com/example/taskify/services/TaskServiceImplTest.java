package com.example.taskify.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.dao.EmptyResultDataAccessException;

import com.example.taskify.dto.TaskDTO;
import com.example.taskify.exceptions.TaskNotFoundException;
import com.example.taskify.models.Task;
import com.example.taskify.repositories.TaskRepository;

@ExtendWith(MockitoExtension.class)
public class TaskServiceImplTest {

    @Mock
    private TaskRepository taskRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private TaskServiceImpl taskService;

    @Test
    public void testGetAllTasks_ReturnsEmptyList() {
        // Arrange
        List<Task> tasksList = new ArrayList<>();
        when(taskRepository.findAll()).thenReturn(tasksList);

        // Act
        List<TaskDTO> result = taskService.getAllTasks();

        // Assert
        assertNotNull(result);
        assertEquals(0, result.size());
    }

    @Test
    public void testGetAllTasks_ReturnsTaskDTOList() {
        // Arrange
        List<Task> tasksList = createTasksList(false);
        List<TaskDTO> taskDTOs = createTaskDTOs(false);

        when(taskRepository.findAll()).thenReturn(tasksList);
        when(modelMapper.map(any(Task.class), eq(TaskDTO.class)))
                .thenReturn(taskDTOs.get(0), taskDTOs.get(1), taskDTOs.get(2));

        // Act
        List<TaskDTO> resultTasks = taskService.getAllTasks();

        // Assert
        Mockito.verify(taskRepository, times(1)).findAll();
        assertNotNull(resultTasks);
        assertEquals(tasksList.size(), resultTasks.size());
        for (int i = 0; i < tasksList.size(); i++) {
            Assertions.assertThat(resultTasks.get(i).getId()).isGreaterThan(0);
            assertEquals(tasksList.get(i).getTitle(), resultTasks.get(i).getTitle());
            assertEquals(tasksList.get(i).getDescription(), resultTasks.get(i).getDescription());
            assertNotNull(resultTasks.get(i).getCreatedDate());
            assertEquals(tasksList.get(i).isCompleted(), resultTasks.get(i).isCompleted());
        }
    }

    @Test
    public void testGetActiveTasks_ReturnsEmptyList() {
        // Arrange
        List<Task> tasksList = new ArrayList<>();
        when(taskRepository.findAllByCompleted(false)).thenReturn(tasksList);

        // Act
        List<TaskDTO> result = taskService.getActiveTasks();

        // Assert
        assertNotNull(result);
        assertEquals(0, result.size());
    }

    @Test
    public void testGetActiveTasks_ReturnsTaskDTOList() {
        // Arrange
        List<Task> tasksList = createTasksList(false);
        List<TaskDTO> taskDTOs = createTaskDTOs(false);

        when(taskRepository.findAllByCompleted(false)).thenReturn(tasksList);
        when(modelMapper.map(any(Task.class), eq(TaskDTO.class)))
                .thenReturn(taskDTOs.get(0), taskDTOs.get(1), taskDTOs.get(2));

        // Act
        List<TaskDTO> resultTasks = taskService.getActiveTasks();

        // Assert
        Mockito.verify(taskRepository, times(1)).findAllByCompleted(false);
        assertNotNull(resultTasks);
        assertEquals(tasksList.size(), resultTasks.size());
        for (int i = 0; i < tasksList.size(); i++) {
            Assertions.assertThat(resultTasks.get(i).getId()).isGreaterThan(0);
            assertEquals(tasksList.get(i).getTitle(), resultTasks.get(i).getTitle());
            assertEquals(tasksList.get(i).getDescription(), resultTasks.get(i).getDescription());
            assertNotNull(resultTasks.get(i).getCreatedDate());
            assertEquals(tasksList.get(i).isCompleted(), resultTasks.get(i).isCompleted());
        }
    }

    @Test
    public void testGetCompletedTasks_ReturnsEmptyList() {
        // Arrange
        List<Task> tasksList = new ArrayList<>();
        when(taskRepository.findAllByCompleted(true)).thenReturn(tasksList);

        // Act
        List<TaskDTO> result = taskService.getCompletedTasks();

        // Assert
        assertNotNull(result);
        assertEquals(0, result.size());
    }

    @Test
    public void testGetCompletedTasks_ReturnsTaskDTOList() {
        // Arrange
        List<Task> tasksList = createTasksList(true);
        List<TaskDTO> taskDTOs = createTaskDTOs(true);

        when(taskRepository.findAllByCompleted(true)).thenReturn(tasksList);
        when(modelMapper.map(any(Task.class), eq(TaskDTO.class)))
                .thenReturn(taskDTOs.get(0), taskDTOs.get(1), taskDTOs.get(2));

        // Act
        List<TaskDTO> resultTasks = taskService.getCompletedTasks();

        // Assert
        Mockito.verify(taskRepository, times(1)).findAllByCompleted(true);
        assertNotNull(resultTasks);
        assertEquals(tasksList.size(), resultTasks.size());
        for (int i = 0; i < tasksList.size(); i++) {
            Assertions.assertThat(resultTasks.get(i).getId()).isGreaterThan(0);
            assertEquals(tasksList.get(i).getTitle(), resultTasks.get(i).getTitle());
            assertEquals(tasksList.get(i).getDescription(), resultTasks.get(i).getDescription());
            assertNotNull(resultTasks.get(i).getCreatedDate());
            assertEquals(tasksList.get(i).isCompleted(), resultTasks.get(i).isCompleted());
        }
    }

    @Test
    public void testGetTaskById_TaskFound_ReturnsTaskDTO() {
        // Arrange
        Long taskId = 1L;
        Task testTask = Task.builder().title("Title 1").description("Description 1").build();
        when(taskRepository.findById(taskId)).thenReturn(Optional.of(testTask));

        TaskDTO taskDTO = TaskDTO.builder().id(taskId).title("Task 1").description("Description 1").completed(false)
                .build();
        when(modelMapper.map(testTask, TaskDTO.class)).thenReturn(taskDTO);

        // Act
        TaskDTO result = taskService.getTaskById(taskId);

        // Assert
        assertNotNull(result);
        assertEquals(taskDTO, result);
    }

    @Test
    public void testGetTaskById_TaskNotFound_ThrowsException() {
        // Arrange
        Long taskId = 1L;
        when(taskRepository.findById(taskId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(TaskNotFoundException.class, () -> taskService.getTaskById(taskId));
    }

    @Test
    public void testCreateTask_SuccessfullyCreated_ReturnsTaskDTO() {
        // Arrange
        TaskDTO taskDTO = TaskDTO.builder().title("Title 1").description("Description 1").build();
        Task testTask = Task.builder().title("Title 1").description("Description 1").build();

        Task savedTask = Task.builder().id(1L).title("Title 1").description("Description 1").build();
        TaskDTO savedTaskDTO = TaskDTO.builder().id(1L).title("Title 1").description("Description 1").build();

        when(modelMapper.map(taskDTO, Task.class)).thenReturn(testTask);
        when(taskRepository.save(testTask)).thenReturn(savedTask);
        when(modelMapper.map(savedTask, TaskDTO.class)).thenReturn(savedTaskDTO);

        // Act
        TaskDTO result = taskService.createTask(taskDTO);

        // Assert
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Title 1", result.getTitle());
        assertEquals("Description 1", result.getDescription());
        assertFalse(result.isCompleted());
    }

    @Test
    public void testDeleteTask_TaskExists_DeletesTask() {
        // Arrange
        Long taskId = 1L;

        // Act
        taskService.deleteTask(taskId);

        // Assert
        verify(taskRepository, times(1)).deleteById(taskId);
    }

    @Test
    public void testDeleteTask_TaskDoesNotExist_ThrowsException() {
        // Arrange
        Long taskId = 1L;
        doThrow(EmptyResultDataAccessException.class).when(taskRepository).deleteById(taskId);

        // Act & Assert
        assertThrows(EmptyResultDataAccessException.class, () -> taskService.deleteTask(taskId));
    }

    @Test
    public void testDeleteCompletedTasks() {
        // Mock behavior
        doNothing().when(taskRepository).deleteByCompleted(true);

        // Call the method under test
        taskService.deleteCompletedTasks();

        // Verify that the method was called
        verify(taskRepository, times(1)).deleteByCompleted(true);
    }

    @Test
    public void testCountAllTasks_ReturnsCount() {
        // Arrange
        long expectedCount = 5L;
        when(taskRepository.count()).thenReturn(expectedCount);

        // Act
        long result = taskService.countAllTasks();

        // Assert
        assertEquals(expectedCount, result);
    }

    @Test
    public void testCountActiveTasks_ReturnsCount() {
        // Arrange
        long expectedCount = 3L;
        when(taskRepository.countAllByCompleted(false)).thenReturn(expectedCount);

        // Act
        long result = taskService.countActiveTasks();

        // Assert
        assertEquals(expectedCount, result);
    }

    @Test
    public void testCountCompletedTasks_ReturnsCount() {
        // Arrange
        long expectedCount = 2L;
        when(taskRepository.countAllByCompleted(true)).thenReturn(expectedCount);

        // Act
        long result = taskService.countCompletedTasks();

        // Assert
        assertEquals(expectedCount, result);
    }

    @Test
    public void testToggleTaskStatus_TaskExists_ToggledSuccessfully() {
        // Arrange
        Long taskId = 1L;

        Task testTask = Task.builder().title("Title 1").description("Description 1").build();
        Task updatedTask = Task.builder().title("Title 1").description("Description 1").completed(true).build();
        TaskDTO taskDTO = TaskDTO.builder().title("Title 1").description("Description 1").completed(true).build();

        when(taskRepository.findById(taskId)).thenReturn(Optional.of(testTask));
        when(taskRepository.save(testTask)).thenReturn(updatedTask);
        when(modelMapper.map(updatedTask, TaskDTO.class)).thenReturn(taskDTO);

        // Act
        TaskDTO result = taskService.toggleTaskStatus(taskId);

        // Assert
        assertNotNull(result);
        assertTrue(result.isCompleted());
    }

    @Test
    public void testToggleTaskStatus_TaskNotFound_ThrowsException() {
        // Arrange
        Long taskId = 1L;
        when(taskRepository.findById(taskId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(TaskNotFoundException.class, () -> taskService.toggleTaskStatus(taskId));
    }

    @Test
    public void testToggleAllTasksStatus_WithActiveTasks_TogglesToCompleted() {
        // Arrange
        List<Task> activeTasks = createTasksList(false);
        List<TaskDTO> taskDTOs = createTaskDTOs(true);

        when(taskRepository.findAll()).thenReturn(activeTasks);
        when(taskRepository.saveAll(any())).thenReturn(activeTasks);
        when(modelMapper.map(any(Task.class), eq(TaskDTO.class)))
                .thenReturn(taskDTOs.get(0), taskDTOs.get(1), taskDTOs.get(2));

        // Act
        List<TaskDTO> result = taskService.toggleAllTasksStatus();

        // Assert
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertTrue(result.stream().allMatch(TaskDTO::isCompleted));
    }

    @Test
    public void testToggleAllTasksStatus_WithCompletedTasks_TogglesToActive() {
        // Arrange
        List<Task> completedTasks = createTasksList(true);
        List<TaskDTO> taskDTOs = createTaskDTOs(false);

        when(taskRepository.findAll()).thenReturn(completedTasks);
        when(taskRepository.saveAll(any())).thenReturn(completedTasks);
        when(modelMapper.map(any(Task.class), eq(TaskDTO.class)))
                .thenReturn(taskDTOs.get(0), taskDTOs.get(1), taskDTOs.get(2));

        // Act
        List<TaskDTO> result = taskService.toggleAllTasksStatus();

        // Assert
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertTrue(result.stream().noneMatch(TaskDTO::isCompleted));
    }

    @Test
    public void testToggleAllTasksStatus_NoTasks_ReturnsEmptyList() {
        // Arrange
        List<Task> tasks = new ArrayList<>();
        when(taskRepository.findAll()).thenReturn(tasks);

        // Act
        List<TaskDTO> result = taskService.toggleAllTasksStatus();

        // Assert
        assertTrue(result.isEmpty());
    }

    private List<Task> createTasksList(boolean completed) {
        List<Task> tasksList = new ArrayList<>();
        for (int i = 1; i <= 3; i++)
            tasksList.add(Task.builder().title("Title " + i)
                    .description("Description " + i).completed(completed).build());

        return tasksList;
    }

    private List<TaskDTO> createTaskDTOs(boolean completed) {
        List<TaskDTO> taskDTOs = new ArrayList<>();

        for (int i = 1; i <= 3; i++)
            taskDTOs.add(TaskDTO.builder().id(1L * i).title("Title " + i).description("Description " + i)
                    .createdDate(LocalDateTime.now()).completed(completed).build());

        return taskDTOs;
    }
}
