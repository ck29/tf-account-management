package com.assignment.tf.services;

import com.assignment.tf.controller.response.BalanceResponse;
import org.springframework.stereotype.Service;

@Service
public class BalanceService {

  public BalanceResponse getBalance(String accountId){
    return new BalanceResponse()
        .setBalance("500")
        .setAccountId(accountId);


  }
}
