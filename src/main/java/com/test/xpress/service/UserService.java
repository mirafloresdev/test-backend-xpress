package com.test.xpress.service;

import com.test.xpress.dto.LoginRequest;
import com.test.xpress.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User createUser(User user);
    Optional<User> getUserById(Integer userId);
    List<User> getAllUsers();
    User updateUser(User user);
    void deleteUserById(Integer userId);
    Optional<User> authUser(LoginRequest loginRequest);
    Optional<User> getUserByUsername(String username);
}
