package com.demo.crudBCI.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserActiveDTO {
    private String uuid;
    private Boolean isActive;
}
