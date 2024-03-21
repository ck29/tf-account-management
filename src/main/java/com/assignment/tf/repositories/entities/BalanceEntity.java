package com.assignment.tf.repositories.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Data
@Entity
@Accessors(chain = true)
@Table(name = "balance")
public class BalanceEntity {

  @Id
  @Column(name= "iban")
  private String iban;

  @Column(name = "balance", nullable = false)
  private BigDecimal balance;

  @Column(name = "last-transaction-amount", nullable = true)
  private BigDecimal lastTransactionAmount;

  @UpdateTimestamp
  @Column(name="created_timestamp", nullable = false)
  private LocalDateTime updatedTimestamp;

}
