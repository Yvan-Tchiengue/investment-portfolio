package com.java.test3;

import org.springframework.stereotype.Service;
import java.time.Instant;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


@Service
public class FraudDetectionService {

    private static FraudDetectionService instance;
    private final Map<String, TransactionRecord> transactionHistory;
    private static final double FRAUD_THRESHOLD = 5000.0;

    private FraudDetectionService() {
        this.transactionHistory = new ConcurrentHashMap<>();
    }

    public static synchronized FraudDetectionService getInstance() {
        if (instance == null) {
            instance = new FraudDetectionService();
        }
        return instance;
    }

    /**
     * Vérifie si une transaction est suspecte
     * @param userId ID de l'utilisateur
     * @param amount Montant de la transaction
     * @return true si la transaction est suspecte, sinon false
     */
    public boolean isFraudulentTransaction(String userId, double amount) {
        TransactionRecord lastTransaction = transactionHistory.get(userId);

        if (amount > FRAUD_THRESHOLD) {
            return true; // Transaction au-dessus du seuil
        }

        if (lastTransaction != null) {
            long timeDiff = Instant.now().getEpochSecond() - lastTransaction.timestamp();
            if (timeDiff < 60 && amount > 3000) {
                return true; // Deux transactions élevées en moins d'une minute
            }
        }

        // Enregistrer la transaction
        transactionHistory.put(userId, new TransactionRecord(amount, Instant.now().getEpochSecond()));
        return false;
    }

    /**
     * Classe interne pour stocker les transactions
     */
    private record TransactionRecord(double amount, long timestamp) {}
}

