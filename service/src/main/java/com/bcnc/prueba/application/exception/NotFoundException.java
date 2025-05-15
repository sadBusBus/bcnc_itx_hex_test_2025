package com.bcnc.prueba.application.exception;

import java.util.List;

public class NotFoundException extends BusinessException{

    public NotFoundException(String message , List<Object> args , String errorCode) {
        super(message, args, errorCode);
    }

}
