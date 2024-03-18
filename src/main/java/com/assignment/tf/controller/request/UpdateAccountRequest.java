package com.assignment.tf.controller.request;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class UpdateAccountRequest {

  @NotNull
  @Schema(requiredMode = RequiredMode.NOT_REQUIRED, example = "Tony")
  private String name;

  @Email
  @Schema(requiredMode = RequiredMode.NOT_REQUIRED, example = "Tony@tf.com")
  private String email;

}
