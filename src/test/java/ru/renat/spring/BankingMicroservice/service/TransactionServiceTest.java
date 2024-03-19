package ru.renat.spring.BankingMicroservice.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.renat.spring.BankingMicroservice.models.Limit;
import ru.renat.spring.BankingMicroservice.models.Transaction;
import ru.renat.spring.BankingMicroservice.repositories.LimitRepository;
import ru.renat.spring.BankingMicroservice.repositories.TransactionRepository;
import ru.renat.spring.BankingMicroservice.services.TransactionService;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class TransactionServiceTest {
    @MockBean
    private TransactionRepository transactionRepository;

    @MockBean
    private LimitRepository limitRepository;

    @Autowired
    private TransactionService transactionService;

    @Test
    void testSaveTransactionWithLimitExceeded() {
        Transaction transaction = new Transaction();
        transaction.setAmount(new BigDecimal("1100"));
        transaction.setCurrency("USD");

        Limit limit = new Limit();
        limit.setAmount(new BigDecimal("1000"));
        limit.setCurrency("USD");

        when(limitRepository.findByCurrency("USD")).thenReturn(limit);
        transactionService.saveTransaction(transaction);

        assertTrue(transaction.isLimitExceeded());
        verify(transactionRepository, times(1)).save(transaction);
    }
}
