package com.guusto.entity;


import javax.persistence.*;
import java.time.LocalDateTime;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity(name = "Balance")
@Table(name = "balance")
public class Balance {

    @Id
    @SequenceGenerator(
            name = "balance_sequence",
            sequenceName = "balance_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "balance_sequence"
    )
    @Column(
            name = "id",
            updatable = false
    )
    private Long id;

    @Column(
            name = "created_at",
            nullable = false,
            columnDefinition = "TIMESTAMP WITHOUT TIME ZONE"
    )
    private LocalDateTime createdAt;

    @Column(
            name = "client_id",
            unique = true,
            nullable = false
    )
    private Long clientId;
    
    @Column(
            name = "balance",
            nullable = false
    )
    private Long balance;

    

    public Balance() {
    }



	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public LocalDateTime getCreatedAt() {
		return createdAt;
	}



	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}



	public Long getClientId() {
		return clientId;
	}



	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}



	public Long getBalance() {
		return balance;
	}



	public void setBalance(Long balance) {
		this.balance = balance;
	}

}
