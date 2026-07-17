package com.example.SplitWiseMachineCoding.services.strategies;

import com.example.SplitWiseMachineCoding.models.Expense;
import com.example.SplitWiseMachineCoding.models.Split;
import com.example.SplitWiseMachineCoding.models.User;

import java.util.List;
import java.util.Map;

public interface SplitStrategy {

    List<Split> calculateSplits(Expense expense, List<User> users
    , Map<Long,Double> paidMap, Map<Long,Double> percentageMap);


}
