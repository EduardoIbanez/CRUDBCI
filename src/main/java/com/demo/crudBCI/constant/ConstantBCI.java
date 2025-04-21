package com.demo.crudBCI.constant;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class ConstantBCI {
    public static final String EMPTY_NAME_ERROR= "Campo Name vacío";
    public static final String EMPTY_EMAIL_ERROR= "Campo Email vacío";
    public static final String EMPTY_PASSWORD_ERROR= "Campo Password vacío";
    public static final String EMAIL_ERROR= "Email inválido";
    public static final String PASSWORD_ERROR= "Contraseña inválida";
    public static final String EMAIL_REGISTER_ERROR= "El email ya está registrado";
    public static final String GET_USER_ERROR= "El usuario con ese UUID no existe";

}
