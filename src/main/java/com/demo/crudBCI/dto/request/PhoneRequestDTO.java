package com.demo.crudBCI.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PhoneRequestDTO {
    public int number;
    public int citycode;
    public int contrycode;
}
