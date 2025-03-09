package com.investment.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.investment.models.Investment;

@Repository
public interface InvestmentRepository extends JpaRepository<Investment, Long> {
    List<Investment> findByUserId(Long userId);
}

