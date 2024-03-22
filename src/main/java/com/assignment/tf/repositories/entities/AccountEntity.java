package com.assignment.tf.repositories.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.CreationTimestamp;

@Data
@Entity
@Accessors(chain = true)
@Table(name = "account")
public class AccountEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @Column(name = "account-id", nullable = false)
  private String accountId;

  @Column(name = "iban", nullable = false)
  private String iban;

  @Column(name = "name", nullable = false)
  private String name;

  @Column(name="email", nullable = false)
  private String email;

  @CreationTimestamp
  @Column(name="created_timestamp", nullable = false)
  private LocalDateTime createdTimestamp;


}
