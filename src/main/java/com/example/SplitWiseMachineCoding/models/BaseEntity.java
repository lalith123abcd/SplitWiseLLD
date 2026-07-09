package com.example.SplitWiseMachineCoding.models;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;


@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;


    @Column(name="created_at",nullable = false,updatable = false)
    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    protected Date createdAt;


    @Column(name="updated_at",nullable = false,updatable = false)
    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    protected Date updatedAt;



}
