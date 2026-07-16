package com.example.SplitWiseMachineCoding.repository;

import com.example.SplitWiseMachineCoding.models.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense,Long> {

}
