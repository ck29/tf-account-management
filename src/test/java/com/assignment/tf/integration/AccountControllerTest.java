package com.assignment.tf.integration;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.assignment.tf.controller.request.CreateAccountRequest;
import com.assignment.tf.repositories.AccountRepository;
import com.assignment.tf.repositories.entities.AccountEntity;
import java.math.BigDecimal;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MvcResult;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class AccountControllerTest  extends AbstractControllerIntegrationTest{



  @Autowired
  private AccountRepository accountRepository;


  @AfterEach
  public void setup() {
    accountRepository.deleteAll();
  }

  @Test
  public void test_createAccount() throws Exception {
    CreateAccountRequest request = new CreateAccountRequest()
        .setEmail("aEmail@email.com")
        .setName("aName")
        .setOpeningBalance(new BigDecimal("500.00"));

    MvcResult result = performPost("/new", request)
        .andExpect(status().is2xxSuccessful())
        .andReturn();
  }

  @Test
  public void test_createAccountThrowExceptionWhenEmailAlreadyExists() throws Exception {
    CreateAccountRequest request = new CreateAccountRequest()
        .setEmail("aEmail@email.com")
        .setName("aName")
        .setOpeningBalance(new BigDecimal("500.00"));

    accountRepository.save(new AccountEntity().setEmail("aEmail@email.com")
        .setName("someName"));
    MvcResult result = performPost("/new", request)
        .andExpect(status().is4xxClientError())
        .andReturn();
  }

}
