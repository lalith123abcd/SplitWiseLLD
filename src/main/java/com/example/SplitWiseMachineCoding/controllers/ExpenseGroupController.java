package com.example.SplitWiseMachineCoding.controllers;

import com.example.SplitWiseMachineCoding.dto.GroupRequestDTO;
import com.example.SplitWiseMachineCoding.models.ExpenseGroup;
import com.example.SplitWiseMachineCoding.services.ExpenseGroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/groups")
@RequiredArgsConstructor
public class ExpenseGroupController {
    private final ExpenseGroupService expenseGroupService;

    @PostMapping
    public ResponseEntity<ExpenseGroup> createGroup(
            @RequestBody GroupRequestDTO groupRequestDTO) {

        ExpenseGroup group = expenseGroupService.createGroup(groupRequestDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(group);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExpenseGroup> getGroupById(
            @PathVariable Long id) {

        ExpenseGroup group = expenseGroupService.getGroupById(id);

        return ResponseEntity.ok(group);
    }

    @PostMapping("/addMember")
    public ResponseEntity<String> addMember(
            @RequestParam Long groupId,
            @RequestParam Long userId) {

        expenseGroupService.addMember(groupId, userId);

        return ResponseEntity.ok("Member added successfully");
    }

    @PostMapping("/removeMember")
    public ResponseEntity<String> removeMember(
            @RequestParam Long groupId,
            @RequestParam Long userId) {

        expenseGroupService.removeMember(groupId, userId);

        return ResponseEntity.ok("Member removed successfully");
    }

}
