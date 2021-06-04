package ru.mos.etp.exceptions;

import org.springframework.http.HttpStatus;

public class CustomException extends Exception {

    private final HttpStatus responseStatus;

    public CustomException(String message, HttpStatus responseStatus) {
        super(message);
        this.responseStatus = responseStatus;
    }

    public HttpStatus getResponseStatus() {return responseStatus;}
}