package com.investment.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.investment.models.Investment;
import com.investment.services.PortfolioService;

@RestController
@RequestMapping("/api/portfolio")
public class PortfolioController {
    @Autowired
    private PortfolioService portfolioService;

    @GetMapping("/total-value/{userId}")
    public ResponseEntity<Double> getTotalValue(@PathVariable Long userId) {
        return ResponseEntity.ok(portfolioService.getTotalPortfolioValue(userId));
    }

    @GetMapping("/top-investments/{userId}")
    public ResponseEntity<List<Investment>> getTopInvestments(@PathVariable Long userId) {
        return ResponseEntity.ok(portfolioService.getTop3Investments(userId));
    }

    @GetMapping("/group-by-type/{userId}")
    public ResponseEntity<Map<String, List<Investment>>> groupInvestments(@PathVariable Long userId) {
        return ResponseEntity.ok(portfolioService.groupInvestmentsByType(userId));
    }
}

