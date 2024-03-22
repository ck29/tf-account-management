package com.assignment.tf.controller;

import com.assignment.swagger.SwaggerResources;
import com.assignment.tf.controller.request.CreateAccountRequest;
import com.assignment.tf.controller.response.AccountResponse;
import com.assignment.tf.controller.validator.Iban;
import com.assignment.tf.services.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@Tag(name= SwaggerResources.ACCOUNT_CONTROLLER)
@RequestMapping(value = "/accounts", produces = MediaType.APPLICATION_JSON_VALUE)
public class AccountController {

  public final AccountService accountService;

  @GetMapping("/{iban}")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "OK"),
      @ApiResponse(responseCode = "404", description = "Not found", content = @Content),
  })
  @Operation(
      summary = "Account detail retrieval"
  )
  public AccountResponse getAccountById(@PathVariable @Iban String iban){
    log.info("Retrieving account for account id {}.", iban);
    return accountService.retrieveAccount(iban);
  }

  @PostMapping
  @ApiResponses(value = {
      @ApiResponse(responseCode = "201", description = "OK"),
      @ApiResponse(responseCode = "409", description = "Already exists.", content = @Content),
      @ApiResponse(responseCode = "400", description = "Bad request.", content = @Content),
  })
  @Operation(
      summary = "create new account."
  )
  @ResponseStatus(HttpStatus.CREATED)
  public AccountResponse createAccount(@Valid @RequestBody final CreateAccountRequest createAccountRequest){
    log.info("Create account request received.");
    return accountService.createAccount(createAccountRequest);
  }

 }
