package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.UserDTO;
import com.example.demo.entity.Role;

public interface UserService 
{    
	public UserDTO insertNewUser(UserDTO userDto);
    public List<UserDTO> fetchAllUsers();
	public UserDTO fetchByUserId(Long userId);
    public String deleteByUserId(Long userId);
    public UserDTO updateUser(Long userId, UserDTO userDTO);
    public List<UserDTO> findByStatus(String status);
    public UserDTO updateUserStatus(Long userId, String status);
    public List<UserDTO> findByRole(Role role);
    
}

