package com.assignment.tf.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InsufficientFundException  extends RuntimeException{

  public InsufficientFundException() {
  }
}
