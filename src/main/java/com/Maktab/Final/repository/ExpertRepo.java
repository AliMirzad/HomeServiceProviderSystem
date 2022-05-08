package com.Maktab.Final.repository;

import com.Maktab.Final.entity.Expert;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpertRepo extends JpaRepository<Expert, Integer> {
    public Expert findExpertById(Integer id);
    public Expert findExpertByNationalCodeAndPassword(String nationalCode, String password);
}
