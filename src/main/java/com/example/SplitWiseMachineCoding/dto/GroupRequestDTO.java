package com.example.SplitWiseMachineCoding.dto;

import com.example.SplitWiseMachineCoding.models.User;
import lombok.Data;

import java.util.List;

@Data

public class GroupRequestDTO {

    private  String creatorId;
    private String name;
    private List<User> users;
}
