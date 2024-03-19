package ru.renat.spring.BankingMicroservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.renat.spring.BankingMicroservice.models.Limit;

@Repository
public interface LimitRepository extends JpaRepository<Limit, Long> {
    Limit findByCurrency(String currency);
}
