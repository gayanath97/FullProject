package com.example.fullproject.repository;

import com.example.fullproject.entity.ExpenseBill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpenseBillRepository extends JpaRepository<ExpenseBill,Long> {
}
