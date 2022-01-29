package com.example.fullproject.dto;

import com.example.fullproject.entity.Employee;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class RewardDTO {

    private String rewardId;
    private String expireDate;
    private String amount;
    private String addedDate;
    private String employee;
}
