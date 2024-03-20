package com.assignment.tf.repositories;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.assignment.tf.exception.AccountNotFoundException;
import com.assignment.tf.exception.InsufficientFundException;
import com.assignment.tf.repositories.entities.BalanceEntity;
import java.math.BigDecimal;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class BalanceRepositoryServiceTest {

  @InjectMocks
  private BalanceRepositoryService balanceRepositoryService;

  @Mock
  private BalanceRepository repository;

  @Test
  void getBalance_returnsResponse() {
    when(repository.findById(any())).thenReturn(mockOptionalBalanceEntity());
    Optional<BalanceEntity> response = balanceRepositoryService.getBalance("aAccount");
    assertEquals(response.get().getBalance(), new BigDecimal(1000));
  }

  @Test
  void createAccount_returnResponse() {
    when(repository.save(any())).thenReturn(mockBalanceEntity());
    BalanceEntity response = balanceRepositoryService.createAccount("aAccount",new BigDecimal(500));
    assertEquals(response.getBalance(),new BigDecimal(1000));
  }

  @Test
  void add_whenAccountExistsUpdateBalance() {
    when(repository.findById(any())).thenReturn(mockOptionalBalanceEntity());
    balanceRepositoryService.add("aAccount",new BigDecimal(500));

    ArgumentCaptor<BalanceEntity> argCaptor = ArgumentCaptor.forClass(BalanceEntity.class);

    verify(repository,times(1)).save(argCaptor.capture());
    BalanceEntity argEntity = argCaptor.getValue();
    assertEquals(argEntity.getBalance(), new BigDecimal("1500"));
  }

  @Test
  void add_whenAccountDoesntExists_ThrowException() {
    when(repository.findById(any())).thenReturn(Optional.empty());
    assertThrows(AccountNotFoundException.class, ()->balanceRepositoryService.add("aAccount",new BigDecimal(500)));
  }

  @Test
  void deduct_whenBalanceIsEnough_returnsResponse() {
    when(repository.findById(any())).thenReturn(mockOptionalBalanceEntity());
    balanceRepositoryService.deduct("aAccount",new BigDecimal(500));

    ArgumentCaptor<BalanceEntity> argCaptor = ArgumentCaptor.forClass(BalanceEntity.class);

    verify(repository,times(1)).save(argCaptor.capture());
    BalanceEntity argEntity = argCaptor.getValue();
    assertEquals(argEntity.getBalance(), new BigDecimal("500"));
  }

  @Test
  void deduct_whenAccountDoesntExists_ThrowException() {
    when(repository.findById(any())).thenReturn(Optional.empty());
    assertThrows(AccountNotFoundException.class, ()->balanceRepositoryService.deduct("aAccount",new BigDecimal(500)));
  }

  @Test
  void deduct_whenInsufficientBalance_ThrowException() {
    when(repository.findById(any())).thenReturn(mockOptionalBalanceEntity());
    assertThrows(InsufficientFundException.class, ()->balanceRepositoryService.deduct("aAccount",new BigDecimal(1500)));
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
