package com.example.fullproject.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ExpenseDTO {

    private String id;
    private String buOrDept;
    private String project;
    private String extensionNo;
    private String customer;
    private String location;
    private String billability;

    private String employee;
   // private Set<ExpenseBillDTO> expenseBill;
}
