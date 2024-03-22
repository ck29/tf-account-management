package com.assignment.tf.controller.validator;

import com.assignment.tf.util.AccountUtil;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class IbanValidator implements ConstraintValidator<Iban, String> {

  @Override
  public void initialize(Iban iban) {
  }

  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    return AccountUtil.isValidIban(value);
  }
}
