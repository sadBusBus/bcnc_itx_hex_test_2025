/*
 * Copyright (c) 2025 BCNC.
 * All rights reserved.
 */
package com.bcnc.prueba.application.config;

import com.bcnc.prueba.application.exception.BadRequestException;
import com.bcnc.prueba.application.exception.BusinessException;
import com.bcnc.prueba.application.exception.ForbiddenException;
import com.bcnc.prueba.application.exception.NotFoundException;
import com.bcnc.prueba.application.model.ErrorHandler;
import com.bcnc.prueba.application.service.MessagesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@RequiredArgsConstructor
public class ApiExceptionHandler {

  private final MessagesService messagesService;

  @ExceptionHandler(value = NotFoundException.class)
  public ResponseEntity<ErrorHandler> handleNotFoundException(NotFoundException ex) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(createError(ex));
  }

  @ExceptionHandler(value = BadRequestException.class)
  public ResponseEntity<ErrorHandler> handleBadRequestException(BadRequestException ex) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(createError(ex));
  }

  @ExceptionHandler(value = BusinessException.class)
  public ResponseEntity<ErrorHandler> handleBusinessException(BusinessException ex) {
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(createError(ex));
  }

  @ExceptionHandler(value = ForbiddenException.class)
  public ResponseEntity<ErrorHandler> handleForbiddenException(ForbiddenException ex) {
    return ResponseEntity.status(HttpStatus.FORBIDDEN).body(createError(ex));
  }

  private ErrorHandler createError(BusinessException ex) {
    return ErrorHandler.builder()
        .code(ex.getGenericErrorCode())
        .locale(ex.getLocale())
        .description(messagesService.getMessage(ex.getMessage(), ex.getMessageVariables()))
        .build();
  }
}
