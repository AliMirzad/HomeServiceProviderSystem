package com.Maktab.Final.service.serviceInterface;

import com.Maktab.Final.entity.Comment;
import com.Maktab.Final.entity.Customer;
import com.Maktab.Final.entity.Expert;

import java.util.List;

public interface CommentServInt {
    public List<Comment> findCommentByExpert(Expert expert);
    public List<Comment> findCommentByCustomer(Customer customer);
}
