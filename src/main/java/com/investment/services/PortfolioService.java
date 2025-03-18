package com.investment.services;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.investment.models.Investment;
import com.investment.repositories.InvestmentRepository;

@Service
public class PortfolioService {
	
    @Autowired
    private InvestmentRepository investmentRepository;

    /**
     * Cette méthode récupère tous les investissements d'un utilisateur 
     * à partir de la base de données, extrait leur valeur actuelle et 
     * les additionne pour obtenir la valeur totale du portefeuille.*/
    public double getTotalPortfolioValue(Long userId) {
        return investmentRepository.findByUserId(userId)
                .stream()  
                .mapToDouble(Investment::getCurrentValue) 
                .sum(); 
    }
    
    
 
    public List<Investment> getInvestmentsByType(Long userId, String type) {
        return investmentRepository.findByUserId(userId) 
                .stream() 
                .filter(inv -> inv.getAssetType().equalsIgnoreCase(type))
                .collect(Collectors.toList()); 
    }

  
    public List<Investment> getTop3Investments(Long userId) {
        return investmentRepository.findByUserId(userId)
                .stream()
                .sorted(Comparator.comparingDouble(Investment::getCurrentValue).reversed())
                .limit(3) //Garde uniquement les trois premiers investissements
                .collect(Collectors.toList());
    }

   
    public Map<String, List<Investment>> groupInvestmentsByType(Long userId) {
        return investmentRepository.findByUserId(userId)
                .stream()
                .collect(Collectors.groupingBy(Investment::getAssetType));
    }
}



