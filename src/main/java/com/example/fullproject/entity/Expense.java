package com.example.fullproject.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String buOrDept;

    private  String project;

    private Integer extensionNo;

    private String customer;

    private String location;

    private String billability;

    @ManyToOne
    @JoinColumn(name = "employee_employee_code")
    private Employee employee;

//    @OneToMany
//    Set<ExpenseBill> expenseBills;
}
