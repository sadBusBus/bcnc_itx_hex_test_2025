package com.bcnc.prueba.adapter.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Tag(name = "EchoApi" , description = "API testeo CICD")
@RequestMapping("/api/v1")
public interface EchoApi {

    @Operation(summary = "Te saluda.")
    @GetMapping("/echo")
    String greetings();

}
