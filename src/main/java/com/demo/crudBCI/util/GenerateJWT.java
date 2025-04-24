package com.demo.crudBCI.util;

import com.demo.crudBCI.dto.request.UserRequestDTO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class GenerateJWT {

    final int expiration = 3600000;
    private final String SECRET = "kkjjkas8bbbkabsa8abskbjskjasje443jjaq3j3jqjqrn3qjjqk4q3jkjq34bq3k3nj42nk4j23x";

    public SecretKey generateKey(){
        return Keys.hmacShaKeyFor(SECRET.getBytes());
    }

    public String generateToken(UserRequestDTO userRequestDTO, SecretKey secretKey){
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList("ROLE_USER");
        String token = Jwts.builder()
                .subject(userRequestDTO.getName())
                .claim("authorities",
                        grantedAuthorities.stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toList()))
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(secretKey,SignatureAlgorithm.HS512)
                .compact();
        return "Bearer "+token;

    }
}
