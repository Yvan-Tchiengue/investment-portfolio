package com.investment.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.investment.models.Transaction;
import com.investment.services.TransactionService;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @GetMapping("/{investmentId}")
    public ResponseEntity<List<Transaction>> getTransactions(@PathVariable Long investmentId) {
        return ResponseEntity.ok(transactionService.getTransactionHistory(investmentId));
    }
}
