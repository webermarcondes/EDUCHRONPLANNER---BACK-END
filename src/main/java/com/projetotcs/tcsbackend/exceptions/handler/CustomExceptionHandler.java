package com.projetotcs.tcsbackend.exceptions.handler;


import com.projetotcs.tcsbackend.exceptions.StatusInativoException;
import com.projetotcs.tcsbackend.exceptions.message.CustomExceptionMessage;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
@RestController
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    private ResponseEntity<CustomExceptionMessage> ResouceNotFoundHandler(ResourceNotFoundException exception, WebRequest request) {

        CustomExceptionMessage customExceptionMessage = new CustomExceptionMessage(HttpStatus.NOT_FOUND,
                exception.getMessage(),
                request.getDescription(false));


        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(customExceptionMessage);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    private ResponseEntity<CustomExceptionMessage> DataIntegrityViolationHandler(DataIntegrityViolationException exception, WebRequest request) {

        CustomExceptionMessage customExceptionMessage = new CustomExceptionMessage(HttpStatus.CONFLICT,
                exception.getMessage(),
                request.getDescription(false));

        return ResponseEntity.status(HttpStatus.CONFLICT).body(customExceptionMessage);
    }

    @ExceptionHandler(StatusInativoException.class)
    private ResponseEntity<CustomExceptionMessage> StatusInativoHandler(StatusInativoException exception, WebRequest request) {

        CustomExceptionMessage customExceptionMessage = new CustomExceptionMessage(HttpStatus.UNPROCESSABLE_ENTITY,
                                                        exception.getMessage(),
                                                        request.getDescription(false));



        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(customExceptionMessage);
    }

}
