package com.example.SplitWiseMachineCoding.services.strategies;

import com.example.SplitWiseMachineCoding.exceptions.InvalidExpenseException;
import com.example.SplitWiseMachineCoding.models.Expense;
import com.example.SplitWiseMachineCoding.models.Split;
import com.example.SplitWiseMachineCoding.models.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PercentageSplitStrategy implements SplitStrategy {
    @Override
    public List<Split> calculateSplits(Expense expense, List<User> users, Map<Long, Double> paidMap, Map<Long, Double> percentageMap) {
        double totalAmount=expense.getTotalAmount();

        List<Split> splits = new ArrayList<>();
         double totalPercentage=percentageMap.values()
                 .stream()
                 .mapToDouble(Double::doubleValue)
                 .sum();

         if(Math.abs(totalPercentage-100.0)>0.0001){
             throw new InvalidExpenseException("percantage input is wrong");
         }

        for(User user:users) {

            double paid = paidMap.getOrDefault(user.getId(), 0.0);
             double percentage=percentageMap.getOrDefault(user.getId(),0.0);
            double owed = percentage* totalAmount /100;

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
