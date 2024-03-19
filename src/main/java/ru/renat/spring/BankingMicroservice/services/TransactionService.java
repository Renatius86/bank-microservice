package ru.renat.spring.BankingMicroservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.renat.spring.BankingMicroservice.models.Limit;
import ru.renat.spring.BankingMicroservice.models.Transaction;
import ru.renat.spring.BankingMicroservice.repositories.LimitRepository;
import ru.renat.spring.BankingMicroservice.repositories.TransactionRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final LimitRepository limitRepository;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository, LimitRepository limitRepository) {
        this.transactionRepository = transactionRepository;
        this.limitRepository = limitRepository;
    }


    // Метод для сохранения транзакции в базе данных
    public void saveTransaction(Transaction transaction) {
        transactionRepository.save(transaction);
        // Проверяем превышение лимита
        checkLimitExceeded(transaction);
    }

    // Метод для проверки превышения лимита и пометки транзакции
    private void checkLimitExceeded(Transaction transaction) {
        String currency = transaction.getCurrency();
        BigDecimal transactionAmount = transaction.getAmount();
        LocalDate transactionDate = transaction.getTransactionDate();

        Limit limit = limitRepository.findByCurrency(currency);
        if (limit != null && transactionAmount.compareTo(limit.getAmount()) > 0) {
            transaction.setLimitExceeded(true);
            transactionRepository.save(transaction);
        }
    }

    // Метод для установки нового лимита
    public void setLimit(BigDecimal amount, String currency, String category) {
        Limit limit = limitRepository.findByCurrency(currency);
        if (limit == null) {
            limit = new Limit();
            limit.setCurrency(currency);
        }
        limit.setAmount(amount);
        limit.setCategory(category);
        limit.setSetDate(LocalDate.now());
        limitRepository.save(limit);
    }

    // Метод для получения списка транзакций, превысивших лимит
    public List<Transaction> getTransactionsWithLimitExceeded() {
        return transactionRepository.findByLimitExceededTrue();
    }

    // Метод для получения всех лимитов
    public List<Limit> getAllLimits() {
        return limitRepository.findAll();
    }
}
