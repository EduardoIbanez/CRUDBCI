package com.demo.crudBCI.util;

import com.demo.crudBCI.constant.ConstantBCI;
import com.demo.crudBCI.dto.request.UserRequestDTO;
import com.demo.crudBCI.dto.request.UserUpdateRequestDTO;
import com.demo.crudBCI.exceptions.CrudBCIException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class Validator {
    public static boolean emailValidator (String email) {
        Pattern pattern = Pattern.compile("^([0-9a-zA-Z]+[-._+&])*[0-9a-zA-Z]+@([-0-9a-zA-Z]+[.])+[a-zA-Z]{2,6}$");
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static boolean passValidator(String pass){
        Pattern pattern = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+.,:!])(?=\\S+$).{4,8}$");
        Matcher matcher = pattern.matcher(pass);
        return matcher.matches();
    }

    public  void emptyDataValidatorUpdate(UserUpdateRequestDTO userUpdateRequestDTO){
        if(userUpdateRequestDTO.getName().trim().isEmpty()){
            throw  new CrudBCIException(ConstantBCI.EMPTY_NAME_ERROR, HttpStatus.BAD_REQUEST);
        } else if (userUpdateRequestDTO.getEmail().trim().isEmpty()) {
            throw  new CrudBCIException(ConstantBCI.EMPTY_EMAIL_ERROR,HttpStatus.BAD_REQUEST);
        } else if (userUpdateRequestDTO.getPassword().trim().isEmpty()) {
            throw  new CrudBCIException(ConstantBCI.EMPTY_PASSWORD_ERROR,HttpStatus.BAD_REQUEST);
        }
    }

    public  void emptyDataValidator(UserRequestDTO userRequestDTO){
        if(userRequestDTO.getName().trim().isEmpty()){
            throw  new CrudBCIException(ConstantBCI.EMPTY_NAME_ERROR,HttpStatus.BAD_REQUEST);
        } else if (userRequestDTO.getEmail().trim().isEmpty()) {
            throw  new CrudBCIException(ConstantBCI.EMPTY_EMAIL_ERROR,HttpStatus.BAD_REQUEST);
        } else if (userRequestDTO.getPassword().trim().isEmpty()) {
            throw  new CrudBCIException(ConstantBCI.EMPTY_PASSWORD_ERROR,HttpStatus.BAD_REQUEST);
        }
    }
}
