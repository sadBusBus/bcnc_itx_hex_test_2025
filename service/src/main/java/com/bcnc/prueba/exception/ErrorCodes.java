package com.bcnc.prueba.exception;

import lombok.Getter;

@Getter
public enum ErrorCodes {
    GENERIC_ERROR("PRUEBA01");

    private final String code;

    ErrorCodes(String code) {
        this.code = code;
    }
}
