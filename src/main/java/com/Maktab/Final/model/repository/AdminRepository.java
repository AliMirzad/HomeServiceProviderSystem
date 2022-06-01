package com.Maktab.Final.model.repository;

import com.Maktab.Final.model.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AdminRepository extends JpaRepository<Admin, Integer> {
    Admin findAdminById(Integer id);

    Admin findAdminByNationalCode(String nationalCode);
}
