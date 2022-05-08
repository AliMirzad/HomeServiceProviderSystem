package com.Maktab.Final.service.serviceInterface;

import com.Maktab.Final.entity.Comment;
import com.Maktab.Final.entity.Customer;
import com.Maktab.Final.entity.Expert;

public interface CommentServInt {
    public Comment findCommentByExpert(Expert expert);
    public Comment findCommentByCustomer(Customer customer);
}
