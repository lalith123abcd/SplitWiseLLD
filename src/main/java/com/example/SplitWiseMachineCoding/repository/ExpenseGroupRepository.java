package com.example.SplitWiseMachineCoding.repository;

import com.example.SplitWiseMachineCoding.models.ExpenseGroup;
import jdk.jfr.Registered;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpenseGroupRepository extends JpaRepository<ExpenseGroup,Long> {
}
