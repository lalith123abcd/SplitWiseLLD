package com.example.SplitWiseMachineCoding.services;

import com.example.SplitWiseMachineCoding.models.Expense;
import com.example.SplitWiseMachineCoding.models.ExpenseGroup;
import com.example.SplitWiseMachineCoding.models.Split;
import com.example.SplitWiseMachineCoding.models.User;
import com.example.SplitWiseMachineCoding.repository.ExpenseGroupRepository;
import com.example.SplitWiseMachineCoding.repository.ExpenseRepository;
import com.example.SplitWiseMachineCoding.repository.SplitRepository;
import com.example.SplitWiseMachineCoding.services.strategies.SplitStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExpenseService implements IExpenseService{
    private final ExpenseRepository expenseRepository;
    private final ExpenseGroupRepository groupRepository;

    private final SplitRepository splitRepository;
    private final Map<String, SplitStrategy> strategyMap;

    @Override
    public Expense addExpense(Expense expense, Map<Long, Double> paidMap, Map<Long, Double> percentageMap) {
        System.out.println("Total Amount = " + expense.getTotalAmount());
        List<User> users;
        //get users involved

        if(expense.getGroup()!=null){
            ExpenseGroup group=groupRepository.findById(expense.getGroup().getId())
                    .orElseThrow(()->new IllegalArgumentException("Group not found"));

            users=group.getMembers();
        }
        else{
            throw new IllegalArgumentException("Expense must have a group or users involved");


        }
        expenseRepository.save(expense);


        // calculate the splits



        //save the splits
        SplitStrategy strategy=strategyMap.get(expense.getSplitType().name());
        if(strategy==null)throw new IllegalArgumentException("strategy not found");

        List<Split> splits=strategy.calculateSplits(expense,users,paidMap,percentageMap);
        splitRepository.saveAll(splits);
        expense.setSplits(splits);


        return expense;

    }



    @Override
    public List<Expense> getUserExpenses(Long userId) {
        return expenseRepository.findAll().stream()
                .filter(e->e.getSplits().stream().anyMatch(s->s.getUser().getId().equals(userId)))
                .collect(Collectors.toList());
    }
}


