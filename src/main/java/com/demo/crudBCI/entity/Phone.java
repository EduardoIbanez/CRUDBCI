package com.demo.crudBCI.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Entity
@Table(name = "Phone")
@AllArgsConstructor
@NoArgsConstructor
public class Phone implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long idPhone;
    public int number;
    public int cityCode;
    public int countryCode;
    public String uuidUser;
}
