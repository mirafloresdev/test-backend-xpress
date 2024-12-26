package com.test.xpress.controller;

import com.test.xpress.config.ApiResponse;
import com.test.xpress.dto.LoginRequest;
import com.test.xpress.exceptions.ResourceNotFoundException;
import com.test.xpress.model.User;
import com.test.xpress.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Optional;

@RestController
@RequestMapping("api/user")
@CrossOrigin(origins = "*", methods = {RequestMethod.POST, RequestMethod.PUT})
public class UserController {

    @Autowired
    public UserService userService;

    @PostMapping()
    public ResponseEntity<ApiResponse<User>> createUser(@RequestBody User user) {
        User us = userService.createUser(user);
        ApiResponse<User> apiResponse = new ApiResponse<>(
                Collections.emptyList(),
                "SUCCESS",
                us
        );
        return ResponseEntity.ok(apiResponse);
    }


    @PostMapping("/auth")
    public ResponseEntity<ApiResponse<Optional<User>>> getUserByUserName(@RequestBody LoginRequest loginRequest) {

        Optional<User> us = this.userService.authUser(loginRequest);
        if(us.isPresent()) {
            ApiResponse<Optional<User>> apiResponse = new ApiResponse<>(
                    Collections.emptyList(),
                    "SUCCESS",
                    us
            );
            return ResponseEntity.ok(apiResponse);
        }else{
            ApiResponse<Optional<User>> apiResp = new ApiResponse<>(
                    Collections.singletonList("Usuario no encontrado"),
                    "FAILURE",
                    null
            );
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResp);
        }

    }


}
