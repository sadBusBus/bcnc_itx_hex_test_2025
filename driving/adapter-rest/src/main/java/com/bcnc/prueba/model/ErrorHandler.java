package com.bcnc.prueba.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ErrorHandler {
    @JsonProperty("code")
    private String code;
    @JsonProperty("locale")
    private String locale;
    @JsonProperty("description")
    private String description;
    @JsonProperty("details")
    private List<String> details;
}
