package com.bcnc.prueba.application.exception;

import java.util.List;

public class NotAuthorizedException extends BusinessException{
    public NotAuthorizedException(String message , List<Object> args , String errorCode) {
        super(message, args, errorCode);
    }

}
