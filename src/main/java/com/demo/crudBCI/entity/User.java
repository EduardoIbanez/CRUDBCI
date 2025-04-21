package com.demo.crudBCI.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "User")
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
    @Id
    public final String uuid = UUID.randomUUID().toString();
    @Column(nullable = false)
    public String name;
    @Column(nullable = false, unique = true)
    public String email;
    @Column(nullable = false)
    public String password;
    public LocalDateTime created;
    public LocalDateTime modified;
    public LocalDateTime lastLogin;
    public String token;
    public Boolean isActive;

}
