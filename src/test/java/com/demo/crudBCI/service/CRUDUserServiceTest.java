package com.demo.crudBCI.service;

import com.demo.crudBCI.dto.request.GetUserDTO;
import com.demo.crudBCI.dto.response.PhoneResponseDTO;
import com.demo.crudBCI.dto.response.UserResponseDTO;
import com.demo.crudBCI.entity.Phone;
import com.demo.crudBCI.entity.User;
import com.demo.crudBCI.mapper.PhoneToPhoneDTO;
import com.demo.crudBCI.repository.PhoneRepository;
import com.demo.crudBCI.repository.UserRepository;
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
    }

    @Test
    void updateUser() {
    }

    @Test
    void updatePatchUser() {
    }

    @Test
    void deleteUser() {
    }
}