package com.demo.crudBCI.dto.request;

import com.demo.crudBCI.dto.response.PhoneResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateRequestDTO {
    private String uuid;
    private String name;
    private String email;
    private String password;
    private Boolean isActive;
}
