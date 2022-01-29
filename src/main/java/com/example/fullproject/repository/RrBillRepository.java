package com.example.fullproject.repository;

import com.example.fullproject.entity.RrBill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RrBillRepository extends JpaRepository<RrBill,Long> {
}
