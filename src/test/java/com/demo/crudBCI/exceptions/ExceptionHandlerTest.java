package com.demo.crudBCI.exceptions;

import com.demo.crudBCI.dto.response.ErrorDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class ExceptionHandlerTest {

    @InjectMocks
    private ExceptionHandler exceptionHandler;

    @Test
    void requestHandleConflict() {
        CrudBCIException ex = new CrudBCIException("error", HttpStatus.BAD_REQUEST);
        ErrorDTO error = new ErrorDTO();
        error.setMessage("error");

        ResponseEntity<ErrorDTO> errorResponse = exceptionHandler.requestHandleConflict(ex);

        Assertions.assertNotNull(errorResponse);
    }
}