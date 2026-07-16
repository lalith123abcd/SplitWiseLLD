package com.example.SplitWiseMachineCoding.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Group extends BaseEntity{

    @Column(nullable = false,unique = true)
    private String groupName;

    @OneToMany
    private List<User> users;
}
