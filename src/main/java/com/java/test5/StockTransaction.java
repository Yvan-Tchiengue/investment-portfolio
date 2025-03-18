package com.java.test5;

import java.time.LocalDateTime;


/**
 * Problème : Système de Gestion des Titres Financiers (Wertpapiere) en Entreprise

Les entreprises de trading, banques et fonds d’investissement doivent gérer un portefeuille de titres financiers (Wertpapiere).
Un système robuste est nécessaire pour :
✅ Gérer les transactions d’achat/vente de titres en bourse.
✅ Récupérer les prix des actions en temps réel depuis une API externe (ex: AlphaVantage,
 Yahoo Finance).
✅ Calculer la valeur totale du portefeuille pour chaque investisseur.
✅ Gérer un historique des transactions et garantir l’intégrité des données.
Solution

Nous allons créer un système de gestion des Wertpapiere avec :
1️⃣ StockPriceService : Récupère les prix des titres en temps réel depuis une API externe.
2️⃣ PortfolioService : Gère l’achat/vente des titres et le calcul du portefeuille.
3️⃣ TransactionService : Stocke l’historique des transactions.
4️⃣ PortfolioController : API REST pour exposer les fonctionnalités.
 */


/**
 * Représente une transaction d'achat ou de vente d'un titre financier.
 */
public class StockTransaction {
    private String id;
    private String investorId;
    private String stockSymbol;
    private int quantity;
    private double pricePerUnit;
    private String type; // BUY ou SELL
    private LocalDateTime timestamp;

    public StockTransaction(String id, String investorId, String stockSymbol, int quantity, double pricePerUnit, String type) {
        this.id = id;
        this.investorId = investorId;
        this.stockSymbol = stockSymbol;
        this.quantity = quantity;
        this.pricePerUnit = pricePerUnit;
        this.type = type;
        this.timestamp = LocalDateTime.now();
    }

    public String getId() { return id; }
    public String getInvestorId() { return investorId; }
    public String getStockSymbol() { return stockSymbol; }
    public int getQuantity() { return quantity; }
    public double getPricePerUnit() { return pricePerUnit; }
    public String getType() { return type; }
    public LocalDateTime getTimestamp() { return timestamp; }
}

