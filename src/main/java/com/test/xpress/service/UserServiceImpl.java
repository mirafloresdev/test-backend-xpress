package com.test.xpress.service;

import com.test.xpress.config.ApiResponse;
import com.test.xpress.dto.LoginRequest;
import com.test.xpress.exceptions.ResourceNotFoundException;
import com.test.xpress.model.User;
import com.test.xpress.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserRepository userRepository;
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);



    @Autowired
    public UserServiceImpl(BCryptPasswordEncoder bCryptPasswordEncoder, UserRepository userRepository) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public User createUser(User user) {
        String passwordEncriptado = this.bCryptPasswordEncoder.encode(user.getPasswordHash());
        user.setPasswordHash(passwordEncriptado);
        return userRepository.save(user);
    }

    @Override
    public Optional<User> getUserById(Integer userId) {
        return Optional.empty();
    }

    @Override
    public List<User> getAllUsers() {
        return List.of();
    }

    @Override
    public User updateUser(User user) {
        return null;
    }

    @Override
    public void deleteUserById(Integer userId) {

    }


    @Override
    public Optional<User> authUser(LoginRequest loginRequest) {
        String userName = loginRequest.getUsername();
        String password = loginRequest.getPassword();

        Optional<User> user_db = this.getUserByUsername(userName);
        logger.info("user_db: {} " , user_db);

        if (user_db.isPresent() && bCryptPasswordEncoder.matches(password, user_db.get().getPasswordHash())) {
            return Optional.ofNullable(this.userRepository.findByUserNameAndPasswordHash(userName, user_db.get().getPasswordHash())
                    .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado")));
        }

        return Optional.empty();
    }

    @Override
    public Optional<User> getUserByUsername(String username) {
        return this.userRepository.findByUserName(username);
    }


}
