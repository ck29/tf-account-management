package com.assignment.tf.controller;

import com.assignment.tf.controller.request.CreateAccountRequest;
import com.assignment.tf.controller.request.UpdateAccountRequest;
import com.assignment.tf.controller.response.AccountResponse;
import com.assignment.tf.services.AccountService;
import com.assignment.swagger.SwaggerResources;
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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@Tag(name= SwaggerResources.ACCOUNT_CONTROLLER)
@RequestMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
public class AccountController {

  public final AccountService accountService;

  @GetMapping("/{account-id}")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "OK"),
      @ApiResponse(responseCode = "404", description = "Not found", content = @Content),
  })
  @Operation(
      summary = "Account detail retrieval"
  )
  public AccountResponse getAccountById(@PathVariable("account-id") String accountId){
    log.info("Retrieving account for account id {}.", accountId); //TODO dont show full account number, may be sensetive.
    return accountService.retrieveAccount(accountId);
  }

  @PostMapping("/new")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "201", description = "OK"),
      @ApiResponse(responseCode = "409", description = "Already exists.", content = @Content),
      @ApiResponse(responseCode = "400", description = "Bad request.", content = @Content),
  })
  @Operation(
      summary = "create new account."
  )
  @ResponseStatus(HttpStatus.ACCEPTED)
  public AccountResponse createAccount(@Valid @RequestBody final CreateAccountRequest createAccountRequest){
    log.info("Create account request received.");
    return accountService.createAccount(createAccountRequest);
  }


  @DeleteMapping("/{account-id}")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "OK"),
      @ApiResponse(responseCode = "404", description = "Not found", content = @Content),
  })
  @Operation(
      summary = "delete account."
  )
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteAccount(@PathVariable("account-id") String accountId){
    log.info("delete account request received.");
    accountService.removeAccount(accountId);
  }
}
