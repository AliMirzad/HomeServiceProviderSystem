package com.Maktab.Final.repository;

import com.Maktab.Final.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface AdminRepo extends JpaRepository<Admin, Integer> {
    public Admin findAdminById(Integer id);
    public Admin findAdminByNationalCodeAndPassword(String nationalCode, String password);
}
