package com.assignment.tf.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class AccountAlreadyExistsException  extends RuntimeException{

  public AccountAlreadyExistsException(){

  }
}
