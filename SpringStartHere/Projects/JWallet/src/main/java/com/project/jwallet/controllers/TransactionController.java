package com.project.jwallet.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.jwallet.dto.TransactionDTO;
import com.project.jwallet.dto.TransferDTO;
import com.project.jwallet.services.TransactionService;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransactionDTO> getTransactionById(@PathVariable Long id) {
        TransactionDTO transactions = transactionService.getTransactionById(id);
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<List<TransactionDTO>> getAllTransactionsForUser(@PathVariable Long id) {
        List<TransactionDTO> transactions = transactionService.getAllTransactionsForUser(id);
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }

    @PostMapping("/transfer")
    public ResponseEntity<TransactionDTO> transferBalance(@RequestBody TransferDTO transferDTO) {
        TransactionDTO transaction = transactionService.transferBalance(transferDTO);
        return new ResponseEntity<>(transaction, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<TransactionDTO>> getAllTransactions() {
        List<TransactionDTO> users = transactionService.getAllTransactions();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
}
