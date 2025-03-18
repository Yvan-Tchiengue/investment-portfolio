package com.unit.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.investment.models.Investment;
import com.investment.repositories.InvestmentRepository;
import com.investment.services.PortfolioService;


// Active Mockito pour gérer les objets simulés (@Mock) 
// et les injections de dépendances (@InjectMocks).
@ExtendWith(MockitoExtension.class) 
public class PortfolioServiceTest {
	
	@Mock //Crée un faux InvestmentRepository qui simulera les retours de la base de données.
    private InvestmentRepository investmentRepository;

    @InjectMocks //injecte automatiquement le mock du repository (investmentRepository) dans PortfolioService.
    private PortfolioService portfolioService;

    private Investment investment1, investment2, investment3;

    @BeforeEach //Initialise trois investissements avant chaque test.
    void setUp() {
        investment1 = new Investment();
        investment1.setAssetName("Apple");
        investment1.setAssetType("Stock");
        investment1.setCurrentValue(1000.0);

        investment2 = new Investment();
        investment2.setAssetName("Bitcoin");
        investment2.setAssetType("Crypto");
        investment2.setCurrentValue(5000.0);

        investment3 = new Investment();
        investment3.setAssetName("Tesla");
        investment3.setAssetType("Stock");
        investment3.setCurrentValue(3000.0);
    }

    @Test    //test1  Vérifier que getTotalPortfolioValue(1L) retourne la somme correcte des investissements.
    void testGetTotalPortfolioValue() {
        when(investmentRepository.findByUserId(1L)).thenReturn(Arrays.asList(investment1, investment2, investment3));

        double totalValue = portfolioService.getTotalPortfolioValue(1L);
        assertEquals(9000.0, totalValue);
    }

    @Test
    void testGetTop3Investments() {
        when(investmentRepository.findByUserId(1L)).thenReturn(Arrays.asList(investment1, investment2, investment3));

        //verification
        List<Investment> topInvestments = portfolioService.getTop3Investments(1L);
        assertEquals(3, topInvestments.size());
        assertEquals("Bitcoin", topInvestments.get(0).getAssetName());
        //✅ Test réussi si :

          //  La liste contient 3 éléments.
            //Bitcoin est le premier dans la liste (car c'est le plus grand).
    }

    @Test
    void testGroupInvestmentsByType() {
        when(investmentRepository.findByUserId(1L)).thenReturn(Arrays.asList(investment1, investment2, investment3));

        Map<String, List<Investment>> groupedInvestments = portfolioService.groupInvestmentsByType(1L);

        assertEquals(2, groupedInvestments.size());
        assertTrue(groupedInvestments.containsKey("Stock"));
        assertTrue(groupedInvestments.containsKey("Crypto"));
        //✅ Test réussi si :

          //  Il y a 2 groupes (Stock, Crypto).
            //Les clés "Stock" et "Crypto" existent dans la map
    }

}

 