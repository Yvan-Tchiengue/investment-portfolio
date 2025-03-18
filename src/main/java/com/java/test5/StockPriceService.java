package com.java.test5;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;

import java.util.Map;

/**
 * Service pour récupérer les prix des actions en temps réel via une API externe.
 */
@Service
public class StockPriceService {

    private static final String API_URL = "https://api.example.com/stockprice?symbol=";
    private final RestTemplate restTemplate;

    public StockPriceService() {
        this.restTemplate = new RestTemplate();
    }

    /**
     * Récupère le prix actuel d'une action.
     * @param stockSymbol Symbole du titre (ex: AAPL, TSLA)
     * @return Prix actuel
     */
    public double getStockPrice(String stockSymbol) {
        try {
            ResponseEntity<Map> response = restTemplate.getForEntity(API_URL + stockSymbol, Map.class);
            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                return Double.parseDouble(response.getBody().get("price").toString());
            }
        } catch (Exception e) {
            System.err.println("Erreur API StockPrice : " + e.getMessage());
        }
        return -1; // Retourne -1 en cas d’échec
    }
}
