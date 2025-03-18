package com.java.test5;

import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Service pour gérer l'achat, la vente et la valeur du portefeuille d'un investisseur.
 */
@Service
public class PortfolioService {

    private final StockPriceService stockPriceService;
    private final List<StockTransaction> transactionHistory;
    private final Map<String, Map<String, Integer>> investorPortfolio;

    public PortfolioService(StockPriceService stockPriceService) {
        this.stockPriceService = stockPriceService;
        this.transactionHistory = new ArrayList<>();
        this.investorPortfolio = new HashMap<>();
    }

    /**
     * Achète un certain nombre d'actions pour un investisseur.
     */
    public String buyStock(String investorId, String stockSymbol, int quantity) {
        double price = stockPriceService.getStockPrice(stockSymbol);
        if (price < 0) {
            return "Erreur : Impossible de récupérer le prix du titre.";
        }

        StockTransaction transaction = new StockTransaction(UUID.randomUUID().toString(), investorId, stockSymbol, quantity, price, "BUY");
        transactionHistory.add(transaction);

        investorPortfolio.computeIfAbsent(investorId, k -> new HashMap<>())
                .merge(stockSymbol, quantity, Integer::sum);

        return "Achat confirmé : " + quantity + "x " + stockSymbol + " à " + price + " €";
    }

    /**
     * Vend un certain nombre d'actions pour un investisseur.
     */
    public String sellStock(String investorId, String stockSymbol, int quantity) {
        Map<String, Integer> portfolio = investorPortfolio.get(investorId);
        if (portfolio == null || portfolio.getOrDefault(stockSymbol, 0) < quantity) {
            return "Erreur : Nombre d'actions insuffisant.";
        }

        double price = stockPriceService.getStockPrice(stockSymbol);
        if (price < 0) {
            return "Erreur : Impossible de récupérer le prix du titre.";
        }

        StockTransaction transaction = new StockTransaction(UUID.randomUUID().toString(), investorId, stockSymbol, quantity, price, "SELL");
        transactionHistory.add(transaction);

        portfolio.put(stockSymbol, portfolio.get(stockSymbol) - quantity);
        if (portfolio.get(stockSymbol) == 0) {
            portfolio.remove(stockSymbol);
        }

        return "Vente confirmée : " + quantity + "x " + stockSymbol + " à " + price + " €";
    }

    /**
     * Calcule la valeur totale du portefeuille d'un investisseur.
     */
    public double getPortfolioValue(String investorId) {
        Map<String, Integer> portfolio = investorPortfolio.getOrDefault(investorId, new HashMap<>());
        return portfolio.entrySet().stream()
                .mapToDouble(entry -> stockPriceService.getStockPrice(entry.getKey()) * entry.getValue())
                .sum();
    }

    /**
     * Récupère l'historique des transactions d'un investisseur.
     */
    public List<StockTransaction> getTransactionHistory(String investorId) {
        return transactionHistory.stream()
                .filter(tx -> tx.getInvestorId().equals(investorId))
                .toList();
    }
}
