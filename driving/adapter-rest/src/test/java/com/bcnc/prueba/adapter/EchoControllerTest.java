/*
 * Copyright (c) 2025 BCNC.
 * All rights reserved.
 */
package com.bcnc.prueba.adapter;

import com.bcnc.prueba.application.adapter.EchoController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith({MockitoExtension.class})
class EchoControllerTest {

  @InjectMocks private EchoController echoController;

  @Test
  void testEcho() {
    // when
    String result = echoController.greetings().getBody();
    // then
    Assertions.assertEquals("Greetings", result);
  }
}
