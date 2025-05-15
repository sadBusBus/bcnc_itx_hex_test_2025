package com.bcnc.prueba.application.adapter;

import com.bcnc.api.EchoApi;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class EchoController implements EchoApi {
    @Override
    public ResponseEntity<String> greetings() {
        return ResponseEntity.ok("Greetings");
    }
}
