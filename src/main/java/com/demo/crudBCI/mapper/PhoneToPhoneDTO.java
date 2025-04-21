package com.demo.crudBCI.mapper;

import com.demo.crudBCI.dto.response.PhoneResponseDTO;
import com.demo.crudBCI.entity.Phone;
import org.springframework.stereotype.Component;

@Component
public class PhoneToPhoneDTO implements IMapper<Phone, PhoneResponseDTO>{
    @Override
    public PhoneResponseDTO map(Phone in) {
        PhoneResponseDTO phoneDto  =new PhoneResponseDTO();
        phoneDto.setNumber(in.getNumber());
        phoneDto.setCityCode(in.getCityCode());
        phoneDto.setCountryCode(in.getCountryCode());
        return phoneDto;
    }
}
