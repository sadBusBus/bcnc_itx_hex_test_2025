package com.bcnc.prueba.adapter;

import com.bcnc.prueba.application.service.MessagesService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import java.util.List;
import java.util.Locale;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class MessagesServiceTest {

    @InjectMocks
    private MessagesService messagesService;

    @Mock
    private MessageSource messageSource;

    @Test
    void getMensajes() {
        Object[] fake_campo = new Object[1];
        fake_campo[0] = "autorizacion";
        String fake_code = "NotNull";
        String fake_mensaje = "El campo autorizacion debe estar informado";

        Locale locale = LocaleContextHolder.getLocale();
        when(messageSource.getMessage(fake_code, fake_campo, locale)).thenReturn(fake_mensaje);

        String mensaje = messagesService.getMessage(fake_code, List.of(fake_campo));

        assertEquals(fake_mensaje, mensaje);
    }

}
