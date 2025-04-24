package com.demo.crudBCI.service;

import com.demo.crudBCI.constant.ConstantBCI;
import com.demo.crudBCI.dto.request.*;
import com.demo.crudBCI.dto.response.PhoneResponseDTO;
import com.demo.crudBCI.dto.response.UserResponseDTO;
import com.demo.crudBCI.entity.Phone;
import com.demo.crudBCI.entity.User;
import com.demo.crudBCI.exceptions.CrudBCIException;
import com.demo.crudBCI.mapper.PhoneToPhoneDTO;
import com.demo.crudBCI.mapper.UserDTOToUser;
import com.demo.crudBCI.repository.PhoneRepository;
import com.demo.crudBCI.repository.UserRepository;
import com.demo.crudBCI.util.Validator;
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
class CRUDUserServiceTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private PhoneRepository phoneRepository;
    @Mock
    private PhoneToPhoneDTO mapperPhone;
    @Mock
    private UserDTOToUser mapperUser;
    @Mock
    private Validator validator;

    @InjectMocks
    private CRUDUserService crudUserService;

    @Test
    void getUser() {
        GetUserDTO userDTO = new GetUserDTO(Mockito.anyString());
        User user = new User("a","a@a.com","abcdE1.", LocalDateTime.now(),LocalDateTime.now(), LocalDateTime.now(),"1a2b3c4d",true);

        List<Phone> phones = new ArrayList<>();
        Phone phone = new Phone(1L,1111111,1,1,"a-b-c-d");
        PhoneResponseDTO phoneDto = new PhoneResponseDTO(1111111,1,1);
        phones.add(phone);
        Mockito.when(userRepository.findByUuid(userDTO.getUuid())).thenReturn(user);
        Mockito.when(mapperPhone.map(phone)).thenReturn(phoneDto);
        Mockito.when(phoneRepository.findByUuidUser(user.getUuid())).thenReturn(phones);

        Assertions.assertNotNull(crudUserService.getUser(userDTO));
    }

    @Test
    void getAllUser() {
        List<UserResponseDTO> listAllUser = new ArrayList<>();

        List<User> listUsers = new ArrayList<>();
        List<Phone> listPhones = new ArrayList<>();

        Phone phone = new Phone(1L,1111111,1,1,"a-b-c-d");
        listPhones.add(phone);

        User user = new User("a","a@a.com","abcdE1.", LocalDateTime.now(),LocalDateTime.now(), LocalDateTime.now(),"1a2b3c4d",true);
        listUsers.add(user);

        List<PhoneResponseDTO> listPhonesResponseDTO = new ArrayList<>();
        PhoneResponseDTO phoneResponseDTO = new PhoneResponseDTO(1111,11,1);
        listPhonesResponseDTO.add(phoneResponseDTO);

        UserResponseDTO userResponseDTO= new UserResponseDTO("a","a@a.cl","assA1.","a1b2c3d4",LocalDateTime.now(),LocalDateTime.now(),LocalDateTime.now(),"aa33fe",true,listPhonesResponseDTO);

        listAllUser.add(userResponseDTO);

        Mockito.when(userRepository.findAll()).thenReturn(listUsers);
        Mockito.when(userRepository.findByUuid(user.getUuid())).thenReturn(user);
        Mockito.when(mapperPhone.map(phone)).thenReturn(phoneResponseDTO);
        Mockito.when(phoneRepository.findByUuidUser(user.getUuid())).thenReturn(listPhones);

        Assertions.assertNotNull(crudUserService.getAllUser());
    }

    @Test
    void createUser() {

        PhoneRequestDTO phoneRequestDto = new PhoneRequestDTO(1111111,1,1);
        List<PhoneRequestDTO> listPhonesRequest = new ArrayList<>();
        listPhonesRequest.add(phoneRequestDto);
        User user = new User("a","a@a.com","abcdE1.", LocalDateTime.now(),LocalDateTime.now(), LocalDateTime.now(),"1a2b3c4d",true);

        Phone phone = new Phone(1L,1111111,1,1,"a-b-c-d");
        List<Phone> listPhones = new ArrayList<>();
        listPhones.add(phone);
        List<PhoneResponseDTO> listPhonesResponseDTO = new ArrayList<>();
        PhoneResponseDTO phoneResponseDTO = new PhoneResponseDTO(1111,11,1);
        listPhonesResponseDTO.add(phoneResponseDTO);
        UserRequestDTO userDto = new UserRequestDTO("Ever","aaa@a.com","abcdE1.",listPhonesRequest);


        Mockito.when(userRepository.findByEmail("b@bcom")).thenReturn(user);
        Mockito.when(mapperUser.map(userDto)).thenReturn(user);
        Mockito.when(userRepository.findByUuid(user.getUuid())).thenReturn(user);
        Mockito.when(mapperPhone.map(phone)).thenReturn(phoneResponseDTO);
        Mockito.when(phoneRepository.findByUuidUser(user.getUuid())).thenReturn(listPhones);

        Assertions.assertNotNull(crudUserService.createUser(userDto));

    }

    @Test
    void updateUser() {

        User user = new User("a","a@a.com","abcdE1.", LocalDateTime.now(),LocalDateTime.now(), LocalDateTime.now(),"1a2b3c4d",true);
        UserUpdateRequestDTO userUpdateRequestDTO = new UserUpdateRequestDTO("Aaaa","Alan","aa@a.cl","aaA1.",true);
        Phone phone = new Phone(1L,1111111,1,1,"a-b-c-d");
        List<Phone> listPhones = new ArrayList<>();
        listPhones.add(phone);
        List<PhoneResponseDTO> listPhonesResponseDTO = new ArrayList<>();
        PhoneResponseDTO phoneResponseDTO = new PhoneResponseDTO(1111,11,1);
        listPhonesResponseDTO.add(phoneResponseDTO);

        Mockito.when(userRepository.findByUuid(Mockito.anyString())).thenReturn(user);
        Mockito.when(mapperPhone.map(phone)).thenReturn(phoneResponseDTO);
        Mockito.when(phoneRepository.findByUuidUser(user.getUuid())).thenReturn(listPhones);

        Assertions.assertNotNull(crudUserService.updateUser(userUpdateRequestDTO));
    }

    @Test
    void updatePatchUser() {
        UserPatchDTO userPatchDTO = new UserPatchDTO("aa21",false);
        User user = new User("a","a@a.com","abcdE1.", LocalDateTime.now(),LocalDateTime.now(), LocalDateTime.now(),"1a2b3c4d",true);
        Phone phone = new Phone(1L,1111111,1,1,"a-b-c-d");
        List<Phone> listPhones = new ArrayList<>();
        listPhones.add(phone);
        List<PhoneResponseDTO> listPhonesResponseDTO = new ArrayList<>();
        PhoneResponseDTO phoneResponseDTO = new PhoneResponseDTO(1111,11,1);
        listPhonesResponseDTO.add(phoneResponseDTO);

        Mockito.when(userRepository.findByUuid(Mockito.anyString())).thenReturn(user);
        Mockito.when(mapperPhone.map(phone)).thenReturn(phoneResponseDTO);
        Mockito.when(phoneRepository.findByUuidUser(user.getUuid())).thenReturn(listPhones);

        Assertions.assertNotNull(crudUserService.updatePatchUser(userPatchDTO));
    }

    @Test
    void deleteUser() {
        GetUserDTO userDTO = new GetUserDTO("s12c");
        User user = new User("a","a@a.com","abcdE1.", LocalDateTime.now(),LocalDateTime.now(), LocalDateTime.now(),"1a2b3c4d",true);
        Phone phone = new Phone(1L,1111111,1,1,"a-b-c-d");
        List<Phone> listPhones = new ArrayList<>();
        listPhones.add(phone);

        Mockito.when(userRepository.findByUuid(Mockito.anyString())).thenReturn(user);
        Mockito.when(phoneRepository.findByUuidUser(user.getUuid())).thenReturn(listPhones);

        Assertions.assertNotNull(crudUserService.deleteUser(userDTO));
    }

    @Test
    void getUSerError(){
        GetUserDTO getUserDTO = new GetUserDTO("aas3");

        CrudBCIException ex = Assertions.assertThrows(CrudBCIException.class, () -> crudUserService.getUser(getUserDTO));

        Assertions.assertEquals(ConstantBCI.GET_USER_ERROR, ex.getMessage());
    }

    @Test
    void createEmailError(){
        PhoneRequestDTO phoneRequestDto = new PhoneRequestDTO(1111111,1,1);
        List<PhoneRequestDTO> listPhonesRequest = new ArrayList<>();
        listPhonesRequest.add(phoneRequestDto);
        UserRequestDTO userDto = new UserRequestDTO("Ever","aaaa.com","abcdE1.",listPhonesRequest);

        CrudBCIException ex = Assertions.assertThrows(CrudBCIException.class, () -> crudUserService.createUser(userDto));

        Assertions.assertEquals(ConstantBCI.EMAIL_ERROR, ex.getMessage());
    }

    @Test
    void createPassError(){
        PhoneRequestDTO phoneRequestDto = new PhoneRequestDTO(1111111,1,1);
        List<PhoneRequestDTO> listPhonesRequest = new ArrayList<>();
        listPhonesRequest.add(phoneRequestDto);
        UserRequestDTO userDto = new UserRequestDTO("Ever","aa@aa.com","abcd",listPhonesRequest);

        CrudBCIException ex = Assertions.assertThrows(CrudBCIException.class, () -> crudUserService.createUser(userDto));

        Assertions.assertEquals(ConstantBCI.PASSWORD_ERROR, ex.getMessage());
    }

    @Test
    void createEmailDuplicateError(){
        PhoneRequestDTO phoneRequestDto = new PhoneRequestDTO(1111111,1,1);
        List<PhoneRequestDTO> listPhonesRequest = new ArrayList<>();
        listPhonesRequest.add(phoneRequestDto);
        UserRequestDTO userDto = new UserRequestDTO("Ever","aa@a.cl","abcdA1.",listPhonesRequest);
        User user = new User("a","aa@a.cl","abcdA1.", LocalDateTime.now(),LocalDateTime.now(), LocalDateTime.now(),"",true);

        Mockito.when(userRepository.findByEmail("aa@a.cl")).thenReturn(user);

        CrudBCIException ex = Assertions.assertThrows(CrudBCIException.class, () -> crudUserService.createUser(userDto));

        Assertions.assertEquals(ConstantBCI.EMAIL_REGISTER_ERROR, ex.getMessage());
    }

    @Test
    void updateGetUserError(){
        User user = new User("juan","juanmail.cl","aaccA1.", LocalDateTime.now(),LocalDateTime.now(), LocalDateTime.now(),"1a2b3c4d",true);
        UserUpdateRequestDTO userUpdateRequestDTO = new UserUpdateRequestDTO("aa12","juan","juanmail.cl","aaccA1.",true);

        Mockito.when(userRepository.findByUuid("asd4")).thenReturn(user);
        CrudBCIException ex = Assertions.assertThrows(CrudBCIException.class, () -> crudUserService.updateUser(userUpdateRequestDTO));

        Assertions.assertEquals(ConstantBCI.GET_USER_ERROR, ex.getMessage());
    }

    @Test
    void updateEmailError(){
        User user = new User("juan","juanmail.cl","aaccA1.", LocalDateTime.now(),LocalDateTime.now(), LocalDateTime.now(),"1a2b3c4d",true);
        UserUpdateRequestDTO userUpdateRequestDTO = new UserUpdateRequestDTO("aa12","juan","juanmail.cl","aaccA1.",true);

        Mockito.when(userRepository.findByUuid(Mockito.anyString())).thenReturn(user);
        CrudBCIException ex = Assertions.assertThrows(CrudBCIException.class, () -> crudUserService.updateUser(userUpdateRequestDTO));

        Assertions.assertEquals(ConstantBCI.EMAIL_ERROR, ex.getMessage());
    }

    @Test
    void updatePassError(){
        User user = new User("juan","juan@mail.cl","aaccA1.", LocalDateTime.now(),LocalDateTime.now(), LocalDateTime.now(),"1a2b3c4d",true);
        UserUpdateRequestDTO userUpdateRequestDTO = new UserUpdateRequestDTO("aa12","juan","juan@mail.cl","aaccA",true);

        Mockito.when(userRepository.findByUuid(Mockito.anyString())).thenReturn(user);
        CrudBCIException ex = Assertions.assertThrows(CrudBCIException.class, () -> crudUserService.updateUser(userUpdateRequestDTO));

        Assertions.assertEquals(ConstantBCI.PASSWORD_ERROR, ex.getMessage());
    }


    @Test
    void updatePatchUUIDError(){
        UserPatchDTO userPatchDTO = new UserPatchDTO("",true);

        CrudBCIException ex = Assertions.assertThrows(CrudBCIException.class, () -> crudUserService.updatePatchUser(userPatchDTO));

        Assertions.assertEquals(ConstantBCI.UUID_USER_ERROR, ex.getMessage());
    }

    @Test
    void updatePatchGetUserError(){
        UserPatchDTO userPatchDTO = new UserPatchDTO("as5a",true);
        User user = new User("juan","juan@mail.cl","aaccA1.", LocalDateTime.now(),LocalDateTime.now(), LocalDateTime.now(),"1a2b3c4d",true);

        Mockito.when(userRepository.findByUuid("asd7g")).thenReturn(user);
        CrudBCIException ex = Assertions.assertThrows(CrudBCIException.class, () -> crudUserService.updatePatchUser(userPatchDTO));

        Assertions.assertEquals(ConstantBCI.GET_USER_ERROR, ex.getMessage());
    }

    @Test
    void deleteUUIDError(){
        GetUserDTO getUserDTO = new GetUserDTO("");

        CrudBCIException ex = Assertions.assertThrows(CrudBCIException.class, () -> crudUserService.deleteUser(getUserDTO));

        Assertions.assertEquals(ConstantBCI.UUID_USER_ERROR, ex.getMessage());
    }
}