package com.project.jwallet.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
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

import com.project.jwallet.dto.TransactionDTO;
import com.project.jwallet.dto.TransferDTO;
import com.project.jwallet.dto.UpdateBalanceDTO;
import com.project.jwallet.dto.UserDTO;
import com.project.jwallet.exceptions.InsufficientBalanceException;
import com.project.jwallet.exceptions.TransactionNotFoundException;
import com.project.jwallet.exceptions.UserNotFoundException;
import com.project.jwallet.models.Transaction;
import com.project.jwallet.repositories.TransactionRepository;

@ExtendWith(MockitoExtension.class)
public class TransactionServiceImplTest {
    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private UserService userService;

    @InjectMocks
    private TransactionServiceImpl transactionService;

    private UserDTO sender;
    private UserDTO receiver;

    @BeforeEach
    public void setUp() {
        sender = new UserDTO(1L, "sender", BigDecimal.valueOf(1000));
        receiver = new UserDTO(2L, "reciever", BigDecimal.valueOf(2000));
    }

    @Test
    public void testGetTransactionById_Success() {
        // Mock
        Transaction transaction = new Transaction();
        transaction.setId(1L);
        when(transactionRepository.findById(1L)).thenReturn(Optional.of(transaction));

        // Test
        TransactionDTO transactionDTO = transactionService.getTransactionById(1L);

        // Verify
        assertNotNull(transactionDTO);
        assertEquals(transaction.getId(), transactionDTO.getId());
    }

    @Test
    public void testGetTransactionById_NotFound() {
        // Mock
        when(transactionRepository.findById(1L)).thenReturn(Optional.empty());

        // Test and verify
        assertThrows(TransactionNotFoundException.class, () -> transactionService.getTransactionById(1L));
    }

    @Test
    public void testGetAllTransactionsForUser_Success() {
        // Mock
        Transaction transaction1 = new Transaction(1L, sender.getId(), receiver.getId(), BigDecimal.valueOf(1));
        Transaction transaction2 = new Transaction(2L, receiver.getId(), sender.getId(), BigDecimal.valueOf(1));

        List<Transaction> transactions = new ArrayList<>();
        transactions.add(transaction1);
        transactions.add(transaction2);

        when(transactionRepository.findAllByUserId(sender.getId())).thenReturn(Optional.of(transactions));

        // Test
        List<TransactionDTO> transactionDTOs = transactionService.getAllTransactionsForUser(sender.getId());

        // Verify
        assertNotNull(transactionDTOs);
        assertEquals(2, transactionDTOs.size());
    }

    @Test
    public void testGetAllTransactionsForUser_NotFound() {
        // Mock
        when(transactionRepository.findAllByUserId(sender.getId()))
                .thenReturn(Optional.of(new ArrayList<>()));

        // Test
        List<TransactionDTO> transactions = transactionService.getAllTransactionsForUser(sender.getId());

        // Verify
        assertNotNull(transactions);
        assertTrue(transactions.isEmpty());
    }

    @Test
    public void testTransferBalance_Success() {
        // Mock
        TransferDTO transferDTO = new TransferDTO(sender.getId(), receiver.getId(), BigDecimal.valueOf(500));
        Transaction transaction = new Transaction(
                transferDTO.getSenderId(),
                transferDTO.getReceiverId(),
                transferDTO.getAmount());

        when(userService.getUserById(sender.getId())).thenReturn(sender);
        when(userService.getUserById(receiver.getId())).thenReturn(receiver);
        when(transactionRepository.save(transaction)).thenReturn(transaction);

        // Test
        TransactionDTO transactionDTO = transactionService.transferBalance(transferDTO);

        // Validation
        assertNotNull(transactionDTO);

        verify(userService).updateUserBalance(
                new UpdateBalanceDTO(sender.getId(), sender.getBalance().subtract(transferDTO.getAmount())));

        verify(userService).updateUserBalance(
                new UpdateBalanceDTO(receiver.getId(), receiver.getBalance().add(transferDTO.getAmount())));
    }

    @Test
    public void testTransferBalance_SenderNotFound() {
        TransferDTO transferDTO = new TransferDTO(sender.getId(), receiver.getId(), BigDecimal.valueOf(500));

        // Mock
        when(userService.getUserById(sender.getId()))
                .thenThrow(new UserNotFoundException("Sender not found"));

        // Test and verify
        assertThrows(
                UserNotFoundException.class,
                () -> transactionService.transferBalance(transferDTO));
    }

    @Test
    public void testTransferBalance_ReceiverNotFound() {
        TransferDTO transferDTO = new TransferDTO(sender.getId(), receiver.getId(), BigDecimal.valueOf(500));

        // Mock
        when(userService.getUserById(sender.getId())).thenReturn(sender);
        when(userService.getUserById(receiver.getId()))
                .thenThrow(new UserNotFoundException("Receiver not found"));

        // Test and verify
        assertThrows(
                UserNotFoundException.class,
                () -> transactionService.transferBalance(transferDTO));
    }

    @Test
    public void testTransferBalance_InsufficientBalance() {
        TransferDTO transferDTO = new TransferDTO(sender.getId(), receiver.getId(), BigDecimal.valueOf(500));

        // Mock
        sender.setBalance(BigDecimal.valueOf(200)); // Insufficient balance
        when(userService.getUserById(sender.getId())).thenReturn(sender);
        when(userService.getUserById(receiver.getId())).thenReturn(receiver);

        // Test and verify
        assertThrows(
            InsufficientBalanceException.class, 
            () -> transactionService.transferBalance(transferDTO));
    }
}
