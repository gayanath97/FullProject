package com.example.fullproject.repository;

import com.example.fullproject.entity.OpdBill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OpdBillRepository extends JpaRepository<OpdBill,Long> {
}
