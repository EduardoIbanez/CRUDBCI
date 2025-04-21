package com.demo.crudBCI.repository;

import com.demo.crudBCI.entity.Phone;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PhoneRepository extends JpaRepository<Phone, Long> {

    List<Phone> findByUuidUser(String uuidUser);
}

