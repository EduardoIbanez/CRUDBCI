package com.demo.crudBCI.service;

import com.demo.crudBCI.constant.ConstantBCI;
import com.demo.crudBCI.dto.request.PhoneRequestDTO;
import com.demo.crudBCI.dto.request.UserRequestDTO;
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
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class CRUDUserService {
    private final UserRepository userRepository;
    private final PhoneRepository phoneRepository;
    private final UserDTOToUser mapperUser;
    private final PhoneToPhoneDTO mapperPhone;

    public CRUDUserService(UserRepository userRepository, PhoneRepository phoneRepository, UserDTOToUser mapperUser, PhoneToPhoneDTO mapperPhone) {
        this.userRepository = userRepository;
        this.phoneRepository = phoneRepository;
        this.mapperUser = mapperUser;
        this.mapperPhone = mapperPhone;
    }

    public UserResponseDTO getUser(String uuid){
        User user = this.userRepository.findByUuid(uuid);
        if (Objects.nonNull(user)){
            return generateResponse(user);
        }else {
            throw  new CrudBCIException(ConstantBCI.GET_USER_ERROR, HttpStatus.BAD_REQUEST);
        }
    }

    public UserResponseDTO createUser(UserRequestDTO userRequestDTO){
        User userPhones = new User();
        emptyDataValidator(userRequestDTO);
        boolean emailValidator = Validator.emailValidator(userRequestDTO.getEmail());
        boolean passValidator = Validator.passValidator(userRequestDTO.getPassword());
        User existEmail = this.userRepository.findByEmail(userRequestDTO.getEmail());

        if(!emailValidator){
            throw  new CrudBCIException(ConstantBCI.EMAIL_ERROR, HttpStatus.BAD_REQUEST);
        }else if(!passValidator){
            throw  new CrudBCIException(ConstantBCI.PASSWORD_ERROR,HttpStatus.BAD_REQUEST);
        }else if(Objects.nonNull(existEmail)){
            throw  new CrudBCIException(ConstantBCI.EMAIL_REGISTER_ERROR,HttpStatus.INTERNAL_SERVER_ERROR);
        }else{
            userPhones = createNewUser(userRequestDTO);
        }

        return generateResponse(userPhones);
    }

    private  void emptyDataValidator(UserRequestDTO userRequestDTO){
        if(userRequestDTO.getName().trim().isEmpty()){
            throw  new CrudBCIException(ConstantBCI.EMPTY_NAME_ERROR,HttpStatus.BAD_REQUEST);
        } else if (userRequestDTO.getEmail().trim().isEmpty()) {
            throw  new CrudBCIException(ConstantBCI.EMPTY_EMAIL_ERROR,HttpStatus.BAD_REQUEST);
        } else if (userRequestDTO.getPassword().trim().isEmpty()) {
            throw  new CrudBCIException(ConstantBCI.EMPTY_PASSWORD_ERROR,HttpStatus.BAD_REQUEST);
        }
    }

    private User createNewUser(UserRequestDTO userRequestDTO) {
        User user = mapperUser.map(userRequestDTO);
        this.userRepository.save(user);
        User userPhones = this.userRepository.findByUuid(user.getUuid());

        if(Objects.nonNull(userPhones)) {
            for (int i = 0; i < userRequestDTO.getPhones().size(); i++) {
                createPhone(userPhones, userRequestDTO.getPhones().get(i));
            }
        }
        return userPhones;
    }

    private Phone createPhone(User user, PhoneRequestDTO phones){
        Phone phoneCreate = new Phone();
        phoneCreate.setNumber(phones.getNumber());
        phoneCreate.setCityCode(phones.getCitycode());
        phoneCreate.setCountryCode(phones.getContrycode());
        phoneCreate.setUuidUser(user.getUuid());
        return this.phoneRepository.save(phoneCreate);
    }

    private UserResponseDTO generateResponse(User user){
        User getUser = this.userRepository.findByUuid(user.getUuid());
        List<Phone> phones  = this.phoneRepository.findByUuidUser(user.getUuid());

        UserResponseDTO userResponse  = new UserResponseDTO();
        userResponse.setName(getUser.getName());
        userResponse.setEmail(getUser.getEmail());
        userResponse.setPassword(getUser.getPassword());
        userResponse.setUuid(getUser.getUuid());
        userResponse.setCreated(getUser.getCreated());
        userResponse.setModified(getUser.getModified());
        userResponse.setLastLogin(getUser.getLastLogin());
        userResponse.setToken(getUser.getToken());
        userResponse.setIsActive(getUser.getIsActive());
        userResponse.setPhones(converterPhones(phones));

        return userResponse;
    }
    private List<PhoneResponseDTO> converterPhones(List<Phone> phones){
        List<PhoneResponseDTO> phonesResponseDTO = new ArrayList<>();
        for (Phone phone : phones) {
            PhoneResponseDTO phoneResponseMapper = this.mapperPhone.map(phone);
            phonesResponseDTO.add(phoneResponseMapper);
        }
        return phonesResponseDTO;
    }
}
