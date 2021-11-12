package com.cvs.poc.SpringMongoPoc.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InValidEmailAddressException extends Exception{
    public InValidEmailAddressException(String message) {
        super(message);
    }
}
