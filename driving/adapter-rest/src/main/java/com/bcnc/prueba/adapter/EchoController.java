package com.bcnc.prueba.adapter;

import com.bcnc.prueba.adapter.api.EchoApi;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class EchoController implements EchoApi {
    @Override
    public String greetings() {
        return "Greetings";
    }
}
