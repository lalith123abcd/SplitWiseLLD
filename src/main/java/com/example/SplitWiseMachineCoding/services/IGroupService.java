package com.example.SplitWiseMachineCoding.services;

import com.example.SplitWiseMachineCoding.dto.GroupRequestDTO;
import com.example.SplitWiseMachineCoding.models.Group;
import com.example.SplitWiseMachineCoding.models.User;

public interface IGroupService {
    Group createGroup(GroupRequestDTO groupRequestDTO);

    Group getGroupById(Long id);

    void addMember(Long groupId,Long userId);

    void removeMember(Long groupId,Long userId);

}
