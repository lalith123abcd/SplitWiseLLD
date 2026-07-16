package com.example.SplitWiseMachineCoding.services;

import com.example.SplitWiseMachineCoding.dto.UserRequestDTO;
import com.example.SplitWiseMachineCoding.models.User;

public interface IUserService {

    public User createUser(UserRequestDTO requestDTO);

    public User getUser(Long id);

}
