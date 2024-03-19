package ru.renat.spring.BankingMicroservice.controllers;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.renat.spring.BankingMicroservice.models.Transaction;
import ru.renat.spring.BankingMicroservice.services.TransactionService;

import java.util.List;

@RestController
@RequestMapping("/transactions")
@Api(tags = "Transactions", description = "Endpoints for managing transactions")
public class TransactionController {
    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping
    public ResponseEntity<String> createTransaction(@RequestBody Transaction transaction) {
        transactionService.saveTransaction(transaction);
        return new ResponseEntity<>("Transaction created successfully", HttpStatus.CREATED);
    }

    @GetMapping("/limit-exceeded")
    public ResponseEntity<List<Transaction>> getTransactionsWithLimitExceeded() {
        List<Transaction> transactions = transactionService.getTransactionsWithLimitExceeded();
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }
}
