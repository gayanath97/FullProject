package com.example.fullproject.dto;

import com.example.fullproject.entity.Opd;
import com.example.fullproject.entity.OpdAmount;
import com.example.fullproject.entity.Reward;
import com.example.fullproject.entity.Rr;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class EmployeeDTO {

    private String employeeCode;
    private String email;
    private String name;
    private String password;
    private String phoneNumber;
    private String type;
//    private Set<RewardDTO> rewards;
//    private Set<ExpenseDTO> expenses;
//    private Set<RrDTO> rrs;
//    private Set<OpdDTO> opds;
    private String opdAmount;

}
