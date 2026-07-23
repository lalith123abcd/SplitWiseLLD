package com.example.SplitWiseMachineCoding.services;

import com.example.SplitWiseMachineCoding.models.User;

import java.util.List;
import com.example.SplitWiseMachineCoding.dto.GroupRequestDTO;
import com.example.SplitWiseMachineCoding.models.ExpenseGroup;
import com.example.SplitWiseMachineCoding.models.User;
import com.example.SplitWiseMachineCoding.repository.ExpenseGroupRepository;
import com.example.SplitWiseMachineCoding.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExpenseGroupService {
    private final ExpenseGroupRepository expenseGroupRepository;
    private final UserRepository userRepository;

    public ExpenseGroup createGroup(GroupRequestDTO groupRequestDTO) {

        ExpenseGroup group = new ExpenseGroup();
        group.setName(groupRequestDTO.getName());

        List<User> members = new ArrayList<>();

        for (User user : groupRequestDTO.getUsers()) {
            User dbUser = userRepository.findById(user.getId())
                    .orElseThrow(() -> new RuntimeException("User not found"));

            members.add(dbUser);
        }

        group.setMembers(members);

        return expenseGroupRepository.save(group);
    }

    public ExpenseGroup getGroupById(Long id) {
        return expenseGroupRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Group not found"));
    }

    public void addMember(Long groupId, Long userId) {

        ExpenseGroup group = expenseGroupRepository.findById(groupId)
                .orElseThrow(() -> new RuntimeException("Group not found"));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<User> members = group.getMembers();

        if (members == null) {
            members = new ArrayList<>();
        }

        if (members.contains(user)) {
            throw new RuntimeException("User already exists in group");
        }

        members.add(user);
        group.setMembers(members);

        expenseGroupRepository.save(group);
    }

    public void removeMember(Long groupId, Long userId) {

        ExpenseGroup group = expenseGroupRepository.findById(groupId)
                .orElseThrow(() -> new RuntimeException("Group not found"));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<User> members = group.getMembers();

        if (members == null || !members.contains(user)) {
            throw new RuntimeException("User not found in group");
        }

        members.remove(user);

        group.setMembers(members);

        expenseGroupRepository.save(group);
    }
}
