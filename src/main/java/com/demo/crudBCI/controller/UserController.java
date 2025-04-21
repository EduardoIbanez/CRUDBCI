package com.demo.crudBCI.controller;

import com.demo.crudBCI.dto.request.UserRequestDTO;
import com.demo.crudBCI.dto.response.UserResponseDTO;
import com.demo.crudBCI.service.CRUDUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class UserController {

    private final CRUDUserService crudUserService;

    public UserController(CRUDUserService crudUserService) {
        this.crudUserService = crudUserService;
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<UserResponseDTO> getUser(@PathVariable String uuid){
        return new ResponseEntity<>(crudUserService.getUser(uuid), HttpStatus.OK);
    }
    @PostMapping("/create")
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody UserRequestDTO user){
        return new ResponseEntity<>(crudUserService.createUser(user), HttpStatus.CREATED);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateUser(){
        return new ResponseEntity<>(null);
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<?> updatePatchUser(){
        return new ResponseEntity<>(null);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteUser(){
        return new ResponseEntity<>(null);
    }

}
