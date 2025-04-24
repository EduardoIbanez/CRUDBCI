package com.demo.crudBCI.service;

import com.demo.crudBCI.constant.ConstantBCI;
import com.demo.crudBCI.dto.request.*;
import com.demo.crudBCI.dto.response.PhoneResponseDTO;
import com.demo.crudBCI.dto.response.SuccessDTO;
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

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class CRUDUserService {
    private final UserRepository userRepository;
    private final PhoneRepository phoneRepository;
    private final UserDTOToUser mapperUser;
    private final PhoneToPhoneDTO mapperPhone;
    private final Validator utilValidator;

    public CRUDUserService(UserRepository userRepository, PhoneRepository phoneRepository, UserDTOToUser mapperUser, PhoneToPhoneDTO mapperPhone, Validator utilValidator) {
        this.userRepository = userRepository;
        this.phoneRepository = phoneRepository;
        this.mapperUser = mapperUser;
        this.mapperPhone = mapperPhone;
        this.utilValidator = utilValidator;
    }

    public UserResponseDTO getUser(GetUserDTO userDTO){
        User user = this.userRepository.findByUuid(userDTO.getUuid());
        if (Objects.nonNull(user)){
            return generateResponse(user);
        }else {
            throw  new CrudBCIException(ConstantBCI.GET_USER_ERROR, HttpStatus.BAD_REQUEST);
        }
    }

    public List<UserResponseDTO> getAllUser(){
        List<UserResponseDTO> listAllUser = new ArrayList<>();
        List<User> users = this.userRepository.findAll();
        for (User user : users) {
            listAllUser.add(generateResponse(user));
        }
        return listAllUser;
    }

    public UserResponseDTO createUser(UserRequestDTO userRequestDTO){
        User userPhones = new User();
        this.utilValidator.emptyDataValidator(userRequestDTO);
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

    public UserResponseDTO updateUser(UserUpdateRequestDTO userUpdateRequestDTO){
        User userUpdate = this.userRepository.findByUuid(userUpdateRequestDTO.getUuid());
        if(Objects.isNull(userUpdate)){
            throw  new CrudBCIException(ConstantBCI.GET_USER_ERROR,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        this.utilValidator.emptyDataValidatorUpdate(userUpdateRequestDTO);
        boolean emailValidator = Validator.emailValidator(userUpdateRequestDTO.getEmail());
        boolean passValidator = Validator.passValidator(userUpdateRequestDTO.getPassword());
        if(!emailValidator){
            throw  new CrudBCIException(ConstantBCI.EMAIL_ERROR, HttpStatus.BAD_REQUEST);
        }else if(!passValidator){
            throw  new CrudBCIException(ConstantBCI.PASSWORD_ERROR,HttpStatus.BAD_REQUEST);
        }else{
            updateUserData(userUpdateRequestDTO, userUpdate);
        }
        return generateResponse(userUpdate);
    }

    public UserResponseDTO updatePatchUser(UserPatchDTO userPatchDTO){
        if(userPatchDTO.getUuid().isBlank()){
            throw  new CrudBCIException(ConstantBCI.UUID_USER_ERROR, HttpStatus.BAD_REQUEST);
        }
        User userUpdate = this.userRepository.findByUuid(userPatchDTO.getUuid());
        if(Objects.isNull(userUpdate)){
            throw  new CrudBCIException(ConstantBCI.GET_USER_ERROR,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        updatePatchUserData(userPatchDTO, userUpdate);
        return generateResponse(userUpdate);
    }

    public SuccessDTO deleteUser(GetUserDTO userDTO){
        User user = this.userRepository.findByUuid(userDTO.getUuid());
        if (Objects.nonNull(user)){
            List <Phone> phones = this.phoneRepository.findByUuidUser(userDTO.getUuid());
            for (Phone phone : phones) {
                this.phoneRepository.deleteById(phone.getIdPhone());
            }
            this.userRepository.deleteById(userDTO.getUuid());
            SuccessDTO succes = new SuccessDTO();
            succes.setMessage(ConstantBCI.DELETE_USER_SUCCESS);
            return succes;
        }else {
            throw  new CrudBCIException(ConstantBCI.UUID_USER_ERROR, HttpStatus.BAD_REQUEST);
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

    private void createPhone(User user, PhoneRequestDTO phones){
        Phone phoneCreate = new Phone();
        phoneCreate.setNumber(phones.getNumber());
        phoneCreate.setCityCode(phones.getCitycode());
        phoneCreate.setCountryCode(phones.getContrycode());
        phoneCreate.setUuidUser(user.getUuid());
        this.phoneRepository.save(phoneCreate);
    }

    private void updateUserData(UserUpdateRequestDTO userUpdateRequestDTO, User userUpdate){
        userUpdate.setModified(LocalDateTime.now());
        userUpdate.setName(userUpdateRequestDTO.getName().isBlank() ? userUpdate.getName(): userUpdateRequestDTO.getName());
        userUpdate.setEmail(userUpdateRequestDTO.getEmail().isBlank() ? userUpdate.getEmail() : userUpdateRequestDTO.getEmail());
        userUpdate.setPassword(userUpdateRequestDTO.getPassword().isBlank() ? userUpdate.getPassword() : userUpdateRequestDTO.getPassword());
        userUpdate.setIsActive(userUpdateRequestDTO.getIsActive());
        this.userRepository.updateUser(userUpdate);
    }

    private void updatePatchUserData(UserPatchDTO userPatchDTO, User userUpdate){
        userUpdate.setModified(LocalDateTime.now());
        userUpdate.setIsActive(userPatchDTO.getIsActive());
        this.userRepository.updatePatchUser(userUpdate);
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
