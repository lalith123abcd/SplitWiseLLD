package com.example.SplitWiseMachineCoding.dto;

import com.example.SplitWiseMachineCoding.models.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BalanceDTO {

    private User from;
    private User to;
    private Double amount;
}
