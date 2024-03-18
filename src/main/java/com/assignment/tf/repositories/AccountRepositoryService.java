package com.assignment.tf.repositories;

import com.assignment.tf.controller.request.CreateAccountRequest;
import com.assignment.tf.exception.AccountAlreadyExistsException;
import com.assignment.tf.exception.AccountNotFoundException;
import com.assignment.tf.repositories.entities.AccountEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountRepositoryService {

  private final AccountRepository repository;

  public AccountEntity getAccount(String accountId){
    return repository.findById(accountId).orElseThrow(() -> new AccountNotFoundException());
  }

  public AccountEntity findAccount(String email){
    return repository.findByEmail(email).orElse(null);
  }

  public AccountEntity createAccount(CreateAccountRequest createAccountRequest) {
    if(findAccount(createAccountRequest.getEmail()) == null){
      AccountEntity entity = new AccountEntity()
          .setName(createAccountRequest.getName())
          .setEmail(createAccountRequest.getEmail());
      return repository.save(entity);
    }else{
      throw new AccountAlreadyExistsException();
    }

  }

  public void removeAccount(String accountId) {
    repository.deleteById(accountId);
  }
}
