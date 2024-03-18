package com.assignment.tf.controller.response;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class BalanceResponse {
  @Schema(requiredMode = RequiredMode.REQUIRED, example = "ace4e1-e23cb-3eaddf-45ffbcea")
  private String accountId;

  @Schema(requiredMode = RequiredMode.REQUIRED, example = "500")
  private String balance;
}
