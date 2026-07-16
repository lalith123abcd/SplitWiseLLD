package com.example.SplitWiseMachineCoding.services;

import com.example.SplitWiseMachineCoding.dto.UserRequestDTO;
import com.example.SplitWiseMachineCoding.models.User;
import com.example.SplitWiseMachineCoding.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService  implements IUserService{

    private final UserRepository userRepository;
    @Override
    public User createUser(UserRequestDTO requestDTO) {

        String name=requestDTO.getName();
        String email=requestDTO.getEmail();

        User user=User.builder().
                name(name)
                .email(email)
                .build();
        userRepository.save(user);
        return user;

    }

    @Override
    public User getUser(Long id) {

        User user=userRepository.findById(id)
                .orElseThrow(()->new RuntimeException("User Not Found"));

        return user;
    }


}
