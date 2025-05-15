package com.bcnc.prueba.application.service;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public class MessagesService {

    private final MessageSource messageSource;

    @SneakyThrows
    public String getMessage(String code , List<Object> args) {
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage(code, args.toArray(), locale);
    }
}
