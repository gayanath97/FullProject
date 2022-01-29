package com.example.fullproject.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class OpdAmountDTO {

    private String id;
    private String amount;
    private String expireDate;
    //private String employee;


}
