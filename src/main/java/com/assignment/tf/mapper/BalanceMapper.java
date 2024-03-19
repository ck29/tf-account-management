package com.assignment.tf.mapper;

import com.assignment.tf.controller.response.BalanceResponse;
import com.assignment.tf.controller.response.TransactionResponse;
import com.assignment.tf.repositories.entities.BalanceEntity;
import java.math.BigDecimal;
import org.springframework.stereotype.Service;

@Service
public class BalanceMapper {

  public static BalanceResponse mapToBalanceResponse(BalanceEntity entity){
    return new BalanceResponse()
        .setBalance(String.valueOf(entity.getBalance()))
        .setAccountId(entity.getAccountId());
  }

  public static TransactionResponse mapToTransactionResponse(BalanceEntity entity, BigDecimal lastTransaction){
    return new TransactionResponse()
        .setLastTransactionAmount(String.valueOf(lastTransaction))
        .setAccountId(entity.getAccountId());
  }
}
