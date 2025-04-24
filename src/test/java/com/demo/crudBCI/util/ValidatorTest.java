package com.demo.crudBCI.util;

import com.demo.crudBCI.constant.ConstantBCI;
import com.demo.crudBCI.dto.request.PhoneRequestDTO;
import com.demo.crudBCI.dto.request.UserRequestDTO;
import com.demo.crudBCI.dto.request.UserUpdateRequestDTO;
import com.demo.crudBCI.exceptions.CrudBCIException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class ValidatorTest {
    @InjectMocks
    private Validator validator;


    @Test
    void emptyDataValidatorUpdate() {
        UserUpdateRequestDTO userUpdateRequestDTOName = new UserUpdateRequestDTO("Aaaa","","aa@a.cl","aaA1.",true);
        UserUpdateRequestDTO userUpdateRequestDTOPass = new UserUpdateRequestDTO("Aaaa","AAA","","aaA1.",true);
        UserUpdateRequestDTO userUpdateRequestDTOEmail = new UserUpdateRequestDTO("Aaaa","AAA","aa@a.cl","",true);

        CrudBCIException exName = Assertions.assertThrows(CrudBCIException.class, () -> validator.emptyDataValidatorUpdate(userUpdateRequestDTOName));
        CrudBCIException exPass = Assertions.assertThrows(CrudBCIException.class, () -> validator.emptyDataValidatorUpdate(userUpdateRequestDTOPass));
        CrudBCIException exEmail = Assertions.assertThrows(CrudBCIException.class, () -> validator.emptyDataValidatorUpdate(userUpdateRequestDTOEmail));

        Assertions.assertEquals(ConstantBCI.EMPTY_NAME_ERROR, exName.getMessage());
        Assertions.assertEquals(ConstantBCI.EMPTY_EMAIL_ERROR, exPass.getMessage());
        Assertions.assertEquals(ConstantBCI.EMPTY_PASSWORD_ERROR, exEmail.getMessage());

    }

    @Test
    void emptyDataValidator() {
        PhoneRequestDTO phoneRequestDto = new PhoneRequestDTO(1111111,1,1);
        List<PhoneRequestDTO> listPhonesRequest = new ArrayList<>();
        listPhonesRequest.add(phoneRequestDto);
        UserRequestDTO userDtoName = new UserRequestDTO("","aa@aa.com","abcd",listPhonesRequest);
        UserRequestDTO userDtoPass = new UserRequestDTO("Ever","","abcd",listPhonesRequest);
        UserRequestDTO userDtoEmail = new UserRequestDTO("Ever","aa@aa.com","",listPhonesRequest);

        CrudBCIException exName = Assertions.assertThrows(CrudBCIException.class, () -> validator.emptyDataValidator(userDtoName));
        CrudBCIException exPass = Assertions.assertThrows(CrudBCIException.class, () -> validator.emptyDataValidator(userDtoPass));
        CrudBCIException exEmail = Assertions.assertThrows(CrudBCIException.class, () -> validator.emptyDataValidator(userDtoEmail));

        Assertions.assertEquals(ConstantBCI.EMPTY_NAME_ERROR, exName.getMessage());
        Assertions.assertEquals(ConstantBCI.EMPTY_EMAIL_ERROR, exPass.getMessage());
        Assertions.assertEquals(ConstantBCI.EMPTY_PASSWORD_ERROR, exEmail.getMessage());
    }
}