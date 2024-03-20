package com.assignment.tf.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.assignment.tf.controller.response.BalanceResponse;
import com.assignment.tf.controller.response.TransactionResponse;
import com.assignment.tf.repositories.BalanceRepositoryService;
import com.assignment.tf.repositories.entities.BalanceEntity;
import java.math.BigDecimal;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class BalanceServiceTest {

  @InjectMocks
  private BalanceService balanceService;

  @Mock
  private BalanceRepositoryService repositoryService;

  @Test
  void getBalance_returnsBalance() {
    when(repositoryService.getBalance(any())).thenReturn(mockOptionalBalanceEntity());
    BalanceResponse response = balanceService.getBalance("aAccount");
    assertEquals(response.getBalance(),"1000");
  }

  @Test
  void createAccount_returnBalanceResponse() {
    when(repositoryService.createAccount(any(),any())).thenReturn(mockBalanceEntity());
    BalanceResponse response = balanceService.createAccount("aAccount", new BigDecimal(500));
    assertEquals(response.getBalance(),"1000");
    assertEquals(response.getAccountId(),"aAccount");

  }

  @Test
  void credit() {
    when(repositoryService.add(any(),any())).thenReturn(mockBalanceEntity());
    TransactionResponse response = balanceService.credit("aAccount", new BigDecimal(500));
    assertEquals(response.getLastTransactionAmount(), "500");
  }

  @Test
  void debit() {
    when(repositoryService.deduct(any(),any())).thenReturn(mockBalanceEntity());
    TransactionResponse response = balanceService.debit("aAccount", new BigDecimal(500));
    assertEquals(response.getLastTransactionAmount(), "500");
  }

  private BalanceEntity mockBalanceEntity(){
    return mockOptionalBalanceEntity().orElse(null);
  }
  private Optional<BalanceEntity> mockOptionalBalanceEntity(){
    return Optional.of(new BalanceEntity()
        .setLastTransactionAmount(new BigDecimal(500))
        .setAccountId("aAccount")
        .setBalance(new BigDecimal(1000)));
  }
}