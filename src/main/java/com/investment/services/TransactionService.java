package com.investment.services;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.investment.models.Transaction;
import com.investment.repositories.TransactionRepository;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    public List<Transaction> getTransactionHistory(Long investmentId) {
        return transactionRepository.findByInvestmentId(investmentId)
                .stream()
                .sorted(Comparator.comparing(Transaction::getDate).reversed())
                .collect(Collectors.toList());
    }
}
