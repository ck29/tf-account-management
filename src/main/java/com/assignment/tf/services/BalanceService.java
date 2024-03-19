package com.assignment.tf.services;

import com.assignment.tf.controller.request.TransactionRequest;
import com.assignment.tf.controller.response.AccountResponse;
import com.assignment.tf.controller.response.BalanceResponse;
import com.assignment.tf.controller.response.TransactionResponse;
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
    return BalanceMapper.mapToBalanceResponse(balanceRepositoryService.createAccount(accountId, openingBalance));
  }

  public void removeAccount(String accountId) {
    balanceRepositoryService.removeAccount(accountId);
  }

  public TransactionResponse credit(String accountId, BigDecimal creditAmount){
    return BalanceMapper.mapToTransactionResponse(balanceRepositoryService.add(accountId,creditAmount), creditAmount);
  }

  public TransactionResponse debit(String accountId, BigDecimal debitAmount){
    return BalanceMapper.mapToTransactionResponse(balanceRepositoryService.deduct(accountId, debitAmount), debitAmount);
  }

  public TransactionResponse credit(TransactionRequest creditRequest) {
    return credit(creditRequest.getAccountId(), creditRequest.getAmount());
  }

  public TransactionResponse debit(TransactionRequest debitRequest) {
    return debit(debitRequest.getAccountId(), debitRequest.getAmount());
  }
}
