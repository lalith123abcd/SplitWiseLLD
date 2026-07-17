package com.example.SplitWiseMachineCoding.services.strategies;

import com.example.SplitWiseMachineCoding.models.Expense;
import com.example.SplitWiseMachineCoding.models.Split;
import com.example.SplitWiseMachineCoding.models.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EqualSplitStrategy implements SplitStrategy{
    @Override
    public List<Split> calculateSplits(Expense expense, List<User> users, Map<Long, Double> paidMap, Map<Long, Double> percentageMap) {

        double totalAmount=expense.getTotalAmount();
        double share= totalAmount/ users.size();
        List<Split> splits = new ArrayList<>();
        for(User user:users) {

            double paid = paidMap.getOrDefault(user.getId(), 0.0);

            double owed = share;

            Split split = new Split();
            split.setUser(user);
            split.setExpense(expense);
            split.setOwed(owed);
            split.setPaid(paid);
            splits.add(split);
        }
            return splits;

        }

}
