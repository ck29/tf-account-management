package com.assignment.tf.services;

import com.assignment.tf.controller.response.AccountResponse;
import com.assignment.tf.controller.response.BalanceResponse;
import com.assignment.tf.mapper.AccountMapper;
import com.assignment.tf.repositories.AccountRepositoryService;
import com.assignment.tf.repositories.entities.AccountEntity;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
  private AccountMapper mapper;

  private AccountRepositoryService repositoryService;
  private BalanceService balanceService;

  public AccountResponse retrieveAccount(String accountId) {
    AccountEntity accountEntity = repositoryService.getAccount(accountId);
    BalanceResponse balanceResponse= balanceService.getBalance(accountId);
    return mapper.mapToAccount(accountEntity, balanceResponse);
  }
}
