package com.Maktab.Final.service;

import com.Maktab.Final.entity.Comment;
import com.Maktab.Final.entity.Customer;
import com.Maktab.Final.entity.Expert;
import com.Maktab.Final.repository.CommentRepo;
import com.Maktab.Final.service.serviceInterface.CommentServInt;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class CommentServ implements CommentServInt {
    //--------------------------------------------------------------needed
    private CommentRepo commentRepo;

    public CommentServ(CommentRepo commentRepo) {
        this.commentRepo = commentRepo;
    }

    //--------------------------------------------------------------methods
    @Override
    public Comment findCommentByExpert(Expert expert) {
        return commentRepo.findCommentByExpert(expert);
    }

    @Override
    public Comment findCommentByCustomer(Customer customer) {
        return commentRepo.findCommentByCustomer(customer);
    }

    @Transactional
    public void save(Comment comment) {
        commentRepo.save(comment);
    }
}
