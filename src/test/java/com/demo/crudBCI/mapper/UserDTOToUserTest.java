package com.demo.crudBCI.mapper;

import com.demo.crudBCI.dto.request.PhoneRequestDTO;
import com.demo.crudBCI.dto.request.UserRequestDTO;
import com.demo.crudBCI.entity.User;
import com.demo.crudBCI.util.GenerateJWT;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.crypto.SecretKey;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class UserDTOToUserTest {
    @Mock
    private GenerateJWT generateJWT;
    @InjectMocks
    private UserDTOToUser userDTOToUser;

    @Test
    void map() {
        PhoneRequestDTO phoneRequestDto = new PhoneRequestDTO(1111111,1,1);
        List<PhoneRequestDTO> listPhonesRequest = new ArrayList<>();
        listPhonesRequest.add(phoneRequestDto);
        UserRequestDTO user = new UserRequestDTO("Ever","aaa@a.com","abcdE1",listPhonesRequest);

        String token = "87as9dbasd8sd79a8s";
        SecretKey key = generateJWT.generateKey();
        Mockito.when(generateJWT.generateToken(user,key)).thenReturn(token);
        User userMap = userDTOToUser.map(user);

        Assertions.assertNotNull(userMap);
    }
}