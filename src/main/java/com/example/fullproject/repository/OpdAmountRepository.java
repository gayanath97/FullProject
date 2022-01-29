package com.example.fullproject.repository;

import com.example.fullproject.entity.OpdAmount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OpdAmountRepository extends JpaRepository<OpdAmount,Long> {
}
