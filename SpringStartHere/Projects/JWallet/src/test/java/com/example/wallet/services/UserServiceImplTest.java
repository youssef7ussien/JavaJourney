package com.example.wallet.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.wallet.dto.UpdateBalanceDTO;
import com.example.wallet.dto.UserDTO;
import com.example.wallet.exceptions.UserNotFoundException;
import com.example.wallet.models.User;
import com.example.wallet.repositories.UserRepository;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    private Long testId;
    private User testUser;
    private UserDTO testUserDTO;

    @BeforeEach
    public void setUp() {
        testId = 1L;
        testUser = new User(1L, "testuser", BigDecimal.valueOf(1000));
        testUserDTO = new UserDTO(1L, "testuser", BigDecimal.valueOf(1000));
    }

    @Test
    void testCreateUser() {
        // Given
        when(userRepository.save(any(User.class))).thenReturn(testUser);

        // When
        UserDTO createdUserDTO = userService.createUser(testUserDTO);

        // Then
        assertEquals(testUserDTO, createdUserDTO);
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void testGetUserById_Succes() {
        // Arrange
        when(userRepository.findById(testId)).thenReturn(Optional.of(testUser));

        // Act
        UserDTO foundUser = userService.getUserById(testId);

        // Assert
        assertNotNull(foundUser);
        assertEquals(testUserDTO.getId(), foundUser.getId());
        assertEquals(testUserDTO.getUsername(), foundUser.getUsername());
        assertEquals(testUserDTO.getBalance(), foundUser.getBalance());
    }

    @Test
    public void testGetUserById_UserNotFound() {
        // Arrange
        when(userRepository.findById(testId)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(UserNotFoundException.class, () -> userService.getUserById(testId));
    }

    @Test
    void testGetUserByUsername_Succes() {
        // Given
        String username = "testuser1";
        when(userRepository.findByUsername(username)).thenReturn(Optional.of(testUser));

        // When
        UserDTO foundUserDTO = userService.getUserByUsername(username);

        // Then
        assertNotNull(foundUserDTO);
        assertEquals(testUser.getId(), foundUserDTO.getId());
        assertEquals(testUser.getUsername(), foundUserDTO.getUsername());
        assertEquals(testUser.getBalance(), foundUserDTO.getBalance());
        verify(userRepository, times(1)).findByUsername(username);
    }

    @Test
    public void testGetUserByUsername_UserNotFound() {
        // Arrange
        String username = "testuser1";
        when(userRepository.findByUsername(username)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(
                UserNotFoundException.class,
                () -> userService.getUserByUsername(username));
    }

    @Test
    void testGetAllUsers() {
        // Given
        List<User> userList = new ArrayList<>();
        userList.add(testUser);
        userList.add(testUser);
        userList.add(testUser);
        when(userRepository.findAll()).thenReturn(userList);

        // When
        List<UserDTO> userDTOList = userService.getAllUsers();

        // Then
        assertNotNull(userDTOList);
        assertEquals(userList.size(), userDTOList.size());
        for (int i = 0; i < userList.size(); i++) {
            assertEquals(userList.get(i).getId(), userDTOList.get(i).getId());
            assertEquals(userList.get(i).getUsername(), userDTOList.get(i).getUsername());
            assertEquals(userList.get(i).getBalance(), userDTOList.get(i).getBalance());
        }
        verify(userRepository, times(1)).findAll();
    }

    @Test
    public void testUpdateUserBalance_Success() {
        // Arrange
        UpdateBalanceDTO updateBalanceDTO = new UpdateBalanceDTO();
        updateBalanceDTO.setUserId(testId);
        updateBalanceDTO.setNewBalance(BigDecimal.valueOf(1500));

        when(userRepository.findById(testId)).thenReturn(Optional.of(testUser));

        // Act
        UserDTO updatedUser = userService.updateUserBalance(updateBalanceDTO);

        // Assert
        assertNotNull(updatedUser);
        assertEquals(updateBalanceDTO.getUserId(), updatedUser.getId());
        assertEquals(testUser.getUsername(), updatedUser.getUsername());
        assertEquals(updateBalanceDTO.getNewBalance(), updatedUser.getBalance());
    }

    @Test
    public void testUpdateUserBalance_UserNotFound() {
        // Arrange
        UpdateBalanceDTO updateBalanceDTO = new UpdateBalanceDTO();
        updateBalanceDTO.setUserId(testId);
        updateBalanceDTO.setNewBalance(BigDecimal.valueOf(1500));

        when(userRepository.findById(testId)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(
                UserNotFoundException.class,
                () -> userService.updateUserBalance(updateBalanceDTO));
    }
}
