package com.Maktab.Final.service.serviceInterface;

import com.Maktab.Final.entity.Admin;

public interface AdminServInt {
    public Admin findAdminById(Integer id);
    public Admin findAdminByNationalCodeAndPassword(String nationalCode, String password);
}
