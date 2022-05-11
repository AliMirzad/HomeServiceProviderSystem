package com.Maktab.Final.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Sort;

@Getter
@Setter
public class ExpertPage {
    private Integer pageNumber = 0;
    private Integer pageSize = 10;
    private Sort.Direction sortDirection = Sort.Direction.ASC;
    private String sortBy = "lastName";
}
