package com.example.fullproject.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
public class OpdBill {


        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private Double amount;

        private String particulars;

        private Date date;

        private String status;

        @ManyToOne
        @JoinColumn(name = "opd_id")
        private Opd opd;


}
