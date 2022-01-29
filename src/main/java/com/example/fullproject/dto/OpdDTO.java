package com.example.fullproject.dto;

import com.example.fullproject.entity.OpdBill;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class OpdDTO {

    private String id;
    private String date;

    private String employee;

   // private Set<OpdBillDTO> opdBills;


}
