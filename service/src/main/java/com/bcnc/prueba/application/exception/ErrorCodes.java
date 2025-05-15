package com.bcnc.prueba.application.exception;

import lombok.Getter;

@Getter
public enum ErrorCodes {
    GENERIC_ERROR("PRUEBA01"),
    PRICE_NOT_FOUND("PRUEBA02");

    private final String code;

    ErrorCodes(String code) {
        this.code = code;
    }
}
