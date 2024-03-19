package ru.renat.spring.BankingMicroservice.controllers;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.renat.spring.BankingMicroservice.models.Limit;
import ru.renat.spring.BankingMicroservice.services.TransactionService;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/limits")
@Api(tags = "Limits", description = "Endpoints for managing limits")
public class LimitController {
    private final TransactionService transactionService;

    @Autowired
    public LimitController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping
    public ResponseEntity<String> setLimit(@RequestParam BigDecimal amount,
                                           @RequestParam String currency,
                                           @RequestParam String category) {
        transactionService.setLimit(amount, currency, category);
        return new ResponseEntity<>("Limit set successfully", HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Limit>> getAllLimits() {
        List<Limit> limits = transactionService.getAllLimits();
        return new ResponseEntity<>(limits, HttpStatus.OK);
    }
}
