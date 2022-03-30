package com.guusto.entity;


import static javax.persistence.GenerationType.SEQUENCE;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "GiftCard")
@Table(name = "giftcard")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GiftCard {

    @Id
    @SequenceGenerator(
            name = "giftcard_sequence",
            sequenceName = "giftcard_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "giftcard_sequence"
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
            nullable = false
    )
    private Long clientId;
    
    @Column(
            name = "amount",
            nullable = false
    )
    private Long amount;

    @Column(
            name = "quantity",
            nullable = false
    )
    private Long quantity;
 

}
