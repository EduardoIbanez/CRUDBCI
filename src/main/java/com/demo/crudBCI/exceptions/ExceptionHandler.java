package com.demo.crudBCI.exceptions;

import com.demo.crudBCI.dto.response.ErrorDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {
    @org.springframework.web.bind.annotation.ExceptionHandler(value = CrudBCIException.class)
    protected ResponseEntity<ErrorDTO> requestHandleConflict(CrudBCIException ex){
        ErrorDTO error = new ErrorDTO();
        error.setMessage(ex.getMessage());
        return new ResponseEntity<>(error, ex.getHttpStatus());
    }
}
