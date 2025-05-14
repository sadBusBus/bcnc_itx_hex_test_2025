package com.bcnc.prueba.model;

import lombok.Builder;

import java.util.List;

@Builder
public class ErrorHandler {
    private String code;
    private String locale;
    private String description;
    private List<String> details;
}
