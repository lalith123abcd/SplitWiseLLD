package com.example.SplitWiseMachineCoding.services;

import com.example.SplitWiseMachineCoding.dto.BalanceDTO;
import com.example.SplitWiseMachineCoding.helper.BalanceRequest;
import com.example.SplitWiseMachineCoding.models.Expense;
import com.example.SplitWiseMachineCoding.models.Split;
import com.example.SplitWiseMachineCoding.models.User;
import com.example.SplitWiseMachineCoding.repository.ExpenseRepository;
import com.example.SplitWiseMachineCoding.repository.SplitRepository;
import com.example.SplitWiseMachineCoding.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SettleUpService implements ISettleUpService{
    private final ExpenseRepository expenseRepository;
    private final UserRepository userRepository;
    private final SplitRepository splitRepository;
    @Override
    
    public List<BalanceDTO> settleUpUser(Long userId) {

        List<Split> splits = splitRepository.findAll()
                .stream()
                .filter(split ->
                        split.getUser().getId().equals(userId) ||
                                split.getExpense().getCreatedBy().getId().equals(userId))
                .collect(Collectors.toList());

        Map<User, Double> balanceMap = new HashMap<>();

        for (Split split : splits) {

            User user = split.getUser();
            double net = split.getPaid() - split.getOwed();

            balanceMap.put(user,
                    balanceMap.getOrDefault(user, 0.0) + net);
        }

        PriorityQueue<BalanceRequest> creditor = new PriorityQueue<>(
                (a, b) -> Double.compare(b.getBalance(), a.getBalance()));

        PriorityQueue<BalanceRequest> debtor = new PriorityQueue<>(
                (a, b) -> Double.compare(
                        Math.abs(b.getBalance()),
                        Math.abs(a.getBalance())));

        for (Map.Entry<User, Double> entry : balanceMap.entrySet()) {

            if (entry.getValue() > 0) {
                creditor.offer(new BalanceRequest(entry.getKey(), entry.getValue()));
            } else if (entry.getValue() < 0) {
                debtor.offer(new BalanceRequest(entry.getKey(), entry.getValue()));
            }
        }

        List<BalanceDTO> answer = new ArrayList<>();

        while (!creditor.isEmpty() && !debtor.isEmpty()) {

            BalanceRequest currentCreditor = creditor.poll();
            BalanceRequest currentDebtor = debtor.poll();

            double settlementAmount = Math.min(
                    currentCreditor.getBalance(),
                    Math.abs(currentDebtor.getBalance()));

            answer.add(
                    BalanceDTO.builder()
                            .from(currentDebtor.getUser())
                            .to(currentCreditor.getUser())
                            .amount(settlementAmount)
                            .build()
            );

            double newCreditorBalance =
                    currentCreditor.getBalance() - settlementAmount;

            double newDebtorBalance =
                    currentDebtor.getBalance() + settlementAmount;

            if (newCreditorBalance > 0) {
                creditor.offer(
                        new BalanceRequest(
                                currentCreditor.getUser(),
                                newCreditorBalance
                        )
                );
            }

            if (newDebtorBalance < 0) {
                debtor.offer(
                        new BalanceRequest(
                                currentDebtor.getUser(),
                                newDebtorBalance
                        )
                );
            }
        }

        return answer;
    }

}
