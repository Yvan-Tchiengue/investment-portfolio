package com.java.test1;

import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class ProductService {

    private final CacheService cacheService;

    public ProductService() {
        this.cacheService = CacheService.getInstance(); // Récupération du singleton
    }

    
    public String getProductById(String productId) {
        Object cachedProduct = cacheService.get(productId);
        if (cachedProduct != null) {
            return (String) cachedProduct;
        }
        String product = fetchFromDatabase(productId);
        cacheService.put(productId, product);

        return product;
    }

    
    private String fetchFromDatabase(String productId) {
        try {
            Thread.sleep(1000); // Simule un temps d'accès à la base de données
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return "Produit-" + productId;
    }
}


