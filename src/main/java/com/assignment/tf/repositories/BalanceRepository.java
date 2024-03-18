package com.assignment.tf.repositories;

import com.assignment.tf.repositories.entities.BalanceEntity;
import java.math.BigDecimal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BalanceRepository extends JpaRepository<BalanceEntity, String> {

  final BigDecimal MIN_BALANCE = BigDecimal.valueOf(0.0);

}
