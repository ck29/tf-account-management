package com.assignment.tf.controller;

import com.assignment.tf.controller.response.AccountResponse;
import com.assignment.tf.services.AccountService;
import com.assignment.tf.swagger.SwaggerResources;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@Tag(name= SwaggerResources.ACCOUNT_MANAGEMENT)
@RequestMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
public class AccountController {

  public AccountService accountService;

  @GetMapping("{account-id}")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "OK"),
      @ApiResponse(responseCode = "404", description = "Not found"),
  })
  @Operation(
      summary = "Account detail retrieval"
  )
  public AccountResponse getAccountById(@PathVariable("account-id") String accountId){
    log.info("Retrieving account for account id {}", accountId); //TODO dont show full account number, may be sensetive.
    return accountService.retrieveAccount(accountId);
  }
}
