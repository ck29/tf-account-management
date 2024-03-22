package com.assignment.tf.controller.validator;

import com.assignment.tf.exception.InvalidIbanException;
import jakarta.validation.ConstraintViolationException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler( MethodArgumentNotValidException.class)
  public ResponseEntity<String> handleValidationException(MethodArgumentNotValidException ex){

    String errorMessages = ex.getAllErrors().stream()
            .map(DefaultMessageSourceResolvable::getDefaultMessage)
            .collect(Collectors.joining(", "));

    Map<String, String> result = new HashMap<>();
    result.put("errors", errorMessages);
    result.put("status", String.valueOf(HttpStatus.BAD_REQUEST.value()));
    result.put("timestamp", String.valueOf(LocalDateTime.now()));

    return new ResponseEntity(result, HttpStatus.BAD_REQUEST);
  }

}
