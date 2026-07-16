package com.example.SplitWiseMachineCoding.controllers;

import com.example.SplitWiseMachineCoding.dto.UserRequestDTO;
import com.example.SplitWiseMachineCoding.models.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

    public ResponseEntity<User> createUser(UserRequestDTO requestDTO){

    }
}
