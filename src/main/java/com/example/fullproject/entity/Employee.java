package com.example.fullproject.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Builder
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long employeeCode;

    @Column
    private String email;

    @Column
    private String name;

    @Column
    private String password;

    @Column
    private Long phoneNumber;

    @Column
    private String type;

//    @OneToMany
//    private Set<Reward> rewards;
//
//    @OneToMany
//    private Set<Expense> expenses;
//
//    @OneToMany
//    private Set<Rr> rrs;
//
//    @OneToMany
//    private  Set<Opd> opds;

    @OneToOne
    private  OpdAmount opdAmount;



}
