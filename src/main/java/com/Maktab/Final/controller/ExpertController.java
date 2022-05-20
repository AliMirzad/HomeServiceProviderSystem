package com.Maktab.Final.controller;

import com.Maktab.Final.controller.dto.ExpertDTO;
import com.Maktab.Final.model.entity.Expert;
import com.Maktab.Final.model.service.ExpertService;
import org.modelmapper.ModelMapper;


public class ExpertController {
    private final ExpertService expertService;
    private final ModelMapper modelMapper = new ModelMapper();

    public ExpertController(ExpertService expertService) {
        this.expertService = expertService;
    }

    private Expert convertToEntity(ExpertDTO expertDTO) {
        return modelMapper.map(expertDTO, Expert.class);
    }
    private ExpertDTO convertToDTO(Expert expert){
        return modelMapper.map(expert, ExpertDTO.class);
    }
}
