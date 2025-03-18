package com.java.test1;

import java.time.Instant;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;


@Service
public class CacheService {

    private static CacheService instance;
    private final Map<String, CacheEntry> cache;
    private static final long EXPIRATION_TIME = 60;

    private CacheService() {
        this.cache = new ConcurrentHashMap<>();
    }

    /**
     * Implémentation du Singleton : retourne l'unique instance de CacheService
     */
    public static synchronized CacheService getInstance() {
        if (instance == null) {
            instance = new CacheService();
        }
        return instance;
    }

    /**
     * Ajoute une donnée au cache avec expiration
     * @param key clé de la donnée
     * @param value valeur à stocker
     */
    public void put(String key, Object value) {
        cache.put(key, new CacheEntry(value, Instant.now().plusSeconds(EXPIRATION_TIME)));
    }

    /**
     * Récupère une donnée depuis le cache
     * Si la donnée a expiré, elle est supprimée et null est retourné
     * @param key clé de la donnée
     * @return la valeur stockée ou null si expirée/non existante
     */
    public Object get(String key) {
        CacheEntry entry = cache.get(key);
        if (entry == null) {
            return null;
        }
        if (Instant.now().isAfter(entry.expirationTime())) {
            cache.remove(key);
            return null;
        }
        return entry.value();
    }

    /**
     * Supprime une entrée du cache
     * @param key clé de la donnée à supprimer
     */
    public void remove(String key) {
        cache.remove(key);
    }

    /**
     * Vérifie si une clé est présente dans le cache
     * @param key clé à vérifier
     * @return true si la clé existe, false sinon
     */
    public boolean containsKey(String key) {
        return cache.containsKey(key);
    }

    /**
     * Vide entièrement le cache
     */
    public void clear() {
        cache.clear();
    }

    /**
     * Classe interne représentant une entrée de cache avec expiration
     */
    private record CacheEntry(Object value, Instant expirationTime) {}
}


