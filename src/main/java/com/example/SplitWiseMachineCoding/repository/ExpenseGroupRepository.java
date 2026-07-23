package com.example.SplitWiseMachineCoding.repository;

import com.example.SplitWiseMachineCoding.models.ExpenseGroup;
import org.hibernate.boot.archive.internal.JarProtocolArchiveDescriptor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseGroupRepository extends JpaRepository<ExpenseGroup,Long> {
}
