package com.Maktab.Final.model.repository;

import com.Maktab.Final.model.entity.Expert;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpertRepository extends JpaRepository<Expert, Integer> {
    Expert findExpertById(Integer id);
}
