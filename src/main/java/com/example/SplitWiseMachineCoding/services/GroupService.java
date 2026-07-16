package com.example.SplitWiseMachineCoding.services;

import com.example.SplitWiseMachineCoding.dto.GroupRequestDTO;
import com.example.SplitWiseMachineCoding.models.Group;
import com.example.SplitWiseMachineCoding.models.User;
import com.example.SplitWiseMachineCoding.repository.GroupRepository;
import com.example.SplitWiseMachineCoding.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupService implements IGroupService{
    private final UserRepository userRepository;
    private final GroupRepository groupRepository;
    @Override
    public Group createGroup(GroupRequestDTO groupRequestDTO) {

        String groupName=groupRequestDTO.getName();

        List<User> users=new ArrayList<>();
        for(User user:groupRequestDTO.getUsers()){
            users.add(user);

        }

        Group group=Group.builder().
                groupName(groupName)
                        .users(users)
                                .build();
        groupRepository.save(group);

        return group;
    }

    @Override
    public Group getGroupById(Long id) {
        return groupRepository.findById(id).orElseThrow(()->new RuntimeException("Group not found"));
    }

    @Override
    public void addMember(Long groupId, Long userId) {

        Group group=groupRepository.findById(groupId)
                .orElseThrow(()->new RuntimeException("group not found"));

        User user=userRepository.findById(userId)
                .orElseThrow(()->new RuntimeException("User Not Found"));


        List<User> groupUsers=group.getUsers();

        if (groupUsers==null){
            groupUsers=new ArrayList<>();
        }
        if (groupUsers.contains(user)) {
            throw new RuntimeException("User already exists in the group");
        }


        groupUsers.add(user);
        group.setUsers(groupUsers);
        groupRepository.save(group);
    }

    @Override
    public void removeMember(Long groupId, Long userId) {
        Group group=groupRepository.findById(groupId)
                .orElseThrow(()->new RuntimeException("group not found"));

        User user=userRepository.findById(userId)
                .orElseThrow(()->new RuntimeException("User Not Found"));


        List<User> groupUsers=group.getUsers();

        if (groupUsers==null){
            groupUsers=new ArrayList<>();
        }
        if (!groupUsers.contains(user)) {
            throw new RuntimeException("User doesnt exists in the group");
        }


        groupUsers.remove(user);
        group.setUsers(groupUsers);
        groupRepository.save(group);
    }
}
