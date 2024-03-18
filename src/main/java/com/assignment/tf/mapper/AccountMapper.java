package com.assignment.tf.mapper;

import com.assignment.tf.controller.response.AccountResponse;
import com.assignment.tf.controller.response.BalanceResponse;
import com.assignment.tf.repositories.entities.AccountEntity;

public class AccountMapper {

  public static AccountResponse mapToAccount(AccountEntity entity, BalanceResponse balance){
    return new AccountResponse()
        .setAccountId(entity.getAccountId())
        .setName(entity.getName())
        .setEmail(entity.getEmail())
        .setBalance(balance.getBalance());
  }
}
