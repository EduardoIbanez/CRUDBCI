package com.demo.crudBCI.mapper;

public interface IMapper <I,O>{
    public O map (I in);
}
