package com.example.fullproject.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ExpenseBillDTO {

    private String id;
    private String particulars;
    private String amount;
    private String status;
    private String expense;

}
