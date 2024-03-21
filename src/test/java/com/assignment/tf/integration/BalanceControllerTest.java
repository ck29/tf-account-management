package com.assignment.tf.integration;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.assignment.tf.controller.request.TransactionRequest;
import com.assignment.tf.repositories.BalanceRepository;
import com.assignment.tf.repositories.entities.BalanceEntity;
import java.math.BigDecimal;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class BalanceControllerTest extends AbstractControllerIntegrationTest{

  @Autowired
  private BalanceRepository balanceRepository;


  @AfterEach
  public void setup() {
    balanceRepository.deleteAll();
  }


  @Test
  void test_getBalance() throws Exception {

    balanceRepository.save(new BalanceEntity().setIban("abc").setBalance(new BigDecimal(1000)));

    performGet("/balance/abc" )
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.balance").value("1000.00"));
  }

  @Test
  void test_credit() throws Exception {

    balanceRepository.save(new BalanceEntity().setIban("abc").setBalance(new BigDecimal(1000)));
    TransactionRequest request = new TransactionRequest().setAccountId("abc").setAmount(new BigDecimal("500"));
    performPost("/balance/credit",request )
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.lastTransactionAmount").value("500"));
  }

  @Test
  void test_debit() throws Exception {

    balanceRepository.save(new BalanceEntity().setIban("abc").setBalance(new BigDecimal(1000)));

    TransactionRequest request = new TransactionRequest().setAccountId("abc").setAmount(new BigDecimal("100"));

    performPost("/balance/debit", request )
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.lastTransactionAmount").value("100"));
  }
}
