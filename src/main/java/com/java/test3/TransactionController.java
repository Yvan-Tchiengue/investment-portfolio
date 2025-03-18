package com.java.test3;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/process")
    public String processTransaction(@RequestParam String userId, @RequestParam double amount) {
        return transactionService.processTransaction(userId, amount);
    }

    @GetMapping("/balance")
    public double getBalance(@RequestParam String userId) {
        return transactionService.getBalance(userId);
    }
}


