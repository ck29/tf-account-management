package com.assignment.tf.repositories;


import static com.assignment.tf.repositories.BalanceRepository.MIN_BALANCE;

import com.assignment.tf.exception.AccountNotFoundException;
import com.assignment.tf.exception.InsufficientFundException;
import com.assignment.tf.repositories.entities.BalanceEntity;
import java.math.BigDecimal;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BalanceRepositoryService {

  private final BalanceRepository repository;

  public Optional<BalanceEntity> getBalance(String accountId) {
    return repository.findById(accountId);
  }


  public BalanceEntity add(String accountId, BigDecimal creditAmount){
    Optional<BalanceEntity> optionalBalance = getBalance(accountId);

    BalanceEntity balance = null;
    if(optionalBalance.isPresent()) {
      balance = optionalBalance.get();
      balance.setBalance(balance.getBalance().add(creditAmount));
    }else{
      balance = new BalanceEntity()
          .setBalance(creditAmount)
          .setAccountId(accountId);
    }
    return repository.save(balance);
  }

  public BalanceEntity deduct(String accountId, BigDecimal debitAmount){
    Optional<BalanceEntity> optionalBalance = getBalance(accountId);

    BalanceEntity balance = null;
    if(optionalBalance.isPresent()){
      balance = optionalBalance.get();
      balance.setBalance(balance.getBalance().subtract(debitAmount));

      if(balance.getBalance().compareTo(MIN_BALANCE) < 0){
        throw new InsufficientFundException();
      }
      return repository.save(balance);
    }else{
      throw new AccountNotFoundException();
    }
  }

  public void removeAccount(String accountId){
    repository.deleteById(accountId);
  }

}
