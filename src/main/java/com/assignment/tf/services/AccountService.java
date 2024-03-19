package com.assignment.tf.services;

import com.assignment.tf.controller.request.CreateAccountRequest;
import com.assignment.tf.controller.response.AccountResponse;
import com.assignment.tf.controller.response.BalanceResponse;
import com.assignment.tf.controller.response.TransactionResponse;
import com.assignment.tf.mapper.AccountMapper;
import com.assignment.tf.repositories.AccountRepositoryService;
import com.assignment.tf.repositories.entities.AccountEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountService {
  private final AccountMapper mapper;

  private final AccountRepositoryService repositoryService;
  private final BalanceService balanceService;

  public AccountResponse retrieveAccount(String accountId) {
    AccountEntity accountEntity = repositoryService.getAccount(accountId);
    BalanceResponse balanceResponse= balanceService.getBalance(accountId);
    return mapper.mapToAccount(accountEntity, balanceResponse);
  }

  public AccountResponse createAccount(CreateAccountRequest createAccountRequest) {

    AccountEntity account = repositoryService.createAccount(createAccountRequest);
    BalanceResponse balanceResponse = balanceService.createAccount(account.getAccountId(), createAccountRequest.getOpeningBalance());
    return mapper.mapToAccount(account,balanceResponse);

  }

  public void removeAccount(String accountId) {
    repositoryService.removeAccount(accountId);
    balanceService.removeAccount(accountId);
  }
}
