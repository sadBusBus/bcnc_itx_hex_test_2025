/*
 * Copyright (c) 2025 BCNC.
 * All rights reserved.
 */
package com.bcnc.prueba.application.exception;

import java.util.List;

public class ForbiddenException extends BusinessException {

  public ForbiddenException(String message, List<Object> args, String errorCode) {
    super(message, args, errorCode);
  }
}
