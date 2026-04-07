package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.entity.User.Status;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    public List<User> findByRole(Role role);

    public List<User> findByStatus(String status);

	public User findByEmail(String email);

}