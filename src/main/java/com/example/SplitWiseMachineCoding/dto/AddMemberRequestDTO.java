package com.example.SplitWiseMachineCoding.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddMemberRequestDTO {


    private Long groupId;
    private Long userId;
}
