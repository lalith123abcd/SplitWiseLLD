package com.example.SplitWiseMachineCoding.controllers;

import com.example.SplitWiseMachineCoding.dto.ExpenseRequestDTO;
import com.example.SplitWiseMachineCoding.models.Expense;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/expenses")
@RequiredArgsConstructor
public class ExpenseController {


    public ResponseEntity<Expense> addExpense(@RequestBody ExpenseRequestDTO requestDTO){


    }

}
