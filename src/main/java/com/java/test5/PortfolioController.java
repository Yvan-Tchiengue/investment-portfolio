package com.java.test5;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/portfolio")
public class PortfolioController {

    private final PortfolioService portfolioService;

    public PortfolioController(PortfolioService portfolioService) {
        this.portfolioService = portfolioService;
    }

    @PostMapping("/buy")
    public String buyStock(@RequestParam String investorId, @RequestParam String stockSymbol, @RequestParam int quantity) {
        return portfolioService.buyStock(investorId, stockSymbol, quantity);
    }

    @PostMapping("/sell")
    public String sellStock(@RequestParam String investorId, @RequestParam String stockSymbol, @RequestParam int quantity) {
        return portfolioService.sellStock(investorId, stockSymbol, quantity);
    }

    @GetMapping("/value")
    public double getPortfolioValue(@RequestParam String investorId) {
        return portfolioService.getPortfolioValue(investorId);
    }

    @GetMapping("/history")
    public List<StockTransaction> getTransactionHistory(@RequestParam String investorId) {
        return portfolioService.getTransactionHistory(investorId);
    }
}

