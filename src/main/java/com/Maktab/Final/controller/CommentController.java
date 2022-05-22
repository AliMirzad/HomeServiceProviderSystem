package com.Maktab.Final.controller;

import com.Maktab.Final.controller.dto.CommentDTO;
import com.Maktab.Final.model.entity.Comment;
import com.Maktab.Final.model.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/")
public class CommentController {
    private final CommentService commentService;
    private final ModelMapper modelMapper = new ModelMapper();

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/comment/create")
    public void create(@RequestBody CommentDTO commentDTO) {
        Comment comment = modelMapper.map(commentDTO, Comment.class);
        commentService.create(comment, commentDTO.getCustomerId(), commentDTO.getExpertId());
    }
}
