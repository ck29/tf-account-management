package com.assignment.tf.exception;

import org.springframework.http.HttpStatus;

public class AccountNotFoundException extends RuntimeException{
  public final String name = "SERVICE_ACCOUNT_NOT_FOUND_EXCEPTION";
  private final int httpStatus = HttpStatus.NOT_FOUND.value();
  private final String description = "Account not found %s";
  private final String [] params;

  public AccountNotFoundException(String e){
    this.params = new String[]{e};
  }

}
