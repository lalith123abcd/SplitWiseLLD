package com.example.SplitWiseMachineCoding.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "user")
public class User extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false,unique = true)
    private String email;


}
