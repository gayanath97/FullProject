package com.example.fullproject.repository;

import com.example.fullproject.entity.Opd;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OpdRepository extends JpaRepository<Opd,Long> {
}
