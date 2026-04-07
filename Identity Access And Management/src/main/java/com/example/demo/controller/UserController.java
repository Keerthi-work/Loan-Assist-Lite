package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.UserDTO;
import com.example.demo.entity.Role;
import com.example.demo.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;


    // ✅ CREATE NEW USER
    @PostMapping("/add")
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO dto) 
    {
        UserDTO created = userService.insertNewUser(dto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }


    // ✅ FETCH ALL USERS
    @GetMapping("/all")
    public ResponseEntity<List<UserDTO>> getAllUsers()
    {
        List<UserDTO> list = userService.fetchAllUsers();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }


    // ✅ FETCH USER BY ID
    @GetMapping("/{userId}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long userId)
    {
        UserDTO dto = userService.fetchByUserId(userId);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }


    //DELETE USER BY ID
    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<String> deleteUserById(@PathVariable Long userId)
    {
        String message = userService.deleteByUserId(userId);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }


    //UPDATE USER
    @PutMapping("/update/{userId}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long userId, @RequestBody UserDTO dto)
    {
        UserDTO updated = userService.updateUser(userId, dto);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }


    //FIND USERS BY STATUS (String)
    @GetMapping("/status/{status}")
    public ResponseEntity<List<UserDTO>> findByStatus(@PathVariable String status)
    {
        List<UserDTO> users = userService.findByStatus(status);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }


    //UPDATE ONLY STATUS
    @PutMapping("/{userId}/status")
    public ResponseEntity<UserDTO> updateStatus(
            @PathVariable Long userId,
            @RequestParam String status) {

        UserDTO updated = userService.updateUserStatus(userId, status);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }


    // FIND USERS BY ROLE
    @GetMapping("/role/{role}")
    public ResponseEntity<List<UserDTO>> findByRole(@PathVariable Role role) 
    {
        List<UserDTO> users = userService.findByRole(role);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

}