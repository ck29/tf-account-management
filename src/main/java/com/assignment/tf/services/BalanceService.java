package com.assignment.tf.services;

import com.assignment.tf.controller.request.TransactionRequest;
import com.assignment.tf.controller.response.AccountResponse;
import com.assignment.tf.controller.response.BalanceResponse;
import com.assignment.tf.exception.AccountNotFoundException;
import com.assignment.tf.mapper.BalanceMapper;
import com.assignment.tf.repositories.BalanceRepositoryService;
import com.assignment.tf.repositories.entities.BalanceEntity;
import java.math.BigDecimal;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BalanceService {

  private final BalanceRepositoryService balanceRepositoryService;

  public BalanceResponse getBalance(String accountId){
    BalanceEntity balanceEntity =  balanceRepositoryService.getBalance(accountId).orElseThrow(
        AccountNotFoundException::new);
    return BalanceMapper.mapToBalanceResponse(balanceEntity);

  }

  public BalanceResponse createAccount(String accountId, BigDecimal openingBalance) {
    return credit(accountId, openingBalance);
  }

  public void removeAccount(String accountId) {
    balanceRepositoryService.removeAccount(accountId);
  }

  public BalanceResponse credit(String accountId, BigDecimal creditAmount){
    return BalanceMapper.mapToBalanceResponse(balanceRepositoryService.add(accountId,creditAmount));
  }

  public BalanceResponse debit(String accountId, BigDecimal debitAmount){
    return BalanceMapper.mapToBalanceResponse(balanceRepositoryService.deduct(accountId, debitAmount));
  }

  public BalanceResponse credit(TransactionRequest creditRequest) {
    return credit(creditRequest.getAccountId(), creditRequest.getAmount());
  }

  public BalanceResponse debit(TransactionRequest debitRequest) {
    return debit(debitRequest.getAccountId(), debitRequest.getAmount());
  }
}
