package com.example.fullproject.dto;

import com.example.fullproject.entity.RrBill;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class RrDTO {

    private String id;
    private String extensionNo;
    private String customer;
    private String location;
    private String status;

    private String employee;

  //  private Set<RrBillDTO> rrBills;


}
