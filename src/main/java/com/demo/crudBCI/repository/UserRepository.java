package com.demo.crudBCI.repository;

import com.demo.crudBCI.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {

    User findByUuid(String uuid);
    User findByEmail(String email);
}
