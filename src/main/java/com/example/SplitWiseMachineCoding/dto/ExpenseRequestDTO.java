package com.example.SplitWiseMachineCoding.dto;

import com.example.SplitWiseMachineCoding.models.Expense;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExpenseRequestDTO {

    private Expense expense;
    private Map<Long, Double> paidMap;
    private Map<Long, Double> percentageMap;
}
