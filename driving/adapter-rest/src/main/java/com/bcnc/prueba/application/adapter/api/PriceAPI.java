package com.bcnc.prueba.application.adapter.api;

import com.bcnc.prueba.application.model.price.PriceDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.OffsetDateTime;

@Tag(name = "PriceApi", description = "Price API for price related calls")
@RequestMapping("/api/v1")
public interface PriceAPI {

    @Operation(summary = "Te saluda.")
    @GetMapping("/price")
    ResponseEntity<PriceDTO> getPrice(@RequestParam OffsetDateTime dateTime, @RequestParam Long productId, @RequestParam Long brandId);

}
