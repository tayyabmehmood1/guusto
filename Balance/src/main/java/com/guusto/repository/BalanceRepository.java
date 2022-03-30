package com.guusto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.guusto.entity.Balance;

public interface BalanceRepository
        extends JpaRepository<Balance, Long> {
	@Query("select b from Balance b where b.clientId = ?1")
	public Balance findByClientId(Long clientId);
}
