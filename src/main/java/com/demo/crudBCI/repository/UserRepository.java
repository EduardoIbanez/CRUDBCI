package com.demo.crudBCI.repository;

import com.demo.crudBCI.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

public interface UserRepository extends JpaRepository<User, String> {

    User findByUuid(String uuid);
    User findByEmail(String email);
    @Modifying
    @Transactional
    @Query(value = "UPDATE User SET name=:#{#user.name}, email=:#{#user.email}, password=:#{#user.password}, " +
            "modified=:#{#user.modified}, isActive=:#{#user.isActive} WHERE uuid=:#{#user.uuid} ")
    void updateUser(@Param("user") User user);

    @Modifying
    @Transactional
    @Query(value = "UPDATE User SET isActive=:#{#user.isActive} WHERE uuid=:#{#user.uuid} ")
    void updatePatchUser(@Param("user") User user);

    //void deleteByUuid(String uuid);

    @Override
    void deleteById(String uuid);
}
