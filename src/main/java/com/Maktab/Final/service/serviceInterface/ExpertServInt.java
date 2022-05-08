package com.Maktab.Final.service.serviceInterface;

import com.Maktab.Final.entity.Expert;

public interface ExpertServInt {
    public Expert findExpertById(Integer id);
    public Expert findExpertByNationalCodeAndPassword(String nationalCode, String password);
}
