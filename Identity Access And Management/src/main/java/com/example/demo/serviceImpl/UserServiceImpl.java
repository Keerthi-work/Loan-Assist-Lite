package com.example.demo.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.dto.UserDTO;
import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;

@Service
public class UserServiceImpl implements UserService
{
	@Autowired
    private UserRepository repository;

    //ENTITY → DTO
    private UserDTO convertToDTO(User user) 
    {
        UserDTO dto = new UserDTO();
        dto.setUserId(user.getUserId());
        dto.setName(user.getName());
        dto.setRole(user.getRole());
        dto.setEmail(user.getEmail());
        dto.setPhone(user.getPhone());
        dto.setStatus(user.getStatus());   
        dto.setPassword(user.getPassword());     
        return dto;
    }

    //DTO → ENTITY
    private User convertToEntity(UserDTO dto)
    {
        User user = new User();
        user.setUserId(dto.getUserId());
        user.setName(dto.getName());
        user.setRole(dto.getRole());                     
        user.setEmail(dto.getEmail());
        user.setPhone(dto.getPhone());
        user.setStatus(dto.getStatus()); 
        user.setPassword(dto.getPassword());
        return user;
    }
    
    private UserDTO convertUserToDTO(User user)
    {
        UserDTO dto = new UserDTO();
        dto.setUserId(user.getUserId());
        dto.setName(user.getName());
        dto.setRole(user.getRole());
        dto.setEmail(user.getEmail());
        dto.setPhone(user.getPhone());
        dto.setStatus(user.getStatus());
        // ✅ SECURITY: never expose password
        dto.setPassword(null);
        return dto;
    }
    
 // ✅ INSERT NEW USER
    @Override
    public UserDTO insertNewUser(UserDTO userDto)
    {
        User entity = convertToEntity(userDto);
        User saved = repository.save(entity);
        return convertToDTO(saved);
    }

    // ✅ FETCH ALL USERS
    @Override
    public List<UserDTO> fetchAllUsers()
    {
        List<User> users = repository.findAll();
        List<UserDTO> dtoList = new ArrayList<>();

        for (User user : users) {
            dtoList.add(convertToDTO(user));
        }
        return dtoList;
    }
	
	// ✅ FETCH BY USER ID
    @Override
    public UserDTO fetchByUserId(Long userId)
    {
        User user = repository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));
        return convertToDTO(user);
    }

    // ✅ DELETE BY USER ID
    @Override
    public String deleteByUserId(Long userId)
    {
        if (!repository.existsById(userId)) {
            throw new RuntimeException("User not found with ID: " + userId);
        }
        repository.deleteById(userId);
        return "User deleted successfully with ID: " + userId;
    }

 // ✅ UPDATE USER
    @Override
    public UserDTO updateUser(Long userId, UserDTO userDTO) 
    {
        User existing = repository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));

        existing.setName(userDTO.getName());
        existing.setRole(userDTO.getRole());
        existing.setEmail(userDTO.getEmail());
        existing.setPhone(userDTO.getPhone());
        existing.setStatus(userDTO.getStatus()); // ✅ convert String → Enum;
        existing.setPassword(userDTO.getPassword());
        User updated = repository.save(existing);
        return convertToDTO(updated);
    }

    // ✅ FIND USERS BY STATUS
    public List<UserDTO> findByStatus(String status) 
    {
        List<User> userList = repository.findByStatus(status);
        List<UserDTO> dtoList = new ArrayList<>();
        for (User user : userList) {
            dtoList.add(convertToDTO(user));
        }
        return dtoList;
    }
    
  //UPDATE ONLY STATUS
    @Override
    public UserDTO updateUserStatus(Long userId, String status)
    {
        User user = repository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));
        user.setStatus(status);
        User updated = repository.save(user);
        return convertToDTO(updated);
    }
    //FIND USERS BY ROLE
    public List<UserDTO> findByRole(Role role) 
    {
        List<User> users = repository.findByRole(role);
        List<UserDTO> dtoList = new ArrayList<>();

        for (User user : users) {
            dtoList.add(convertToDTO(user));
        }
        return dtoList;
    }

   
}