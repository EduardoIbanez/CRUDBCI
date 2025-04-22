package com.demo.crudBCI.controller;

import com.demo.crudBCI.dto.request.GetUserDTO;
import com.demo.crudBCI.dto.request.UserPatchDTO;
import com.demo.crudBCI.dto.request.UserRequestDTO;
import com.demo.crudBCI.dto.request.UserUpdateRequestDTO;
import com.demo.crudBCI.dto.response.SuccessDTO;
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

    @GetMapping("user")
    public ResponseEntity<UserResponseDTO> getUser(@RequestBody GetUserDTO getUser){
        return new ResponseEntity<>(crudUserService.getUser(getUser), HttpStatus.OK);
    }
    @PostMapping("create")
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody UserRequestDTO user){
        return new ResponseEntity<>(crudUserService.createUser(user), HttpStatus.CREATED);
    }
    @PutMapping("updateActive")
    public ResponseEntity<UserResponseDTO> updateUser(@RequestBody UserUpdateRequestDTO user){
        return new ResponseEntity<>(crudUserService.updateUser(user), HttpStatus.CREATED);
    }

    @PatchMapping("updatePatch")
    public ResponseEntity<UserResponseDTO> updatePatchUser(@RequestBody UserPatchDTO userPatchDTO){
        return new ResponseEntity<>(crudUserService.updatePatchUser(userPatchDTO), HttpStatus.CREATED);
    }

    @DeleteMapping("delete")
    public ResponseEntity<SuccessDTO> deleteUser(@RequestBody GetUserDTO userDTO){
        return new ResponseEntity<>(crudUserService.deleteUser(userDTO), HttpStatus.OK);
    }

}
