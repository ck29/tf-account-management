package com.assignment.tf.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.assignment.tf.controller.request.CreateAccountRequest;
import com.assignment.tf.controller.response.AccountResponse;
import com.assignment.tf.controller.response.BalanceResponse;
import com.assignment.tf.exception.AccountAlreadyExistsException;
import com.assignment.tf.exception.AccountNotFoundException;
import com.assignment.tf.repositories.AccountRepositoryService;
import com.assignment.tf.repositories.entities.AccountEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class AccountServiceTest {

  @InjectMocks
  private AccountService accountService;

  @Mock
  private AccountRepositoryService accountRepositoryService;

  @Mock
  private BalanceService balanceService;

  @Test
  void retrieveAccount_returnAccountResponse() {
      when(accountRepositoryService.getAccount(any())).thenReturn(mockAccountEntity());
      when(balanceService.getBalance(any())).thenReturn(mockBalanceResponse());
      AccountResponse response = accountService.retrieveAccount("aAccountId");
      assertEquals(response.getAccountId(), "aAccount");
      assertEquals(response.getBalance(), "1000");

  }

  @Test
  void retrieveAccount_throwsExceptionWhenNotFound() {
    when(accountRepositoryService.getAccount(any())).thenThrow(AccountNotFoundException.class);
    assertThrows(AccountNotFoundException.class, ()->accountService.retrieveAccount("aAccountId"));
  }

  @Test
  void createAccount_returnAccountResponse() {
    when(accountRepositoryService.createAccount(any())).thenReturn(mockAccountEntity());
    when(balanceService.createAccount(any(),any())).thenReturn(mockBalanceResponse());
    AccountResponse response = accountService.createAccount(new CreateAccountRequest());
    assertEquals(response.getBalance(),"1000");
    assertEquals(response.getAccountId(),"aAccount");
  }

  @Test
  void createAccount_ThrowsConflictException() {
    when(accountRepositoryService.createAccount(any())).thenThrow(AccountAlreadyExistsException.class);

    assertThrows(AccountAlreadyExistsException.class, ()->accountService.createAccount(new CreateAccountRequest()));

  }

  private AccountEntity mockAccountEntity(){
    return new AccountEntity().setEmail("aAccountEmail")
        .setIban("aAccount")
        .setName("aName");
  }
  private BalanceResponse mockBalanceResponse(){
    return new BalanceResponse().setBalance("1000")
        .setAccountId("aAccount");

  }
}