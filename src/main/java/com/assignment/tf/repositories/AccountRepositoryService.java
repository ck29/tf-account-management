package com.assignment.tf.repositories;

import com.assignment.tf.controller.request.CreateAccountRequest;
import com.assignment.tf.exception.AccountAlreadyExistsException;
import com.assignment.tf.exception.AccountNotFoundException;
import com.assignment.tf.repositories.entities.AccountEntity;
import com.assignment.tf.util.AccountUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountRepositoryService {

  private final AccountRepository repository;

  public AccountEntity getAccount(String accountId){
    return repository.findByIban(accountId).orElseThrow(AccountNotFoundException::new);
  }

  public AccountEntity findAccount(String email){
    return repository.findByEmail(email).orElse(null);
  }

  public AccountEntity createAccount(CreateAccountRequest createAccountRequest) {
    if(findAccount(createAccountRequest.getEmail()) == null){

      AccountEntity entity = new AccountEntity()
          .setName(createAccountRequest.getName())
          .setEmail(createAccountRequest.getEmail())
          .setIban(AccountUtil.createIban());
      return repository.save(entity);
    }else{
      throw new AccountAlreadyExistsException();
    }

  }

}
