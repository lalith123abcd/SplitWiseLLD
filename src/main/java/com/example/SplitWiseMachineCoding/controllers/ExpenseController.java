package com.example.SplitWiseMachineCoding.controllers;

import com.example.SplitWiseMachineCoding.dto.ExpenseRequestDTO;
import com.example.SplitWiseMachineCoding.models.Expense;
import com.example.SplitWiseMachineCoding.services.IExpenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/expenses")
@RequiredArgsConstructor
public class ExpenseController {


    private final IExpenseService expenseService;

    @PostMapping
    public ResponseEntity<Expense> addExpense(
            @RequestBody ExpenseRequestDTO requestDTO) {

        Expense expense = expenseService.addExpense(
                requestDTO.getExpense(),
                requestDTO.getPaidMap(),
                requestDTO.getPercentageMap()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(expense);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Expense>> getUserExpenses(
            @PathVariable Long userId) {

        List<Expense> expenses = expenseService.getUserExpenses(userId);

        return ResponseEntity.ok(expenses);
    }

}
