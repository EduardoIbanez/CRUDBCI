package com.demo.crudBCI.util;

import com.demo.crudBCI.dto.request.UserRequestDTO;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class GenerateJWT {

    final int expiration = 3600000;

    public SecretKey generateKey(){

        return Keys.secretKeyFor(SignatureAlgorithm.HS512);
    }

    public String generateToken(UserRequestDTO userRequestDTO, SecretKey secretKey){
        return
                Jwts.builder()
                        .subject(userRequestDTO.getName())
                        .issuedAt(new Date())
                        .expiration(new Date(System.currentTimeMillis() + expiration))
                        .signWith(secretKey)
                        .compact();
    }


}
