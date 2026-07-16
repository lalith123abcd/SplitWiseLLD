package com.example.SplitWiseMachineCoding.models;

import jakarta.persistence.*;
import jdk.dynalink.linker.LinkerServices;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExpenseGroup extends BaseEntity{

    private String name;

    @ManyToOne
    @JoinColumn(name="owner")
    private User user;


    @ManyToMany
    @JoinTable(
            name="expense_group_members",
            joinColumns = @JoinColumn(name="expense_group_id"),
            inverseJoinColumns = @JoinColumn(name="member_id")
    )
    private List<User> members;
}
