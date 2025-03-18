package com.java.test4;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/process")
    public String processTransaction(
            @RequestParam String sender,
            @RequestParam String receiver,
            @RequestParam double amount,
            @RequestParam String currency,
            @RequestParam String targetCurrency) {
        return transactionService.processTransaction(sender, receiver, amount, currency, targetCurrency);
    }

    @GetMapping("/balance")
    public double getBalance(@RequestParam String userId) {
        return transactionService.getUserBalance(userId);
    }
}

