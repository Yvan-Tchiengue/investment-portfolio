package com.unit.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.investment.models.Transaction;
import com.investment.repositories.TransactionRepository;
import com.investment.services.TransactionService;

@ExtendWith(MockitoExtension.class)
public class TransactionServiceTest {
	@Mock
    private TransactionRepository transactionRepository;

    @InjectMocks
    private TransactionService transactionService;

    private Transaction transaction1, transaction2, transaction3;

    @BeforeEach
    void setUp() {
        transaction1 = new Transaction();
        transaction1.setType("BUY");
        transaction1.setAmount(1000.0);
        transaction1.setDate(LocalDateTime.now().minusDays(2));

        transaction2 = new Transaction();
        transaction2.setType("SELL");
        transaction2.setAmount(500.0);
        transaction2.setDate(LocalDateTime.now().minusDays(1));

        transaction3 = new Transaction();
        transaction3.setType("BUY");
        transaction3.setAmount(1500.0);
        transaction3.setDate(LocalDateTime.now());
    }

    @Test
    void testGetTransactionHistory() {
        when(transactionRepository.findByInvestmentId(1L)).thenReturn(Arrays.asList(transaction1, transaction2, transaction3));

        List<Transaction> transactions = transactionService.getTransactionHistory(1L);
        assertEquals(3, transactions.size());
        assertEquals("BUY", transactions.get(0).getType()); // Le plus récent en premier
    }
}
