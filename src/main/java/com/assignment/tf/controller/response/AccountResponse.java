package com.assignment.tf.controller.response;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class AccountResponse {

  @Schema(requiredMode = RequiredMode.REQUIRED, example = "ace4e1-e23cb-3eaddf-45ffbcea")
  private String accountId;

  @Schema(requiredMode = RequiredMode.REQUIRED, example = "Tony")
  private String name;

  @Schema(requiredMode = RequiredMode.REQUIRED, example = "Tony@tf.com")
  private String email;

  @Schema(requiredMode = RequiredMode.REQUIRED, example = "2000.00")
  private String balance;
}
