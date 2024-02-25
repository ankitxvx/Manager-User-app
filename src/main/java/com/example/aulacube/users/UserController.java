package com.example.aulacube.users;

import com.example.aulacube.commons.dtos.ErrorResponse;
import com.example.aulacube.users.dtos.CreateUserRequest;
import com.example.aulacube.users.dtos.UserResponse;
import com.example.aulacube.users.dtos.LoginUserRequest;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.net.URI;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final ModelMapper modelMapper;
    public UserController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }
    @PostMapping("")
    ResponseEntity< UserResponse> signupUser(@RequestBody CreateUserRequest request) {
        UserEntity savedUser =  userService.createUser(request);
        URI savedUserUri = URI.create("/users/" + savedUser.getId());

        return ResponseEntity.created(savedUserUri)
                .body(modelMapper.map(savedUser,  UserResponse.class));
    }

    @PostMapping("/login")
    ResponseEntity< UserResponse> loginUser(@RequestBody LoginUserRequest request){
        UserEntity savedUser = userService.loginUser(request.getEmail(), request.getPassword());

        return ResponseEntity.ok()
                .body(modelMapper.map(savedUser,  UserResponse.class));
    }
    @ExceptionHandler({
            UserService.UserNotFoundException.class
    })

    ResponseEntity<ErrorResponse> handleUserExceptions(Exception ex) {
        String message = null;
        HttpStatus status = null;

        if (ex instanceof UserService.UserNotFoundException) {
            message = ex.getMessage();
            status = HttpStatus.NOT_FOUND;
        }

        ErrorResponse response = ErrorResponse.builder()
                .message(message)
                .build();

        return ResponseEntity.status(status).body(response);
    }





    @GetMapping("")
    void loginUsers() {

    }
}
