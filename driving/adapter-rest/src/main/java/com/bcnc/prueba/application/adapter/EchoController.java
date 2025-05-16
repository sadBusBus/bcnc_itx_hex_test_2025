package com.bcnc.prueba.application.adapter;

import com.bcnc.api.EchoApi;
import com.bcnc.prueba.application.exception.BusinessException;
import com.bcnc.prueba.application.exception.ErrorCodes;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class EchoController implements EchoApi {
    @Override
    public ResponseEntity<String> greetings() {
        throw new BusinessException(ErrorCodes.GENERIC_ERROR.name(), List.of(),ErrorCodes.GENERIC_ERROR.getCode())     ;

    }
}
