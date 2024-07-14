package com.project.jwallet.services;

import java.util.List;

import com.project.jwallet.dto.TransactionDTO;
import com.project.jwallet.dto.TransferDTO;
import com.project.jwallet.exceptions.InsufficientBalanceException;
import com.project.jwallet.exceptions.TransactionNotFoundException;
import com.project.jwallet.exceptions.UserNotFoundException;

public interface TransactionService {

    /**
     * Retrieves a transaction by its ID.
     *
     * @param transactionId The ID of the transaction to retrieve.
     * @return The transaction DTO.
     * @throws TransactionNotFoundException if no transaction found with the given
     *                                      ID.
     */
    TransactionDTO getTransactionById(Long transactionId);

    /**
     * Retrieves all transactions for a specific user.
     *
     * @param userId The ID of the user.
     * @return A list of transaction DTOs for the user.
     */
    List<TransactionDTO> getAllTransactionsForUser(Long userId);

    /**
     * Transfers balance from one user to another.
     *
     * @param transferDTO The DTO containing transfer details.
     * @return The transaction DTO for the transfer transaction.
     * @throws UserNotFoundException        if either sender or receiver user is not
     *                                      found.
     * @throws InsufficientBalanceException if the sender has insufficient balance
     *                                      for the transfer.
     */
    TransactionDTO transferBalance(TransferDTO transferDTO);

    /**
     * Retrieves all transactions.
     *
     * @return A list of all transaction DTOs.
     */
    List<TransactionDTO> getAllTransactions();
}
