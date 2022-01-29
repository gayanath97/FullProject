package com.example.fullproject.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class OpdBillDTO {


    private String id;
    private String amount;
    private String particulars;
    private String date;
    private String status;

    private String opd;

}
