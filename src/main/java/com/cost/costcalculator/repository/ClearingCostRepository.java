package com.cost.costcalculator.repository;

import com.cost.costcalculator.domain.ClearingCost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClearingCostRepository extends JpaRepository<ClearingCost, Long> {

    Optional<ClearingCost> findByIssuingCountry(String issuingCountry);
}
