package com.example.SplitWiseMachineCoding.services;

import com.example.SplitWiseMachineCoding.models.Expense;

import java.util.List;
import java.util.Map;

public interface IExpenseService {

    Expense addExpense(Expense expense, Map<Long, Double> paidMap, Map<Long, Double> percentageMap);


    List<Expense> getUserExpenses(Long userId);

}
