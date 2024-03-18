package com.assignment.tf.repositories.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Entity
@Accessors(chain = true)
@Table(name = "balance")
public class BalanceEntity {

  @Id
  @Column(name= "account-id")
  private String accountId;

  @Column(name = "name", nullable = false)
  private BigDecimal balance;
}
