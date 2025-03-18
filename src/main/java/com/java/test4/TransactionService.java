package com.java.test4;

import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

/**
 * Service de gestion des transactions en devises.
 */
@Service
public class TransactionService {

    private final CurrencyConversionService currencyConversionService;
    private final Map<String, Double> userBalances;

    public TransactionService(CurrencyConversionService currencyConversionService) {
        this.currencyConversionService = currencyConversionService;
        this.userBalances = new HashMap<>();
    }

    /**
     * Effectue une transaction entre deux utilisateurs avec conversion de devises.
     * @param sender ID de l'envoyeur
     * @param receiver ID du destinataire
     * @param amount Montant envoyé
     * @param currency Devise utilisée
     * @param targetCurrency Devise du destinataire
     * @return Message de confirmation
     */
    public String processTransaction(String sender, String receiver, double amount, String currency, String targetCurrency) {
        if (amount <= 0) {
            return "Montant invalide.";
        }

        // Vérifier le solde de l'envoyeur
        double senderBalance = userBalances.getOrDefault(sender, 0.0);
        if (senderBalance < amount) {
            return "Fonds insuffisants.";
        }

        // Convertir en devise cible
        double convertedAmount = currencyConversionService.convertCurrency(amount, currency, targetCurrency);

        // Effectuer la transaction
        userBalances.put(sender, senderBalance - amount);
        userBalances.put(receiver, userBalances.getOrDefault(receiver, 0.0) + convertedAmount);

        return "Transaction réussie. " + amount + " " + currency + " convertis en " + convertedAmount + " " + targetCurrency;
    }

    /**
     * Vérifie le solde d'un utilisateur.
     * @param userId ID de l'utilisateur
     * @return Solde actuel
     */
    public double getUserBalance(String userId) {
        return userBalances.getOrDefault(userId, 0.0);
    }
}
