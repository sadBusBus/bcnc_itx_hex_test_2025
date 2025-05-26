/*
 * Copyright (c) 2025 BCNC.
 * All rights reserved.
 */
package com.bcnc.prueba.application.exception;

import java.util.List;

public class BadRequestException extends BusinessException {

  public BadRequestException(String message, List<Object> args, String errorCode) {
    super(message, args, errorCode);
  }
}
