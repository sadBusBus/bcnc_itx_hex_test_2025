package com.bcnc.prueba.exception;

import java.util.List;

public class ForbiddenException extends BusinessException{

    public ForbiddenException(String message , List<Object> args , String errorCode) {
        super(message, args, errorCode);
    }

}
