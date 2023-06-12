/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Alejandro.TFG.Service.Impl;

import com.Alejandro.TFG.Service.UserService;
import com.Alejandro.TFG.exception.NotFoundException;
import com.Alejandro.TFG.exception.UnauthorizedException;
import com.Alejandro.TFG.model.User;
import com.Alejandro.TFG.repository.UserRepository;

import java.util.List;

/**
 *
 * @author Alex
 */
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public User getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found"));
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User login(String email, String password) {
      return userRepository.findByEmailAndPassword(email, password)
              .orElseThrow(() -> new UnauthorizedException("Invalid credentials"));
    }
    
}
