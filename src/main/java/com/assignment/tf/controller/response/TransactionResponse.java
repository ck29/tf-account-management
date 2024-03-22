package com.assignment.tf.controller.response;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class TransactionResponse{

  @Schema(requiredMode = RequiredMode.REQUIRED, example = "NL66ABNA0112234968")
  private String accountId;

  @Schema(requiredMode = RequiredMode.REQUIRED, example = "500")
  private String lastTransactionAmount;
}
