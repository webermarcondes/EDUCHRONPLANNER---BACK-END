package com.projetotcs.tcsbackend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class StatusInativoException extends Exception {

    public StatusInativoException(){}

    public StatusInativoException(String message) {
        super(message);
    }
}
