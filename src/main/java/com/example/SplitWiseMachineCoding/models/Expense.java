package com.example.SplitWiseMachineCoding.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Expense extends BaseEntity{

    private String description;

    @Column(name = "total_amount")
    private Double totalAmount;

    @Column(name = "split_type")
    @Enumerated(EnumType.STRING)
    private SplitType splitType;

    @ManyToOne

    @JoinColumn(name="group_id")
    private ExpenseGroup group;

    @ManyToOne
    @Column(name = "created_by",nullable = false)
    private User createdBy;


    @OneToMany(mappedBy = "expense", cascade= CascadeType.ALL)
    @JsonManagedReference
    private List<Split> splits=new ArrayList<>();


    @Transient // This field is not persisted in the database
    private List<Long> userIds; // This is used to get the user ids from the request body and then create the splits for each user.
}


