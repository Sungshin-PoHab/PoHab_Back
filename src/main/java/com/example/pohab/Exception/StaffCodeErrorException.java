package com.example.pohab.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Staff Code Error")
public class StaffCodeErrorException extends RuntimeException {

    public StaffCodeErrorException(String message) {
        super(message);
    }
}
