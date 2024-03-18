package com.assignment.tf.controller.request;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class CreateAccountRequest {


  @NotNull
  @Schema(requiredMode = RequiredMode.REQUIRED, example = "Tony")
  private String name;

  @Email
  @Schema(requiredMode = RequiredMode.REQUIRED, example = "Tony@tf.com")
  private String email;

  @DecimalMin(value = "0.0", inclusive = false)
  @Digits(integer=3, fraction=2)
  @Schema(requiredMode = RequiredMode.REQUIRED, example = "2000.00")
  private BigDecimal openingBalance;

}
