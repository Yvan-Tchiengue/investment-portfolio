package com.integration.tests;

import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import com.investment.controllers.PortfolioController;
import com.investment.models.Investment;
import com.investment.services.PortfolioService;


@SpringBootTest
@AutoConfigureMockMvc
public class PortfolioControllerTest {
	@Autowired
    private MockMvc mockMvc;

    @MockBean
    private PortfolioService portfolioService;

    @Test
    void testGetTotalValue() throws Exception {
        when(portfolioService.getTotalPortfolioValue(1L)).thenReturn(9000.0);

        mockMvc.perform(get("/api/portfolio/total-value/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("9000.0"));
    }

    @Test
    void testGetTopInvestments() throws Exception {
        Investment inv1 = new Investment();
        inv1.setAssetName("Apple");
        inv1.setCurrentValue(2000.0);

        List<Investment> investments = Arrays.asList(inv1);
        when(portfolioService.getTop3Investments(1L)).thenReturn(investments);

        mockMvc.perform(get("/api/portfolio/top-investments/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].assetName").value("Apple"));
    }
}
