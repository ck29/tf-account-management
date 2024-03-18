package com.assignment.tf.repositories;

import com.assignment.tf.exception.AccountNotFoundException;
import com.assignment.tf.repositories.entities.AccountEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountRepositoryService {

  private final AccountRepository repository;

  public AccountEntity getAccount(String accountId){
    return repository.findById(accountId).orElseThrow(() -> new AccountNotFoundException(accountId));
  }

}
