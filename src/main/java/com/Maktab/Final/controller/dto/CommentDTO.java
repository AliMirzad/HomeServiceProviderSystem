package com.Maktab.Final.controller.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class CommentDTO {
    private Integer id;
    private String comment;
    private Boolean point;
    private Integer customerId;
    private Integer expertId;

    public CommentDTO(Integer id, String comment, Boolean point, Integer customerId, Integer expertId) {
        this.id = id;
        this.comment = comment;
        this.point = point;
        this.customerId = customerId;
        this.expertId = expertId;
    }
}
