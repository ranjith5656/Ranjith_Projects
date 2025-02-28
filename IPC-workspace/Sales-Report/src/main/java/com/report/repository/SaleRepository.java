package com.report.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.report.entity.Sale;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {
}
