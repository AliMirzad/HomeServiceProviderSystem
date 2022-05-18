package com.Maktab.Final.model.service.serviceInterface;

import com.Maktab.Final.model.entity.Comment;
import com.Maktab.Final.model.entity.Customer;
import com.Maktab.Final.model.entity.Expert;

import java.util.List;

public interface CommentServiceInterface {
    List<Comment> findCommentByExpert(Expert expert);
    List<Comment> findCommentByCustomer(Customer customer);
}
