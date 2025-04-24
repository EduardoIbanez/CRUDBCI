package com.demo.crudBCI.controller;

import com.demo.crudBCI.dto.request.*;
import com.demo.crudBCI.dto.response.PhoneResponseDTO;
import com.demo.crudBCI.dto.response.SuccessDTO;
import com.demo.crudBCI.dto.response.UserResponseDTO;
import com.demo.crudBCI.service.CRUDUserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class UserControllerTest {
    @Mock
    private CRUDUserService crudUserService;

    @InjectMocks
    private UserController userController;
    @Test
    void getUser() {
        GetUserDTO getUser = new GetUserDTO("244s");
        PhoneResponseDTO phoneResponse = new PhoneResponseDTO(1111111,1,1);
        List<PhoneResponseDTO> listPhoneResponse = new ArrayList<>();
        listPhoneResponse.add(phoneResponse);
        UserResponseDTO userResponseDto = new UserResponseDTO("Ever","aaa@a.com","abcdE1","HAS5-HAS2-833H-8S8D", LocalDateTime.now(),LocalDateTime.now(),LocalDateTime.now(),"ASDASDASDKJBKJYAS7DAS",true,listPhoneResponse);

        Mockito.when(crudUserService.getUser(getUser)).thenReturn(userResponseDto);

        Assertions.assertNotNull(userController.getUser(getUser));
    }

    @Test
    void getAllUser() {
        List<UserResponseDTO> listAllUser = new ArrayList<>();
        List<PhoneResponseDTO> listPhonesResponseDTO = new ArrayList<>();
        PhoneResponseDTO phoneResponseDTO = new PhoneResponseDTO(1111,11,1);
        listPhonesResponseDTO.add(phoneResponseDTO);
        UserResponseDTO userResponseDTO= new UserResponseDTO("a","a@a.cl","assA1.","a1b2c3d4",LocalDateTime.now(),LocalDateTime.now(),LocalDateTime.now(),"aa33fe",true,listPhonesResponseDTO);
        listAllUser.add(userResponseDTO);

        Mockito.when(crudUserService.getAllUser()).thenReturn(listAllUser);

        Assertions.assertNotNull(userController.getAllUser());
    }

    @Test
    void createUser() {
        PhoneRequestDTO phone = new PhoneRequestDTO(1111111,1,1);
        List<PhoneRequestDTO> listPhonesRequest = new ArrayList<>();
        listPhonesRequest.add(phone);
        UserRequestDTO userDto = new UserRequestDTO("Ever","aaa@a.com","abcdE1",listPhonesRequest);
        PhoneResponseDTO phoneResponse = new PhoneResponseDTO(1111111,1,1);
        List<PhoneResponseDTO> listPhoneResponse = new ArrayList<>();
        listPhoneResponse.add(phoneResponse);
        UserResponseDTO userResponseDto = new UserResponseDTO("Ever","aaa@a.com","abcdE1","HAS5-HAS2-833H-8S8D", LocalDateTime.now(),LocalDateTime.now(),LocalDateTime.now(),"ASDASDASDKJBKJYAS7DAS",true,listPhoneResponse);

        Mockito.when(crudUserService.createUser(userDto)).thenReturn(userResponseDto);

        Assertions.assertNotNull(userController.createUser(userDto));

    }

    @Test
    void updateUser() {
        UserUpdateRequestDTO userUpdateRequestDTO = new UserUpdateRequestDTO("Aaaa","Alan","aa@a.cl","aaA1.",true);
        PhoneResponseDTO phoneResponse = new PhoneResponseDTO(1111111,1,1);
        List<PhoneResponseDTO> listPhoneResponse = new ArrayList<>();
        listPhoneResponse.add(phoneResponse);
        UserResponseDTO userResponseDto = new UserResponseDTO("Ever","aaa@a.com","abcdE1","HAS5-HAS2-833H-8S8D", LocalDateTime.now(),LocalDateTime.now(),LocalDateTime.now(),"ASDASDASDKJBKJYAS7DAS",true,listPhoneResponse);

        Mockito.when(crudUserService.updateUser(userUpdateRequestDTO)).thenReturn(userResponseDto);

        Assertions.assertNotNull(userController.updateUser(userUpdateRequestDTO));
    }

    @Test
    void updatePatchUser() {
        UserPatchDTO userPatchDTO = new UserPatchDTO("aa21",false);
        PhoneResponseDTO phoneResponse = new PhoneResponseDTO(1111111,1,1);
        List<PhoneResponseDTO> listPhoneResponse = new ArrayList<>();
        listPhoneResponse.add(phoneResponse);
        UserResponseDTO userResponseDto = new UserResponseDTO("Ever","aaa@a.com","abcdE1","HAS5-HAS2-833H-8S8D", LocalDateTime.now(),LocalDateTime.now(),LocalDateTime.now(),"ASDASDASDKJBKJYAS7DAS",true,listPhoneResponse);

        Mockito.when(crudUserService.updatePatchUser(userPatchDTO)).thenReturn(userResponseDto);

        Assertions.assertNotNull(userController.updatePatchUser(userPatchDTO));
    }

    @Test
    void deleteUser() {
        GetUserDTO getUserDTO = new GetUserDTO("qw23");
        SuccessDTO successDTO = new SuccessDTO("succes");

        Mockito.when(crudUserService.deleteUser(getUserDTO)).thenReturn(successDTO);

        Assertions.assertNotNull(userController.deleteUser(getUserDTO));

    }
}