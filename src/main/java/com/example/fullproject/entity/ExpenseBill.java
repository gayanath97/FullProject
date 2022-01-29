package com.example.fullproject.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
public class ExpenseBill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String particulars;

    private Double amount;

    private  String status;

    @ManyToOne
    @JoinColumn(name = "expense_id")
    private Expense expense;


}
