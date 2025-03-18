package com.java.test3;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Service de gestion des transactions bancaires.
 */
@Service
public class TransactionService {

    private final FraudDetectionService fraudDetectionService;
    private final Map<String, Double> accountBalances;

    public TransactionService() {
        this.fraudDetectionService = FraudDetectionService.getInstance();
        this.accountBalances = new HashMap<>();
    }

    /**
     * Effectue une transaction pour un utilisateur donné.
     * @param userId ID de l'utilisateur
     * @param amount Montant de la transaction
     * @return Message de statut de la transaction
     */
    public String processTransaction(String userId, double amount) {
        if (amount <= 0) {
            return "Montant invalide.";
        }

        if (fraudDetectionService.isFraudulentTransaction(userId, amount)) {
            return "Transaction bloquée : Activité suspecte détectée.";
        }

        accountBalances.put(userId, accountBalances.getOrDefault(userId, 0.0) + amount);
        return "Transaction réussie. Nouveau solde : " + accountBalances.get(userId);
    }

    /**
     * Vérifie le solde d'un compte utilisateur
     * @param userId ID de l'utilisateur
     * @return Solde actuel
     */
    public double getBalance(String userId) {
        return accountBalances.getOrDefault(userId, 0.0);
    }
}
