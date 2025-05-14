package com.bcnc.prueba.exception;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class BusinessException extends RuntimeException{

    protected final List<Object> messageVariables = new ArrayList<>();
    protected final transient List<String> details = new ArrayList<>();
    protected String genericErrorCode = "C01";
    protected String locale;

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String message , Throwable cause) {
        super(message,cause);
    }

    public BusinessException(String message , String genericErrorCode) {
        super(message);
        this.genericErrorCode = genericErrorCode;
    }

    public BusinessException(String message,List<Object> messageVariables , String genericErrorCode) {
        super(message);
        this.messageVariables.addAll(messageVariables);
        this.genericErrorCode = genericErrorCode;
    }

    public BusinessException(String message,List<Object> messageVariables , String genericErrorCode, String locale) {
        super(message);
        this.messageVariables.addAll(messageVariables);
        this.genericErrorCode = genericErrorCode;
        this.locale = locale;
    }


}
