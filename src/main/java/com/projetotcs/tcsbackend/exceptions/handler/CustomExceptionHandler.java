package com.projetotcs.tcsbackend.exceptions.handler;


import com.projetotcs.tcsbackend.exceptions.message.CustomExceptionMessage;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice  //Anotação usada para centralizar o tratamento de exceptions nessa classe
@RestController
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    private ResponseEntity<CustomExceptionMessage> ResouceNotFoundHandler(ResourceNotFoundException exception, WebRequest request) {

        CustomExceptionMessage customExceptionMessage = new CustomExceptionMessage(HttpStatus.NOT_FOUND,
                                                                        exception.getMessage(),
                                                                        request.getDescription(false));


        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(customExceptionMessage);
    }
}
