package com.assignment.tf.services;

import com.assignment.tf.controller.response.BalanceResponse;
import java.math.BigDecimal;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BalanceService {

  public BalanceResponse getBalance(String accountId){
    return new BalanceResponse()
        .setBalance("500")
        .setAccountId(accountId);


  }

  public BalanceResponse createAccount(String accountId, BigDecimal openingBalance) {
    return new BalanceResponse()
        .setBalance(String.valueOf(openingBalance))
        .setAccountId(accountId);
  }

  public void removeAccount(String accountId) {

  }
}
