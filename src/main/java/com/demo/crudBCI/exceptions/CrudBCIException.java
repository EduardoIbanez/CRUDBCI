package com.demo.crudBCI.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;
@EqualsAndHashCode(callSuper = true)
@Data
public class CrudBCIException extends RuntimeException {
    private String message;
    private HttpStatus httpStatus;
    public CrudBCIException(String message, HttpStatus httpStatus) {
        super(message);
        this.message = message;
        this.httpStatus = httpStatus;
    }
}
