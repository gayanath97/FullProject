package com.example.fullproject.repository;

import com.example.fullproject.entity.Rr;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RrRepository extends JpaRepository<Rr,Long> {


}
