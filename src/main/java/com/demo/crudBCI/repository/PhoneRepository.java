package com.demo.crudBCI.repository;

import com.demo.crudBCI.entity.Phone;
import com.demo.crudBCI.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PhoneRepository extends JpaRepository<Phone, Long> {

    List<Phone> findByUuidUser(String uuidUser);

    @Override
    void deleteById(Long id);
}

