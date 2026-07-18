package com.example.SplitWiseMachineCoding.controllers;

import com.example.SplitWiseMachineCoding.dto.AddMemberRequestDTO;
import com.example.SplitWiseMachineCoding.dto.GroupRequestDTO;
import com.example.SplitWiseMachineCoding.models.Group;
import com.example.SplitWiseMachineCoding.repository.GroupRepository;
import com.example.SplitWiseMachineCoding.services.IGroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/groups")
@RequiredArgsConstructor
public class GroupController {

    private final IGroupService groupService;

    @PostMapping("/create")
    public ResponseEntity<Group> createGroup(@RequestBody GroupRequestDTO groupRequestDTO){
        Group group=groupService.createGroup(groupRequestDTO);
        return ResponseEntity.ok(group);
    }

    @GetMapping("/id")
    public ResponseEntity<Group> getGroupById(@PathVariable Long id){

        return ResponseEntity.ok(groupService.getGroupById(id));

    }

    @PostMapping("/addMember")
    public void addMember(@RequestBody AddMemberRequestDTO addMemberRequestDTO){

        Long groupId=addMemberRequestDTO.getGroupId();
        Long userId=addMemberRequestDTO.getUserId();
        groupService.addMember(groupId,userId);
    }

    @DeleteMapping("/removeMember")
    public void removeMember(@RequestBody AddMemberRequestDTO addMemberRequestDTO){

        Long groupId=addMemberRequestDTO.getGroupId();
        Long userId=addMemberRequestDTO.getUserId();
        groupService.removeMember(groupId,userId);
    }




}
