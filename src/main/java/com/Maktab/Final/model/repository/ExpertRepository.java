package com.Maktab.Final.model.repository;

import com.Maktab.Final.model.entity.Expert;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpertRepository extends JpaRepository<Expert, Integer> {
    public Expert findExpertById(Integer id);
    public Expert findExpertByNationalCodeAndPassword(String nationalCode, String password);
}
