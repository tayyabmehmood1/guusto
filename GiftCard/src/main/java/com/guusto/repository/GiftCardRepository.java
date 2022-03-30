package com.guusto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.guusto.entity.GiftCard;

public interface GiftCardRepository
        extends JpaRepository<GiftCard, Long> {
	@Query("select g from GiftCard g where g.clientId = ?1")
	public GiftCard findByClientId(Long clientId);
}
