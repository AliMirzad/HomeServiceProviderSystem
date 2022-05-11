package com.Maktab.Final.service.serviceInterface;

import com.Maktab.Final.entity.Expert;
import com.Maktab.Final.entity.ExpertPage;
import com.Maktab.Final.entity.ExpertSearchCriteria;
import org.springframework.data.domain.Page;

public interface ExpertServInt {
    public Expert findExpertById(Integer id);
    public Expert findExpertByNationalCodeAndPassword(String nationalCode, String password);
    public Page<Expert> getCustomers(ExpertPage expertPage, ExpertSearchCriteria expertSearchCriteria);
}
