package com.projetotcs.tcsbackend.exceptions.message;

import org.springframework.http.HttpStatus;

public class CustomExceptionMessage {

    private HttpStatus httpStatus;
    private String message;
    private String uri;




    public CustomExceptionMessage(HttpStatus httpStatus, String message, String uri) {
        this.httpStatus = httpStatus;
        this.message = message;
        this.uri = uri.split("=")[1];


    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

}
