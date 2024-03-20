package com.example.wallet.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.wallet.dto.TransactionDTO;
import com.example.wallet.dto.TransferDTO;
import com.example.wallet.dto.UpdateBalanceDTO;
import com.example.wallet.dto.UserDTO;
import com.example.wallet.exceptions.InsufficientBalanceException;
import com.example.wallet.exceptions.TransactionNotFoundException;
import com.example.wallet.models.Transaction;
import com.example.wallet.repositories.TransactionRepository;


@Service
public class TransactionServiceImpl implements TransactionService {
    private final UserService userService;
    private final TransactionRepository transactionRepository;

    public TransactionServiceImpl(
            UserService userService,
            TransactionRepository transactionRepository) {
        this.userService = userService;
        this.transactionRepository = transactionRepository;
    }

    @Override
    public TransactionDTO getTransactionById(Long id) {
        Transaction transaction = transactionRepository.findById(id)
                .orElseThrow(() -> new TransactionNotFoundException("Transaction not found with ID = " + id));

        return convertToDTO(transaction);
    }

    @Override
    public List<TransactionDTO> getAllTransactionsForUser(Long userId) {
        List<Transaction> transactions = transactionRepository.findAllByUserId(userId)
                .orElseThrow(() -> new TransactionNotFoundException(
                        "Transaction for the user id = " + userId + " not found"));

        return transactions.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<TransactionDTO> getAllTransactions() {
        List<TransactionDTO> transactionsDTO = new ArrayList<>();

        transactionRepository.findAll().forEach(transaction -> {
            transactionsDTO.add(convertToDTO(transaction));

        });

        return transactionsDTO;
    }

    @Override
    @Transactional
    public TransactionDTO transferBalance(TransferDTO transferDTO) {
        // Check if sender and receiver exist
        UserDTO sender = userService.getUserById(transferDTO.getSenderId());
        UserDTO receiver = userService.getUserById(transferDTO.getReceiverId());

        // Check if the sender has sufficient balance for the transfer
        if (sender.getBalance().compareTo(transferDTO.getAmount()) < 0)
            throw new InsufficientBalanceException(
                    "Sender does not have sufficient balance for the transfer");

        // Update sender's balance
        userService.updateUserBalance(new UpdateBalanceDTO(
                sender.getId(),
                sender.getBalance().subtract(transferDTO.getAmount())));

        // Update receiver's balance
        userService.updateUserBalance(new UpdateBalanceDTO(
                receiver.getId(),
                receiver.getBalance().add(transferDTO.getAmount())));

        return convertToDTO(transactionRepository.save(
                new Transaction(
                        transferDTO.getSenderId(),
                        transferDTO.getReceiverId(),
                        transferDTO.getAmount())));
    }

    TransactionDTO convertToDTO(Transaction transaction) {
        return new TransactionDTO(
                transaction.getId(),
                transaction.getSenderId(),
                transaction.getReceiverId(),
                transaction.getAmount(),
                transaction.getCreatedAt());
    }
}
