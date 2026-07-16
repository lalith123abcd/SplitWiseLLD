package com.example.SplitWiseMachineCoding.services;

import com.example.SplitWiseMachineCoding.models.Expense;
import com.example.SplitWiseMachineCoding.models.ExpenseGroup;
import com.example.SplitWiseMachineCoding.models.Split;
import com.example.SplitWiseMachineCoding.models.User;
import com.example.SplitWiseMachineCoding.repository.ExpenseGroupRepository;
import com.example.SplitWiseMachineCoding.repository.ExpenseRepository;
import com.example.SplitWiseMachineCoding.repository.SplitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
public class ExpenseService implements IExpenseService{
    private final ExpenseRepository expenseRepository;
    private final ExpenseGroupRepository groupRepository;

    private final SplitRepository splitRepository;

    @Override
    public Expense addExpense(Expense expense, Map<Long, Double> paidMap, Map<Long, Double> percentageMap) {

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

      Expense newExpense= expenseRepository.save(expense);


        // calculate the splits



        //save the splits

        List<Split> splits=new ArrayList<>();
        for(User user:users){
            Split split=new Split();
            split.setExpense(expense);
            split.setUser(user);

            Double paid=paidMap.getOrDefault(user.getId(),0.0);

            Double owed=expense.getTotalAmount()/ users.size();

            split.setOwed(owed);
            split.setPaid(paid);

            splitRepository.saveAll(splits);

            return newExpense;

        }

    }

    @Override
    public List<Expense> getUserExpenses(Long userId) {
        return expenseRepository.findAll().stream()
                .filter(e->e.getSplits().stream().anyMatch(s->s.getUser().getId().equals(userId)))
                .collect(Collectors.toList());
    }
}
