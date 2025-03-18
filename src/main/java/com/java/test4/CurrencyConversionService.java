package com.java.test4;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;
import java.util.HashMap;
import java.util.Map;

/**
 * Service pour la conversion de devises avec récupération des taux via une API externe.
 */
@Service
public class CurrencyConversionService {

    private static final String API_URL = "https://api.exchangerate-api.com/v4/latest/";
    private final RestTemplate restTemplate;
    
    // Cache temporaire pour stocker les taux de change et éviter trop d'appels API
    private final Map<String, Map<String, Double>> exchangeRateCache;
    
    public CurrencyConversionService() {
        this.restTemplate = new RestTemplate();
        this.exchangeRateCache = new HashMap<>();
    }

    /**
     * Récupère les taux de change depuis l'API externe.
     * Stocke en cache pour éviter des requêtes répétées.
     * @param baseCurrency Devise de base (ex: USD)
     * @return Map des taux de change
     */
    private Map<String, Double> getExchangeRates(String baseCurrency) {
        if (exchangeRateCache.containsKey(baseCurrency)) {
            return exchangeRateCache.get(baseCurrency);
        }

        String url = API_URL + baseCurrency;
        try {
            ResponseEntity<Map> response = restTemplate.getForEntity(url, Map.class);
            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                Map<String, Double> rates = (Map<String, Double>) response.getBody().get("rates");
                exchangeRateCache.put(baseCurrency, rates); // Stocker en cache
                return rates;
            }
        } catch (Exception e) {
            System.err.println("Erreur lors de la récupération des taux de change : " + e.getMessage());
        }
        return null;
    }

    /**
     * Convertit un montant d'une devise à une autre.
     * @param amount Montant à convertir
     * @param fromCurrency Devise source (ex: EUR)
     * @param toCurrency Devise cible (ex: USD)
     * @return Montant converti
     */
    public double convertCurrency(double amount, String fromCurrency, String toCurrency) {
        if (fromCurrency.equalsIgnoreCase(toCurrency)) {
            return amount; // Pas besoin de conversion
        }

        Map<String, Double> rates = getExchangeRates(fromCurrency);
        if (rates == null || !rates.containsKey(toCurrency)) {
            throw new RuntimeException("Impossible de récupérer le taux de conversion pour " + fromCurrency + " vers " + toCurrency);
        }

        double rate = rates.get(toCurrency);
        return amount * rate;
    }

    /**
     * Vide le cache des taux de change.
     */
    public void clearCache() {
        exchangeRateCache.clear();
    }

}
