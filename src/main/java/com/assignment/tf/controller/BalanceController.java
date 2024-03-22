package com.assignment.tf.controller;


import com.assignment.swagger.SwaggerResources;
import com.assignment.tf.controller.request.TransactionRequest;
import com.assignment.tf.controller.response.BalanceResponse;
import com.assignment.tf.controller.response.TransactionResponse;
import com.assignment.tf.controller.validator.Iban;
import com.assignment.tf.services.BalanceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@Tag(name= SwaggerResources.BALANCE_CONTROLLER)
@RequestMapping(value = "/balance", produces = MediaType.APPLICATION_JSON_VALUE)
public class BalanceController {

  private final BalanceService balanceService;

  @GetMapping("/{iban}")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "OK"),
      @ApiResponse(responseCode = "404", description = "Not found", content = @Content),
  })
  @Operation(
      summary = "balance retrieval"
  )
  public BalanceResponse getAccountBalance(@PathVariable @Iban String iban){
    log.info("Retrieving account balance for account id {}.", iban);
    return balanceService.getBalance(iban);
  }

  @PostMapping("/credit")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "OK"),
      @ApiResponse(responseCode = "400", description = "Bad request.", content = @Content),
  })
  @Operation(
      summary = "credit amount."
  )
  public TransactionResponse creditAmount(@Valid @RequestBody final TransactionRequest creditRequest){
    log.info("credit request received.");
    return balanceService.credit(creditRequest);
  }

  @PostMapping("/debit")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "OK"),
      @ApiResponse(responseCode = "400", description = "Bad request.", content = @Content),
      @ApiResponse(responseCode = "404", description = "Not found.", content = @Content),
  })
  @Operation(
      summary = "debit amount."
  )
  public TransactionResponse debitAmount(@Valid @RequestBody final TransactionRequest debitRequest){
    log.info("credit request received.");
    return balanceService.debit(debitRequest);
  }
}
